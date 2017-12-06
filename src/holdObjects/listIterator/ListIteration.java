package holdObjects.listIterator;

import holdObjects.objects.Pet;
import holdObjects.objects.Pets;

import java.util.*;

/**
 * Created by wulei on 16/1/14.
 * 迭代器ListIterator使用方法
 */
public class ListIteration {
    public static void main(String[] args){
        List<Pet> pets= Pets.arrayList(8);
        ListIterator<Pet> it=pets.listIterator();
        //正向迭代
        while (it.hasNext())
            System.out.print(it.next()+", "+it.nextIndex()+", "+
            it.previousIndex()+": ");
        System.out.println();
        //反向迭代回去
        while(it.hasPrevious())
            System.out.print(it.previous().getId()+" ");
        System.out.println();
        System.out.print(pets);
        while (it.hasNext()){
            it.next();
            it.set(Pets.randomPet());//设置当前pet
        }
        System.out.println(pets);

        List<Integer> listIntegers=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        ListIterator<Integer> iterator=listIntegers.listIterator();
        List<Integer> listIntegers2=new ArrayList<Integer>(listIntegers.size());

        while(iterator.hasNext())
            System.out.println(iterator.next());//正向迭代显示
        while(iterator.hasPrevious())
            listIntegers2.add(iterator.previous());//反向迭代赋值

        ListIterator<Integer> iterator2=listIntegers2.listIterator();
         System.out.println("--------------------------------------");
        while (iterator2.hasNext())
            System.out.println(iterator2.next());
    }
}
