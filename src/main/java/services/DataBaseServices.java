package services;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DataBaseServices {
    public static Logger logger = LoggerFactory.getLogger(DataBaseServices.class);

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PSW = "314159";

    Connection connection = null;
    Statement statement = null;

    public DataBaseServices() {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PSW);
        } catch (ClassNotFoundException | SQLException e) {
            logger.info(e.toString());
        }

        if (connection != null){
            logger.info("Соединение установлено успешно...");
        }else {
            logger.info("Что то пошло не так...");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        try{
            if (statement == null){
                statement = getConnection().createStatement();
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return statement;
    }

    public void executeSQL(String sql){

        try{
            logger.info("Результат выполнения запроса..." + getStatement().execute(sql));
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public ResultSet executeQuery(String sql){

        try{
            return getStatement().executeQuery(sql);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return null;
    }
    public void closeConnection(){

        try {
            connection.close();
            logger.info("Соединение закрыто...");
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
