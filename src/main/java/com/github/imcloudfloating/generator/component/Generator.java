package com.github.imcloudfloating.generator.component;

import com.github.imcloudfloating.generator.config.GeneratorConfig;
import com.github.imcloudfloating.generator.mapper.InsertionMapper;
import com.github.imcloudfloating.generator.config.TableConfig;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据生成器
 */
@Slf4j
@Component
public class Generator {
    @Resource
    private GeneratorConfig generatorConfig;

    @Resource
    private InsertionMapper insertionMapper;

    private Faker faker;

    @PostConstruct
    protected void init() {
        faker = new Faker(generatorConfig.getLocale());
    }

    @Transactional(rollbackFor = Exception.class)
    public void generate() throws Exception {
        List<TableConfig> tableConfigs = generatorConfig.getTables();

        for (TableConfig config : tableConfigs) {
            System.out.printf("Generating data for table: %s.\n", config.getTable());
            for (int cnt = 1; cnt <= config.getCount(); cnt++) {
                System.out.printf("Generating %d of %d rows.\n", cnt, config.getCount());
                insertionMapper.insert(config.getTable(), generateData(config.getColumns()));
            }
        }

        System.out.println("Done.");
    }

    /**
     * 生成数据
     *
     * @param columns 列配置
     * @return {@link HashMap} 每一列的数据
     */
    private HashMap<String, Object> generateData(Map<String, String> columns) throws Exception {
        HashMap<String, Object> tableData = new HashMap<>();

        for (Map.Entry<String, String> entry : columns.entrySet()) {
            String columnName = entry.getKey();
            String[] columnType = entry.getValue().split("\\.");

            Object obj = createInstance(columnType[0]);

            if (obj == null) {
                throw new NullPointerException();
            }
            // 根据类型生成数据
            Object columnValue = obj.getClass().getMethod(columnType[1]).invoke(obj);

            tableData.put(columnName, columnValue);
        }

        return tableData;
    }

    /**
     * 创建 java-faker 类型对象
     */
    private Object createInstance(String className) {
        Object obj = null;

        try {
            Class<?> c = Class.forName("com.github.javafaker." + className);
            Constructor<?> c0 = c.getDeclaredConstructor(Faker.class);
            c0.setAccessible(true);
            obj = c0.newInstance(faker);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return obj;
    }
}
