package 新IO.ByteBuffer应用.内存映射文件;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wulei on 16/3/10.
 *
 * 内存映射文件的使用例子
 *
 * 内存映射文件允许我们创建和修改哪些因为太大不能放入内存的文件
 * 有了内存映射文件,我们就可以假定整个文件在内存中.
 */
public class LargeMappedFiles {
    static int length=0x8FFFFFF;//128MB
    public static void main(String[] args) throws Exception{
        MappedByteBuffer out=//直接缓冲器
                new RandomAccessFile("text.dat","rw").getChannel()
                .map(FileChannel.MapMode.READ_WRITE,0,length);
        for(int i=0;i<length;i++)
            out.put((byte)'x');
        System.out.println("Finished writing");
        for(int i=length/2;i<length/2+6;i++)
            System.out.println((char) out.get(i));
    }
}
