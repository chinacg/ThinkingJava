package holdObjects.objects;

import util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wulei on 16/1/14.
 */
public class Pets {
    private List<Pet> pets;

    public Pets(List<Pet> pets) {
        this.pets = pets;
    }

    public static List<Pet> arrayList(final int num) {
        int numPets = num;
        List<Pet> pets = new ArrayList<Pet>();
        while (numPets > 0) {
            Pet pet = new Pet(numPets, RandomUtil.randomStr(4));
            pets.add(pet);
            numPets--;
        }
        return pets;
    }

    public static Pet randomPet(){
        return new Pet(RandomUtil.randomInt(100,200),RandomUtil.randomStr(4));
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(Pet pet:pets){
            sb.append(pet.getId()+":"+pet.getName());
        }
        return sb.toString();
    }
}
