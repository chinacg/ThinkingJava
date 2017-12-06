package 枚举类型.常量相关的方法;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Created by wulei on 16/3/28.
 * 枚举的实例可以覆盖常量的相关方法
 */
public enum  OverrideConstantSpecific {
    NUT,BOLT,
    WASHER{
        void f(){print("Overridden method");}
    };
    void f(){print("default behavior");}
    public static void main(String[] args){
        for(OverrideConstantSpecific ocs:values()){
            printnb(ocs+": ");
            ocs.f();
        }

    }
}/*NUT: default behavior
BOLT: default behavior
WASHER: Overridden method
*/
