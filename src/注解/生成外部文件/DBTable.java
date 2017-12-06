package 注解.生成外部文件;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wulei on 16/3/31.
 * 数据库表注解,通知注解处理器需要生成一个数据库表
 */
@Target(ElementType.TYPE)//注解只能应用于类型
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    public String name() default "";
}
