package 新IO.ByteBuffer应用.转换数据;

import java.nio.ByteBuffer;

/**
 * Created by wulei on 16/3/10.
 * 从ByteBuffer中产生各种不同基本类型
 */
public class GetData {
    private static final int BSIZE=1024;
    public static void main(String[] args){
        ByteBuffer bb=ByteBuffer.allocate(BSIZE);
        //创建缓冲器后,系统自动填充0到缓冲器
        int i=0;
        while(i++<bb.limit())
            if(bb.get()!=0)
                System.out.print("nonzero");
        System.out.println("i="+i);
        bb.rewind();
        //保存和读取一个字符数组
        bb.asCharBuffer().put("howdy!");
        char c;
        while((c=bb.getChar())!=0)
            System.out.print(c+" ");
        bb.rewind();
        System.out.println();
        //保存和读取一个short
        bb.asShortBuffer().put((short)471142);
        System.out.println(bb.position());
        System.out.println(bb.getShort());
        bb.rewind();
        //保存和读取一个整型数据
        bb.asIntBuffer().put(111111);
        System.out.println(bb.position());
        System.out.println(bb.getInt());
        bb.rewind();
        //保存和读取一个long
        bb.asLongBuffer().put(99999999);
        System.out.println(bb.getLong());
        bb.rewind();
        //保存和读取一个float
        bb.asFloatBuffer().put(99999999);
        System.out.println(bb.getFloat());
        bb.rewind();
        //保存和读取一个double
        bb.asDoubleBuffer().put(99999999);
        System.out.println(bb.getDouble());
        bb.rewind();

        /**
         * i=1025
         h o w d y !
         12390
         111111
         99999999
         1.0E8
         9.9999999E7
         */

    }
}
