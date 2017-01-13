package printToFile;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by huangmao on 2017/1/12.
 */
public class PrintToFile {
    private static Logger logger = Logger.getRootLogger();
    private static String lineSep = System.getProperty("line.separator");

    public static void printInterfFile(Map<String, String> columnInf, String tableName, String path) {
        FileOutputStream fileOut = null;
        PrintWriter printWriter = null;
        try {
            String interfName = "I"+toUpperCaseFirstOne(tableName);//拼接接口名称
            File javaClsFile = new File(path + "/" + interfName + ".java");//拼接路径
            if (javaClsFile.exists()) {//如果存在则删除
                if (!javaClsFile.delete()) {//删除失败
                    logger.error("can't delete the existed file:" + javaClsFile.toString());
                    return;
                }
            }
            if (!javaClsFile.createNewFile()) {//创建新文件
                logger.error("can't create the file:" + javaClsFile.toString());
                return;
            }
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("public interface " + interfName + " {" + lineSep);//打印接口定义
            //打印setter getter
            for (String cloumnName :
                    columnInf.keySet()) {
                String columnType_wholeN = columnInf.get(cloumnName);
                String columnType = columnType_wholeN.substring(columnType_wholeN.lastIndexOf(".") + 1);
                //打印getter
                strBuilder.append("public " + columnType + " get" + toUpperCaseFirstOne(cloumnName) + "();" + lineSep);//打印函数首行
                //打印setter
                strBuilder.append("public void set" + toUpperCaseFirstOne(cloumnName) + "(" + columnType + " " + cloumnName + ");" + lineSep);//打印函数首行
            }
            strBuilder.append("}" + lineSep);//打印接口结束括号
            fileOut = new FileOutputStream(javaClsFile);
            printWriter = new PrintWriter(fileOut, true);
            printWriter.write(strBuilder.toString().toCharArray());//StringBuffer->PrintWriter->FileOutputStream->java文件
            printWriter.flush();
        } catch (Exception e) {
            logger.info("exception occurs when print to file", e);
        } finally {
            try {
                if (printWriter != null)
                    printWriter.close();
                if (fileOut != null)
                    fileOut.close();
            } catch (Exception e) {
                logger.info("exception occurs when close PrintWriter or FileOutputStream", e);
            }
        }
    }

    public static void printImplFile(Map<String, String> columnInf, String tableName, String path) {
        FileOutputStream fileOut = null;
        PrintWriter printWriter = null;
        try {
            String clsName = toUpperCaseFirstOne(tableName);
            File javaClsFile = new File(path + "/" + clsName + ".java");//拼接路径
            if (javaClsFile.exists()) {//如果存在则删除
                if (!javaClsFile.delete()) {//删除失败
                    logger.error("can't delete the existed file:" + javaClsFile.toString());
                    return;
                }
            }
            if (!javaClsFile.createNewFile()) {//创建新文件
                logger.error("can't create the file:" + javaClsFile.toString());
                return;
            }
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("public class " + clsName + " {" + lineSep);//打印类定义
            //打印变量申明
            for (String cloumnName :
                    columnInf.keySet()) {
                String columnType_wholeN = columnInf.get(cloumnName);
                String columnType = columnType_wholeN.substring(columnType_wholeN.lastIndexOf(".") + 1);
                strBuilder.append("private " + columnType + " " + cloumnName + ";" + lineSep);
            }
            //打印setter getter
            for (String cloumnName :
                    columnInf.keySet()) {
                String columnType_wholeN = columnInf.get(cloumnName);
                String columnType = columnType_wholeN.substring(columnType_wholeN.lastIndexOf(".") + 1);
                //打印getter
                strBuilder.append("public " + columnType + " get" + toUpperCaseFirstOne(cloumnName) + "(){" + lineSep);//打印函数首行
                strBuilder.append("return this." + cloumnName + ";" + lineSep);
                strBuilder.append("}" + lineSep);//打印getter结束括号
                //打印setter
                strBuilder.append("public void set" + toUpperCaseFirstOne(cloumnName) + "(" + columnType + " " + cloumnName + "){" + lineSep);//打印函数首行
                strBuilder.append("this." + cloumnName + "=" + cloumnName + ";" + lineSep);
                strBuilder.append("}" + lineSep);//打印setter结束括号
            }
            strBuilder.append("}" + lineSep);//打印类结束括号
            fileOut = new FileOutputStream(javaClsFile);
            printWriter = new PrintWriter(fileOut, true);
            printWriter.write(strBuilder.toString().toCharArray());//StringBuffer->PrintWriter->FileOutputStream->java文件
            printWriter.flush();
        } catch (Exception e) {
            logger.info("exception occurs when print to file", e);
        } finally {
            try {
                if (printWriter != null)
                    printWriter.close();
                if (fileOut != null)
                    fileOut.close();
            } catch (Exception e) {
                logger.info("exception occurs when close PrintWriter or FileOutputStream", e);
            }
        }
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
