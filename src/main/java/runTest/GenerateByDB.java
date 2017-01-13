package runTest;

import jdbc.OperateDB;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import parseConfig.ParseDBConfig;
import printToFile.PrintToFile;

import java.util.Map;

/**
 * Created by huangmao on 2017/1/12.
 */
public class GenerateByDB {
    public static void main(String[] args){
        DOMConfigurator.configure("./config/log4j.xml");//配置log4j
        Logger logger = Logger.getRootLogger();
        ParseDBConfig parseCon = ParseDBConfig.getInstantant();
        parseCon.parse("./config/dbConfig.xml");
        OperateDB operateDB = OperateDB.getInstance();
        operateDB.connet(parseCon.getDriver(), parseCon.getUrl(), parseCon.getUser(), parseCon.getPassword());
        Map<String, String> columnInf = operateDB.getMetaInf(parseCon.getTableName());
        if (columnInf==null){
            logger.info("the column information is null,check table:"+parseCon.getTableName());
            return;
        }
        PrintToFile.printInterfFile(columnInf,parseCon.getTableName(),"./entity");
        PrintToFile.printImplFile(columnInf,parseCon.getTableName(),"./entity");
    }
}
