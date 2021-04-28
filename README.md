# Data Generator

数据库随机数据生成工具

- 使用 [java-faker](https://github.com/DiUS/java-faker) 生成假数据

## 使用

创建 application.yml 文件，配置数据库和数据类型：

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver     # 指定数据库驱动
    url: "jdbc:mysql://localhost:3306/test"         # 根据数据库类型设置 URL
    username: root
    password: root
generator:
  locale: zh_CN                              # 指定国家
  tables:
    - table: table1                          # 表名
      count: 5                               # 生成多少行
      columns:
        first_name: Name.firstName           # 指定列数据类型
        last_name: Name.lastName
        job: Job.title
        phone_number: PhoneNumber.cellPhone
        address: Address.streetAddress
    - table: table2
      count: 10
      columes:
        ...
```

程序内置以下数据库驱动：

| Database   | Driver class name                              |
|------------|------------------------------------------------|
| SQLite     | `org.sqlite.JDBC`                              |
| MySQL      | `com.mysql.cj.jdbc.Driver`                     |
| MariaDB    | `org.mariadb.jdbc.Driver`                      |
| Oracle     | `oracle.jdbc.driver.OracleDriver`              |
| SQL Server | `com.microsoft.sqlserver.jdbc.SQLServerDriver` |

列类型的格式为：`类型.具体类型`

- `类型` 对应 java-faker 中的类
- `具体类型` 对应 java-faker 类中的方法

具体有哪些类型可以参考：[JavaDoc](http://dius.github.io/java-faker/apidocs/index.html)

### 运行

配置好 application.yml 并放在 jar 文件同级目录，使用 `java -jar` 命令运行，或者双击 jar 文件运行。