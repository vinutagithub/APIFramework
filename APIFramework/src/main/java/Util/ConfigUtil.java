package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    Properties prop = new Properties();

    public ConfigUtil(String fileName){
        try{
            prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/"+fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getproperty(String key){
        return prop.getProperty(key);
    }
}
