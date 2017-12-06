package 注解.基本使用;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wulei on 16/3/31.
 * 定义一个用于跟踪一个项目中的用例的注解
 */
@Target(ElementType.METHOD)//运用于方法
@Retention(RetentionPolicy.RUNTIME)//注解在运行时可用
public @interface UseCase {
    /**
     * 定义id
     * @return
     */
    public int id();

    /**
     * 定义解释内容
     * @return
     */
    public String description() default "no description";
}
