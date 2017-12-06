package 新IO.对象序列化.使用持久性;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wulei on 16/3/21.
 *
 * 将同一个对象写入不同的流当中就会产生完全不同的对象网
 *
 * tips:如果我们想保存系统状态,最安全的做法是将其作为"原子"
 * 操作进行序列化.如果我们序列化了某些东西,再去做其它一些工作,再来序列化
 * 更多的东西,如此等等,那么降无法安全地保存系统状态.取而代之的是,将构成系统
 * 状态的所有对象都置于单一容器内,并在一个操作中将该容器直接写出,然后同样只需
 * 一次方法调用,即可以将其恢复
 */
public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        System.out.println("animals:" + animals);

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);//写入一个 list
        o1.writeObject(animals);//用同一个输出流第二次写入

        //用不同的输出流写入相同的对象
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        //恢复他们
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List
                animals1 = (List) in1.readObject(),
                animals2 = (List) in1.readObject(),
                animals3 = (List) in2.readObject();
        System.out.println("animals1:"+animals1);//animals对象的引用被写入到相同的流中,当恢复它们后,对象地址是相同的,
        System.out.println("animals2:"+animals2);//而且这两个对象共享的指向 House 对象的引用的地址也是相同的.

        System.out.println("animals3:"+animals3);//恢复 aninmals3时,因为使用的是不同的流进行的序列化,系统并不知道这两个
                                                    //流使用的是同一个对象的不同别名,所以会产生不同的对象网.所以对象地址和其拥有的
                                                    //House 对象的地址和 animals1与 animals2不同
    }
}/*output:


animals:[Bosco the dog[新IO.对象序列化.使用持久性.Animal@52ab7af2], 新IO.对象序列化.使用持久性.House@7814d044
, Ralph the hamster[新IO.对象序列化.使用持久性.Animal@1e755df3], 新IO.对象序列化.使用持久性.House@7814d044
, Molly the cat[新IO.对象序列化.使用持久性.Animal@2b6b0c24], 新IO.对象序列化.使用持久性.House@7814d044
]
animals1:[Bosco the dog[新IO.对象序列化.使用持久性.Animal@23bffd0d], 新IO.对象序列化.使用持久性.House@117e72d1
, Ralph the hamster[新IO.对象序列化.使用持久性.Animal@4a40050], 新IO.对象序列化.使用持久性.House@117e72d1
, Molly the cat[新IO.对象序列化.使用持久性.Animal@2b108691], 新IO.对象序列化.使用持久性.House@117e72d1
]
animals2:[Bosco the dog[新IO.对象序列化.使用持久性.Animal@23bffd0d], 新IO.对象序列化.使用持久性.House@117e72d1
, Ralph the hamster[新IO.对象序列化.使用持久性.Animal@4a40050], 新IO.对象序列化.使用持久性.House@117e72d1
, Molly the cat[新IO.对象序列化.使用持久性.Animal@2b108691], 新IO.对象序列化.使用持久性.House@117e72d1
]
animals3:[Bosco the dog[新IO.对象序列化.使用持久性.Animal@49f2afad], 新IO.对象序列化.使用持久性.House@5bebacc8
, Ralph the hamster[新IO.对象序列化.使用持久性.Animal@4d9cad9d], 新IO.对象序列化.使用持久性.House@5bebacc8
, Molly the cat[新IO.对象序列化.使用持久性.Animal@694a4639], 新IO.对象序列化.使用持久性.House@5bebacc8
]
*/
