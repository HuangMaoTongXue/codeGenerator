package runTest;

import jdbc.OperateDB;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import parseConfig.ParseDBConfig;

import java.util.Map;

/**
 * Created by huangmao on 2017/1/12.
 */
public class TestConfig {
    public static void main(String[] args){
        DOMConfigurator.configure("./config/log4j.xml");//配置log4j
        Logger logger = Logger.getRootLogger();
        try {
            ParseDBConfig parseCon = ParseDBConfig.getInstantant();
            parseCon.parse("./config/dbConfig.xml");
            OperateDB operateDB = OperateDB.getInstance();
            operateDB.connet(parseCon.getDriver(), parseCon.getUrl(), parseCon.getUser(), parseCon.getPassword());
            Map<String, String> columInf = operateDB.getMetaInf(parseCon.getTableName());
            if (columInf==null){
                logger.info("the column information is null,check table:"+parseCon.getTableName());
            }
            for (String columnName :
                    columInf.keySet()) {
                System.out.println(columnName + "-->" + columInf.get(columnName));
            }
        }catch (Exception e){
            logger.error("exception occurs in main()",e);
        }
    }
}
