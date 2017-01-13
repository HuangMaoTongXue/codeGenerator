package parseConfig;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangmao on 2017/1/12.
 */
public class ParseFileConfig {
    private static Logger logger = Logger.getRootLogger();
    private static String className;
    public static String getClassName(){
        return className;
    }
    public static Map<String,String> parse(String configPath) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(configPath));
            Element root = document.getRootElement();
            className=root.elementText("class");
            List<Element> variables=root.elements("var");
            Map<String,String> varInf=new HashMap<>();
            for (Element var :
                    variables) {
                String varName = var.elementText("name").trim();
                String varType =var.elementText("type").trim();
                varInf.put(varName,varType);
            }
            return varInf;
        } catch (Exception e) {
            logger.error("exceptions occurs when parse config");
            return null;
        }
    }

}
