package parseConfig;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * Created by huangmao on 2017/1/12.
 */
public class ParseDBConfig {
    private String driver;
    private String url;
    private String user;
    private String password;
    private String tableName;
    private Logger logger = Logger.getRootLogger();
    private static ParseDBConfig singleton=new ParseDBConfig();
    private ParseDBConfig(){};//构造函数私有化
    public static ParseDBConfig getInstantant(){
        return singleton;
    }
    public String getDriver(){
        return driver;
    }
    public String getUrl(){
        return url;
    }
    public String getUser(){
        return user;
    }
    public String getPassword(){
        return password;
    }
    public String getTableName(){
        return tableName;
    }
    public void parse(String configPath) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(configPath));
            Element root = document.getRootElement();
            driver=root.elementText("driver").trim();
            url=root.elementText("url").trim();
            user=root.elementText("user").trim();
            password=root.elementText("password").trim();
            tableName=root.elementText("table").trim();
        } catch (Exception e) {
            logger.error("exception occurs when parse config",e);
        }
    }
}
