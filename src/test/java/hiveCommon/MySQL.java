package hiveCommon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQL {

    Logger logger = LogManager.getLogger("Base");
    Connection connection;
    Statement statement;
    ResultSet resultSet;


    public MySQL()
    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=minty&password=greatsqldb");
            statement = connection.createStatement();

        }catch (Exception e)
        {
            logger.error("Cannot connect to DB");
        }
    }

    public ResultSet ExecuteQuery(String query)
    {
        try {
            resultSet = statement.executeQuery(query);
            return resultSet;
        }catch (Exception e)
        {
            logger.error("Cannot execute this query");
        }
        return null;
    }

}
