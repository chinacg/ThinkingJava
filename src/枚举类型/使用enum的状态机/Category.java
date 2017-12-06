package 枚举类型.使用enum的状态机;

import java.util.EnumMap;

import static 枚举类型.使用enum的状态机.Input.*;

/**
 * Created by wulei on 16/3/29.
 * input的分类枚举
 */
public enum Category {
    MONEY(NICKEL,DIME,QUARTER,DOLLAR),
    ITEM_SELECTION(TOOTHPASTE,SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);
    private Input[] values;
    Category(Input... types){values=types;}
    private static EnumMap<Input,Category> categories=
            new EnumMap<Input, Category>(Input.class);
    static {
        for(Category c:Category.class.getEnumConstants())
            for(Input type:c.values)
                categories.put(type,c);
    }
    public static Category categorize(Input input){
        return categories.get(input);
    }
}
