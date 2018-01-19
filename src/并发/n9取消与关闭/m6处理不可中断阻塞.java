package 并发.n9取消与关闭;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * java库中，许多可阻塞方法都是通过提前返回或者抛出InterruptedException来响应
 * 中断请求的，这样容易构建出能响应取消请求的任务。然而，并非所有的可阻塞方法
 * 或者阻塞机制都能响应中断；如果一个县城由于执行同步的Socket I/O 或者等待获得内置
 * 锁而阻塞，那么中断请求只能设置线程的中断状态，除此之外没有其他任何作用。对于那些
 * 由于执行不可中断操作而被阻塞的线程，可以使用类似于中断的手段来停止这些线程，但
 * 这要求我们必须知道线程阻塞的原因。
 *
 * Java.io 包中的同步Socket I/O. 在服务器应用程序中，最常见的阻塞I/O 形式就是对套接字
 * 进行读取和写入。虽然InputStream 和OutputStream中的read和write等方法都不会响应中断，
 * 但通过关闭底层的套接字，可以使得由于执行read 或 write等方法而被阻塞的线程抛出一个
 * SocketException
 *
 * Java.io包中的同步I/O 。当中断一个正在InterruptibleChannel 上等待的线程时，将抛出
 * ClosedByInterruptException 并关闭链路（这使在这条链路上阻塞的其它线程同样抛出ClosedByInterruptException）
 * 大多数标准的Channel 都实现了InterruptibleChannel.
 *
 * Selector 的异步I/O.如果一个线程在调用 Selector.select方法（java.nio.channels)时阻塞了，
 * 那么调用close或wakeup方法回事线程抛出ClosedSelectorException并提前返回。
 *
 * 获取某个锁。如果一个线程由于等待某个内置锁而阻塞，那么将无法响应中断，因为线程认为它肯定
 * 会获得锁，所以不会理会中断请求。但是，在Lock类中提供了lockInterruptibly方法，该方法允许在等待一个
 * 锁的同时仍能响应中断。
 */
public class m6处理不可中断阻塞 {
    /**
     * ReaderThread 给出了如果封装非标准的取消操作。ReaderThread管理了一个套接字链接，
     * 它采取同步的方式从该套接字中读取数据，并将接收到的数据传给processsBuffer。为了结束
     * 某个用户的连接或者关闭服务器，ReaderThread改写了interrupt方法，使其技能处理标准的
     * 中断，也能关闭底层的套接字。因此无论ReaderThread线程是在read方法中阻塞还是在某个
     * 可中断的阻塞方法中阻塞，都可以被中断并停止执行当前的工作。
     */
    public static class ReaderThread extends Thread{
        private final Socket socket;
        private final InputStream in;

        public ReaderThread(Socket socket) throws IOException{
            this.socket = socket;
            this.in = socket.getInputStream();
        }
        @Override
        public void run() {
         try {
             byte[] buf = new byte[1024];
             while (true){
                 int count = in.read(buf);
                 if(count<0)
                     break;
                 else if(count>0) {
                     //processBuffer(buf,count);
                 }
             }
         }catch (IOException e){/*允许线程退出*/}
        }

        @Override
        public void interrupt() {
            try {
                 socket.close();
            }catch (IOException ignored){}
            finally {
                super.interrupt();
            }
        }
    }

}
