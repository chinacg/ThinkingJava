package 枚举类型.多路分发.使用EnumMap分发;

import 枚举类型.多路分发.Competitor;
import 枚举类型.多路分发.Outcome;
import 枚举类型.多路分发.RoShamBo;

import java.util.EnumMap;

import static 枚举类型.多路分发.Outcome.*;

/**
 * Created by wulei on 16/3/30.
 * 使用EnumMap能够实现"真正的"两路分发.
 * EnumMap是为enum专门设计的一种性能非常
 * 好的特殊Map.由于我们的目的是摸索出两种未知的
 * 类型,所以可以用一个EnumMap的EnumMap来实现
 * 两路分发:
 */
public enum RoShamBo5 implements Competitor<RoShamBo5> {
    PAPER,SCISSORS,ROCK;
    static EnumMap<RoShamBo5,EnumMap<RoShamBo5,Outcome>> table=new
            EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>(RoShamBo5.class);//创建一个键是RoShamBo5,值为EnumMap的空列表
    static{
        for(RoShamBo5 it:RoShamBo5.values())
            table.put(it,new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class));
        initRow(PAPER,DRAW,LOSE,WIN);
        initRow(SCISSORS,WIN,DRAW,LOSE);
        initRow(ROCK,LOSE,WIN,DRAW);
    }
    static void initRow(RoShamBo5 it,Outcome vPAPER,Outcome vSCISSORS,Outcome vROCK){
        EnumMap<RoShamBo5,Outcome> row=
                RoShamBo5.table.get(it);//根据table的键（比对源)取出值（比对结果）.
        row.put(RoShamBo5.PAPER,vPAPER);//设置比对结果的值,也是一个EnumMap
        row.put(RoShamBo5.SCISSORS,vSCISSORS);
        row.put(RoShamBo5.ROCK,vROCK);
    }
    @Override
    public Outcome compete(RoShamBo5 it) {
        return table.get(this).get(it);//取出表格对应的值
    }

    public static void main(String[] args){
        RoShamBo.play(RoShamBo5.class,20);
    }
}
