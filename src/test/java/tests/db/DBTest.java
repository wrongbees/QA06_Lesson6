package tests.db;

import baseEntities.BaseApiTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import services.DataBaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest extends BaseApiTest {
    public static Logger logger = LoggerFactory.getLogger(DataBaseServices.class);

    @Test
    public void connectionTest() {

    }

    @Test
    public void firstTest() {
        logger.info("Test is started...");

        String sql = "SELECT * FROM public.customers;";

        ResultSet rs = dbService.executeQuery(sql);

        try {
            while (rs.next()){
                String UserId = rs.getString("id");
                String firstname = rs.getString("firstname");
                String age = rs.getString("age");

                logger.info("id: "+UserId);
                logger.info("firstname: "+firstname);
                logger.info("age: "+age);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        logger.info("Test is completed...");
    }
}
