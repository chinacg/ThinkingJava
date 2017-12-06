package classInfo.泛化的Class引用;

import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过泛型类语法,存储一个类的引用并用它实例化产生一个List;
 * Created by wulei on 16/1/31.
 */
public class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type){this.type=type;}
    public List<T> create(int nElements){
        List<T> result=new ArrayList<T>();
        try{
            for(int i=0;i< nElements;i++)
                result.add(type.newInstance());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }
    public static void main(String[] args){
        FilledList<CountedInteger> fl=
                new FilledList<CountedInteger>(CountedInteger.class);
        System.out.println(fl.create(15));
    }
}
