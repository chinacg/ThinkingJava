package 新IO.ByteBuffer应用.操纵数据;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by wulei on 16/3/10.
 * 交换相邻字符
 */
public class UsingBuffers {
    private static void symmtricScramble(CharBuffer buffer){
        while(buffer.hasRemaining()){
            buffer.mark();
            char c1=buffer.get();
            char c2=buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }
    public static void main(String[] args){
        char[] data="UsingBuffers".toCharArray();
        ByteBuffer bb=ByteBuffer.allocate(data.length*2);
        CharBuffer cb=bb.asCharBuffer();//创建ByteBuffer的一个视图,实际操作仍然是ByteBuffer
        cb.put(data);
        System.out.println(cb.rewind());//rewind()操作重置指针,用来指向原始数据,此时输出原始字符顺序
        symmtricScramble(cb);
        System.out.println(cb.rewind());
        symmtricScramble(cb);
        System.out.println(cb.rewind());
    }/* Output:
    UsingBuffers
    sUniBgfuefsr
    UsingBuffers
    *///
    /*
      ①进入symmetricScramble方法时缓冲器的样子:

                                                       cap(容量)
                                                          ↓
            | U | s | i | n | g | B | u | f | f | e | r | s |
              ↑                                           ↑
             pos(当前位置)                                lim(界限)


       position指针指向缓冲器第一个元素,capacity和limit指向最后一个元素.

       在程序的symmetricScramble（)方法中,迭代执行while循环直到position等于limit.
       一旦调用缓冲器上相对的get()和put()函数,position指针就会随之改变.

       ②当操纵到while循环时,使用mark()调用来设置mark的值.此时,缓冲器状态如下:


             mar(标记)                                    cap
              ↓                                           ↓
            | U | s | i | n | g | B | u | f | f | e | r | s |
              ↑                                           ↑
             pos                                         lim


        ③两个相对的get()调用把前两个字符保存到变量c1和c2中,调用完这两个方法后,缓冲器如下

             mar                                         cap
              ↓                                           ↓
            | U | s | i | n | g | B | u | f | f | e | r | s |
                      ↑                                   ↑
                     pos                                 lim

       ④为了实现交换,要在position=0时写入c2,position=1时写入c1.使用reset()把position的值
         设为mark的值:

             mar                                          cap
              ↓                                           ↓
            | U | s | i | n | g | B | u | f | f | e | r | s |
              ↑                                           ↑
             pos                                         lim

       ⑤这两个put方法先写c2接着写c1:

             mar                                         cap
              ↓                                           ↓
            | s | U | i | n | g | B | u | f | f | e | r | s |
                      ↑                                   ↑
                     pos                                 lim

        ⑥下一次循环迭代期间,将mark设置成position的当前值:

                     mar                                 cap
                      ↓                                   ↓
            | s | U | i | n | g | B | u | f | f | e | r | s |
                      ↑                                   ↑
                     pos                                 lim

        这个过程会持续到遍历完整个缓冲器,在循环的最后,position指向缓冲器的末尾.
        如果打印缓冲器,只能打印出position和limit之间的字符.如果想显示缓冲器内的全部内容,
        必须使用rewind()把position设置到缓冲器的开始位置:

                                                         cap(容量)
                                                          ↓
            | s | U | n | i | B | g | f | u | e | f | s | r |
              ↑                                           ↑
             pos(当前位置)                                lim(界限)


     */

}
