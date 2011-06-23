# Config

## DESCRIPTION

Load configuration objects through a simple interface that allows environment
specific overrides.

## EXAMPLE

    package example;

    import javax.sql.DataSource;

    import com.frdna.config.Config;

    public class MyExample {

        public void doSomething() {
            // Load the DataSource from configuration
            MyDao dao = Config.get(MyDao.class, "myDao");
            // Do stuff with the DAO
            // ...
        }
    }

### Configuring

The environment, file, and context type can be configured by using a
"config.environment", "config.file", and "config.context" system property.  By
default the environment is set to "development", the file is set to "config.xml"
and the context is set to "spring".  The above example with no configuration
system properties set would create MyDao with the development DataSource from
the configuration files below.

`classpath:config.xml`

    <beans
      xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

      <bean id="myDao" class="example.MyDao">
        <property name="dataSource" ref="dataSource" />
      </bean>
    </beans>

`classpath:development/config.xml`

    <beans
      xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

      <bean id="dataSource"
            class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="org.postgresql.Driver" />
        <property name="jdbcUrl"
                  vlaue="jdbc:postgresql://localhost:5432/my_db_development" \>
        <property name="user" value="dev_user" \>
        <property name="password" value="password" \>
      </bean>
    </beans>

If "config.environment" system property had been set to "production", "myDao"
would have been configured with the following DataSource.

`classpath:production/config.xml`

    <beans
      xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/jee
        http://www.springframework.org/schema/jee/spring-jee-3.0.xsd">

      <jee:jndi-lookup id="dataSource" jndi-name="jdbc/MyDataSource" />
    </beans>

## DEPENDENCIES

  * [Ruby](http://www.ruby-lang.org/ "Ruby") (>=1.8.7)
  * [Rake](http://rake.rubyforge.org/ "Rake")
  * ZenTest (optional for the autotest task)
  * Java SE Development Kit (>=1.5)
  * JUnit (4.8.2)
  * Checkstyle (5.3)
  * log4j (1.2 for logging in test)
  * Spring (>= 3.0.0)

## BUILD

    git clone https://github.com/freerangedata/config.git
    cd config
    git submodule update --init
    rake dependencies            # if you have curl
    rake                         # build and test

## LICENSE

Copyright (c) 2011 Free Range Data, LLC, released under the MIT license
