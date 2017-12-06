package 新IO.ByteBuffer应用.通道分类;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by wulei on 16/3/9.
 * 让一个通道和领个通道直接相连
 */
public class TransferTo {
    public static void main(String[] args) throws Exception{
        args=new String[]{"data.txt","data2.txt"};
        if(args.length!=2){
            System.out.print("arguments:sourcefile destfiles");
            System.exit(1);
        }
        FileChannel
                in=new FileInputStream(args[0]).getChannel(),
                out=new FileOutputStream(args[1]).getChannel();
       // in.transferTo(0,in.size(),out);//transferTo允许通道直连
        out.transferFrom(in,0,in.size());
    }
}
