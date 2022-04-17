package hiveCommon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;


public class KeyValReader {
    public static final Logger logger = LogManager.getLogger();

    Properties properties;

    public KeyValReader(String path)
    {
        properties = new Properties();
        try{

            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            properties.load(bufferedReader);
            bufferedReader.close();
            logger.info("Successfully loaded file");

        }catch (Exception e)
        {
            logger.error("Failed to find path of file");
        }

    }

    public String getProperty(String var)
    {
        try{
            logger.debug("trying to grab property from file...");
            return properties.getProperty(var);

        }catch (Exception e)
        {
            logger.error("Cant find property");
            return null;
        }
    }


}
