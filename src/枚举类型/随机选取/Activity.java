package 枚举类型.随机选取;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/24.
 */
public enum Activity {
    SITTING,LYING,STANDING,HOPPING,RUNNING;
    public static void main(String[] args){
        for(int i=0;i<20;i++)
            print(Enums.random(Activity.class)+" ");
    }
}
