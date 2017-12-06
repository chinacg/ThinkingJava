package 枚举类型.向enum添加新方法;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/24.
 * 为每个枚举实例添加自身的描述
 */
public enum OzWitch {
    //实例必须在方法声明之前被定义
    WEST("西方,西方国家"),
    NORTH("北方,北方的国家"),
    EAST("东方,东方国家"),
    SOUTH("南方");

    private String description;

    public String getDescription() {
        return description;
    }

    //构造器必须是包级或者私有的
    private OzWitch(String description){
        this.description=description;
    }
    public static void main(String[] args){
        for(OzWitch witch:OzWitch.values())
            print(witch+": "+witch.getDescription());
    }
}
