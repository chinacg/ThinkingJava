package holdObjects.objects;

/**
 * Created by wulei on 16/1/14.
 */
public class Pet {

    public Pet(int id) {
        this.id = id;
        this.name="none";
    }
    public Pet(int id,String name){
        this.id=id;
        this.name=name;
    }
    @Override
    public String toString() {
        return this.getId()+":"+this.getName();
    }


    private int id;
    private String name;
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public static void main(String args[]) {
        System.out.println(new Pet(1,"jojo"));
    }
}
