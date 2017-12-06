package 注解.生成外部文件;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/31.
 */
public class TableCreator {
    public static void main(String[] args) throws Exception{
        if(args.length<1){
            print("arguments:annotated classes");
            System.exit(0);
        }
        for(String className:args){
            Class<?> cl=Class.forName(className);
            DBTable dbTable=cl.getAnnotation(DBTable.class);
            if(dbTable==null){
                print("No DBTable annotattion in class "+className);
                continue;
            }
            String tablename=dbTable.name();
            //如果表名称是空,用类名的名称
            if(tablename.length()<1)
                tablename=cl.getName().toUpperCase();
            List<String> columDefs=new ArrayList<String>();
            for(Field field:cl.getDeclaredFields()){
                String columnName=null;
                Annotation[] anns=field.getDeclaredAnnotations();
                if(anns.length<1)
                    continue;//不是一个数据库表的列
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt=(SQLInteger)anns[0];
                    //使用域的名字如果name没有被定义
                    if(sInt.name().length()<1)
                        columnName=field.getName().toUpperCase();
                    else
                        columnName=sInt.name();
                    columDefs.add(columnName+" INT"+getConstraints(sInt.constraints()));
                }
                if(anns[0] instanceof SQLString){
                    SQLString sString=(SQLString)anns[0];
                    if(sString.name().length()<1)
                        columnName=field.getName().toUpperCase();
                    else columnName=sString.name();
                    columDefs.add(columnName+" VRCHAR("+sString.value()+")"+
                    getConstraints(sString.constraints()));
                }
                StringBuilder createCommand=new StringBuilder(
                        "CREATE TABLE "+tablename+"(");
                for(String colunmDef:columDefs)
                    createCommand.append("\n   "+colunmDef+",");
                String tableCreate=createCommand.substring(0,createCommand.length()-1)+")";
                print("Table Creation Sql for "+className+" is :\n"+tableCreate);
            }

        }
    }
    private static String getConstraints(Constraints con){
        String constraints="";
        if(!con.allowNull())
            constraints+=" NOT NULL";
        if(con.primaryKey())
            constraints+=" PRIMARY KEY";
        if(con.unique())
            constraints+=" UNIQUE";
        return constraints;

    }
}
