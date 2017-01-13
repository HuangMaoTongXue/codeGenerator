package runTest;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import parseConfig.ParseFileConfig;
import printToFile.PrintToFile;

import java.util.Map;

/**
 * Created by huangmao on 2017/1/12.
 */
public class GenerateByXML {
    public static void main(String[] args) {
        DOMConfigurator.configure("./config/log4j.xml");//配置log4j
        Logger logger = Logger.getRootLogger();
        try {
          Map<String,String> columnInf= ParseFileConfig.parse("./config/fileConfig.xml");
            if (columnInf==null){
                logger.error("column information is null,check fileConfig.xml");
                return;
            }
            PrintToFile.printInterfFile(columnInf,ParseFileConfig.getClassName(),"./entity");
            PrintToFile.printImplFile(columnInf,ParseFileConfig.getClassName(),"./entity");
        }catch (Exception e){
            logger.error("Exception occurs when generate code by xml file",e);
        }
    }
}
