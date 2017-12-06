package 新IO.ByteBuffer应用.通道分类;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wulei on 16/3/9.
 * ByteBuffer 操作 开启通道,一个读一个写
 */
public class ChannelCopy {
    private static final int BSIZE =1024;
    public static void main(String[] args) throws Exception{
        args=new String[]{"data.txt","data2.txt"};

        if(args.length>2){
            System.out.print("arguments:sourcefile destfiles");
            System.exit(1);
        }
        FileChannel
                in=new FileInputStream(args[0]).getChannel(),
                out=new FileOutputStream(args[1]).getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        while(in.read(buffer)!=-1){
            buffer.flip();//准备写入 position返回0位置,limit设置到position位置用来让别人读取缓存的字节
            out.write(buffer);
            System.out.println(buffer.position());
            buffer.clear();//清空缓冲区,将position设置为0,limit设置为容量
            System.out.println(buffer.position());
        }
    }
}
