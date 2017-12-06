package 新IO.ByteBuffer应用.转换数据;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * Created by wulei on 16/3/10.
 * 缓冲器容纳的是普通的字节,为了把它们转换成字符,
 * 我们要么在输入他们的时候对其进行编码,要么在将其
 * 从缓冲器输出时对它们进行解码.可以使用java.nio.charset.Charset类
 * 实现这些功能,该类提供了把数据编码成多种不同类型的字符集的工具:
 */
public class AvailableCharSets {
    public static void main(String[] args){
        SortedMap<String,Charset> charSets=
                Charset.availableCharsets();
        Iterator<String> it=charSets.keySet().iterator();
        while(it.hasNext()){
            String csName=it.next();
            System.out.println(csName);
            Iterator aliases=
                    charSets.get(csName).aliases().iterator();
            if(aliases.hasNext())
                System.out.print(": ");
            while(aliases.hasNext()){
                System.out.print(aliases.next());
                if(aliases.hasNext())
                    System.out.print(", ");
            }
        }

    }
}
