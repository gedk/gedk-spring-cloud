package com.gedk.code.generator;


import java.io.*;
import java.util.*;

/**
 * Desc
 *
 * @author gedekun Email:527552959@qq.com
 * @since 2020/5/2 16:20
 */
public class Main {
    private final static String CHARSET = "UTF-8";

    private final static String SEPARATOR_STR = "#";

    private static void saveFile(String content,File file) throws Exception {
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,CHARSET);
        outputStreamWriter.write(content);
        outputStreamWriter.close();
    }

    private static String underLine2CamelCase(String str){
        String data = underLine2ClassName(str);
        return data.substring(0,1).toLowerCase()+data.substring(1);
    }

    private static String underLine2ClassName(String str){
        String [] array = str.split("_");
        String data = "";
        for (String item:array){
            data += item.substring(0,1) + item.toLowerCase().substring(1);
        }
        return data;
    }

    private static String getValue(String [] data,int index){
        try {
            return data[index];
        }catch (Exception e){
            return "";
        }
    }

    private static List<String> readFile(File file) throws IOException {
        List<String> data = new ArrayList<>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file),CHARSET));
        BufferedReader bufferedReader = new BufferedReader(in);
        String line;
        while((line = bufferedReader.readLine()) != null){
            data.add(line);
        }
        return data;
    }

    private static String getJavaType(String dbType){
        if(dbType.startsWith("VARCHAR")){
            return "String";
        }
        if(dbType.startsWith("DATE")){
            return "Date";
        }
        if(dbType.startsWith("NUMBER(")){
            return "Double";
        }
        if(dbType.equals("NUMBER")){
            return "Integer";
        }
        return "String";
    }

    private static void genPo(String dir,List<String> data) throws Exception {
        String className = "";
        String classDesc = "";
        String item;
        List<String> codeMetaData = new ArrayList<>();
        for (int i = 5 ; i < data.size() ;i++) {
            item = data.get(i);
            if(i == 5){
                className =  underLine2ClassName(getValue(item.split(SEPARATOR_STR),0));
                classDesc = getValue(item.split(SEPARATOR_STR),1);
                continue;
            }
            codeMetaData.add(item);
        }
        String poDirPath = dir + "/po";
        File poDir = new File(poDirPath);
        if(!poDir.exists()){
            poDir.mkdirs();
        }
        String classFileName = className+"PO.java";
        File classFile = new File(poDir,classFileName);
        if(!classFile.exists()){
            classFile.createNewFile();
        }
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/**\n" +
                " * "+classDesc+"\n" +
                " * \n" +
                " */\n");
        stringBuilder.append("public class "+className+"PO {\n");
        int index = 0;
        for(String code:codeMetaData){
            if(index > 0){
                stringBuilder.append("\n");
            }
            String type = getJavaType(getValue(code.split(SEPARATOR_STR),1));
            String fieldName = underLine2CamelCase(getValue(code.split(SEPARATOR_STR),0));
            stringBuilder.append("\t/**\n" +
                    "\t * "+getValue(code.split(SEPARATOR_STR),2)+"\n" +
                    "\t */\n");
            stringBuilder.append("\tprivate "+type+" "+fieldName+";\n");
            index ++;
        }
        stringBuilder.append("}");
        saveFile(stringBuilder.toString(),classFile);
    }

    private static void genSql(String dir,List<String> data) throws Exception {
        String sqlName = "";
        String tableDesc = "";
        String item = data.get(5);
        sqlName =  getValue(item.split(SEPARATOR_STR),0);
        tableDesc = getValue(item.split(SEPARATOR_STR),1);
        String sqlDirPath = dir + "/sql";
        File sqlDir = new File(sqlDirPath);
        if(!sqlDir.exists()){
            sqlDir.mkdirs();
        }
        String sqlFileName = sqlName+".sql";
        File file = new File(sqlDir,sqlFileName);
        StringBuilder stringBuilder = new StringBuilder("");
        for(int i = 0 ; i < data.size();i++){
            if(i >= 5){
                break;
            }
            stringBuilder.append(data.get(i)+"\n");
        }
        stringBuilder.append("declare\n");
        stringBuilder.append("\tnCount NUMBER;\n");
        stringBuilder.append("\tv_sql LONG;\n");
        stringBuilder.append("\n");
        stringBuilder.append("begin\n");
        stringBuilder.append("\tSELECT count(*) into nCount FROM dba_tables where table_name = '"+sqlName+"';\n");
        stringBuilder.append("\tIF(nCount > 0)\n");
        stringBuilder.append("\tTHEN\n");
        stringBuilder.append("\t\tv_sql:='drop table "+sqlName+"';\n");
        stringBuilder.append("\t\texecute immediate v_sql;\n");
        stringBuilder.append("\tEND IF;\n");
        stringBuilder.append("end;\n");
        stringBuilder.append("/\n");
        stringBuilder.append("\n");
        stringBuilder.append("CREATE TABLE "+sqlName+" (\n");
        for(int i = 6 ; i < data.size();i++){
            String str = data.get(i);
            if(i == data.size() -1) {
                stringBuilder.append("\t" + getValue(str.split(SEPARATOR_STR), 0) + " " + getValue(str.split(SEPARATOR_STR), 1) + "\n");
            } else {
                stringBuilder.append("\t" + getValue(str.split(SEPARATOR_STR), 0) + " " + getValue(str.split(SEPARATOR_STR), 1) + ",\n");
            }
        }
        stringBuilder.append(");\n");
        for(int i = 6 ; i < data.size();i++){
            String str = data.get(i);
            String colName = getValue(str.split(SEPARATOR_STR),0);
            String desc = getValue(str.split(SEPARATOR_STR),2);
            stringBuilder.append("COMMENT ON COLUMN "+sqlName+"."+colName+" IS '"+desc+"';\n");
        }
        stringBuilder.append("COMMENT ON TABLE CRM_CUSTOMER IS '"+tableDesc+"';\n");
        stringBuilder.append("/\n");
        saveFile(stringBuilder.toString(),file);
    }

    private static void genVO(String dir,List<String> data) throws Exception {
        String className = "";
        String classDesc = "";
        String item;
        List<String> codeMetaData = new ArrayList<>();
        for (int i = 5 ; i < data.size() ;i++) {
            item = data.get(i);
            if(i == 5){
                className =  underLine2ClassName(getValue(item.split(SEPARATOR_STR),0));
                classDesc = getValue(item.split(SEPARATOR_STR),1);
                continue;
            }
            codeMetaData.add(item);
        }
        String poDirPath = dir + "/vo";
        File poDir = new File(poDirPath);
        if(!poDir.exists()){
            poDir.mkdirs();
        }
        String classFileName = className+"VO.java";
        File classFile = new File(poDir,classFileName);
        if(!classFile.exists()){
            classFile.createNewFile();
        }
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("/**\n" +
                " * "+classDesc+"\n" +
                " * \n" +
                " */\n");
        stringBuilder.append("@ApiModel(\""+classDesc+"Model\")\n");
        stringBuilder.append("public class "+className+"VO {\n");
        int index = 0;
        for(String code:codeMetaData){
            if(index > 0){
                stringBuilder.append("\n");
            }
            String type = getJavaType(getValue(code.split(SEPARATOR_STR),1));
            String fieldName = underLine2CamelCase(getValue(code.split(SEPARATOR_STR),0));
            String defValue = getValue(code.split(SEPARATOR_STR),3);
            stringBuilder.append("\t@ApiModelProperty(name = \""+fieldName+"\",value = \""+getValue(code.split(SEPARATOR_STR),2)+"\",example = \""+defValue+"\")\n");
            stringBuilder.append("\tprivate "+type+" "+fieldName+";\n");
            index ++;
        }
        stringBuilder.append("}");
        saveFile(stringBuilder.toString(),classFile);
    }

    private static void genDoc(String dirPath,List<String> data){

    }

    private static void genSqlSegment(String dirPath,List<String> data) throws Exception {
        String item = data.get(5);
        String tableName =  getValue(item.split(SEPARATOR_STR),0);
        String sqlDirPath = dirPath + "/sql-segment";
        File sqlDir = new File(sqlDirPath);
        if(!sqlDir.exists()){
            sqlDir.mkdirs();
        }
        String fileName = tableName+".segment";
        File sqlSegment = new File(sqlDirPath,fileName);
        String code;
        String fieldNames = "";
        String fieldName;
        String values = "";
        String value;
        String updateFieldNames = "";
        for (int i = 6 ; i < data.size();i++){
            code = data.get(i);
            //插入字段
            if(fieldNames.length() > 0){
                fieldNames += ",\n";
            }
            fieldName = getValue(code.split(SEPARATOR_STR),0);
            fieldNames += "\t"+fieldName;
            if(values.length() > 0){
                values += ",\n";
            }
            value = "#{"+underLine2CamelCase(getValue(code.split(SEPARATOR_STR),0))+",jdbcType = "+getValue(getValue(code.split(SEPARATOR_STR),1).split("\\("),0).replaceAll("\\d","")+"}";
            values += "\t"+value;
            //更新字段
            if(updateFieldNames.length() > 0){
                updateFieldNames += ",\n";
            }
            updateFieldNames += "\t"+fieldName + " = " + value;
        }
        String insertSql = "insert into "+tableName+"(\n"+fieldNames+"\n) values(\n"+values+"\n)";
        String updateSql = "update "+tableName+" set \n"+updateFieldNames+"\n where 1=1";
        saveFile(insertSql +"\n\n"+ updateSql,sqlSegment);
    }

    public static void main(String[] args) throws Exception {
        if(args == null || args.length <= 0){
            System.out.println("参数错误");
            return;
        }
        String dirPath = getValue(args,0);
        System.out.println("目录："+dirPath);
        File dir = new File(dirPath);
        if (!dir.exists()){
            System.out.println("目录不存在");
            return;
        }
        if(!dir.isDirectory()){
            System.out.println("目录错误");
           return;
        }
        File[] files = dir.listFiles();
        if(files == null || files.length <= 0){
            System.out.println("未发现“.d”文件");
           return;
        }
        for(File file:files){
            if(file.isDirectory()){
                continue;
            }
            String fileName = file.getName();
            if(!fileName.endsWith(".d")){
                continue;
            }
            File dFile = new File(dirPath,fileName);
            List<String> data = readFile(dFile);
            if(data.size() <= 0){
                continue;
            }
            genPo(dirPath,data);
            genSql(dirPath,data);
            genVO(dirPath,data);
            genDoc(dirPath,data);
            genSqlSegment(dirPath,data);
        }
    }
}
