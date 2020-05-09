package com.wuhe.background;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class BackgroundApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads()  throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    void testLogger() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("testLogger");
    }

}
