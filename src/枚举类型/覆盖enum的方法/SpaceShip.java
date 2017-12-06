package 枚举类型.覆盖enum的方法;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/24.
 * 覆写 toString()方法,将 SpaceShip 的名称修改未只有首字母大写
 */
public enum SpaceShip {
    SCOUT,CARGO,TRANSPOT,CRUISER,BATTLESHIP,MOTHERSHIP;
    public String toString(){
        String id=name();
        String lower=id.substring(1).toLowerCase();
        return id.charAt(0)+lower;
    }
    public static void main(String[] args){
        for(SpaceShip s:values()){
            print(s);
        }
    }
}
