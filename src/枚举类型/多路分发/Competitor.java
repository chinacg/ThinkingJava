package 枚举类型.多路分发;

/**
 * Created by wulei on 16/3/30.
 * 定义一种类型,该类型的对象可以与另一个Competitor相竞争,
 * 返回结果枚举类型
 */
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
