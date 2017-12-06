package 新IO.ByteBuffer应用.通道分类;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wulei on 16/3/9.
 * 演示三种类型的流,用以产生可写的,可读可写的及可读的通道
 */
public class GetChannel {
    private static final int BSIZE=1024;
    public static void main(String[] args) throws Exception{
        //写文件
        FileChannel fc=new FileOutputStream("data.txt").getChannel();

        fc.write(ByteBuffer.wrap("Some text".getBytes()));//通过wrap方法把字节数据包装到ByteBuffer中
        fc.close();
        //在文件的末尾写入
        fc=new RandomAccessFile("data.txt","rw").getChannel();//可读可写操作
        fc.position(fc.size());//移动到末尾
        fc.write(ByteBuffer.wrap(" Some more".getBytes()));
        fc.close();
        //读取文件
        fc=new FileInputStream("data.txt").getChannel();
        ByteBuffer buff=ByteBuffer.allocate(BSIZE);//对于只读操作,必须显示地使用静态allocate()分配
        fc.read(buff);
        buff.flip();//缓存器调用flip,position返回初始位置,limit设置到position位置用来让别人读取缓存的字节
        while (buff.hasRemaining())
            System.out.print((char) buff.get());
    }
}
