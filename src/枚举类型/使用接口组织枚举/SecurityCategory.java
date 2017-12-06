package 枚举类型.使用接口组织枚举;

import 枚举类型.随机选取.Enums;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/25.
 * enum 嵌套在另一个 enum 内
 */
public enum SecurityCategory {
    STOCK(Security.Stock.class),BOND(Security.Bond.class);
    Security[] values;
    SecurityCategory(Class<? extends Security> kind) {
       values=kind.getEnumConstants();
    }
    interface Security {
        enum Stock implements Security {SHORT, LONG, MARGIN}

        enum Bond implements Security {MUNICIPAL, JUNK}
    }
    public Security randomSelection(){
        return Enums.random(values);
    }

    public static void main(String[] args){
        for(int i=0;i<10;i++){
            SecurityCategory category=
                    Enums.random(SecurityCategory.class);
            print(category+": "+category.randomSelection());
        }
    }
}
