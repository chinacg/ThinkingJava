package 泛型.泛型应用_堆栈;

/**
 * Created by wulei on 16/2/15.
 * 用泛型模拟传统的堆栈结构
 */
public class LinkedStack<T> {
    private class Node<U>{
        U item;
        Node<U> next;
        Node(){item=null;next=null;}
        Node(U item,Node<U> next){
            this.item=item;
            this.next=next;
        }
        boolean end(){return item==null&&next==null;}
    }
    private Node<T> top=new Node<T>();//末端哨兵(end sentinel)
    public void push(T item){
        top=new Node<T>(item,top);
    }
    public T pop(){
        T result=top.item;
        if(!top.end())
            top=top.next;
        return result;
    }
    public static void main(String[] args){
        LinkedStack<String> lss=new LinkedStack<String>();
        for(String s:"Phasers or stun!".split(" "))
            lss.push(s);
        String s;
        while ((s=lss.pop())!=null)
            System.out.println(s);
    }
}
