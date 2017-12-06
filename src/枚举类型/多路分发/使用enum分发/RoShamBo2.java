package 枚举类型.多路分发.使用enum分发;

import 枚举类型.多路分发.Competitor;
import 枚举类型.多路分发.Outcome;
import 枚举类型.多路分发.RoShamBo;

import static 枚举类型.多路分发.Outcome.*;

/**
 * Created by wulei on 16/3/30.
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {
    //枚举定义好所有的分发情况来准备分发
    PAPER(DRAW,LOSE,WIN),
    SCISSORS(WIN,DRAW,LOSE),
    ROCK(LOSE,WIN,DRAW);

    private Outcome vPAPER,vSCISSORS,vROCK;//一共三种比对结果
    RoShamBo2(Outcome paper,Outcome scissors,Outcome rock){
        this.vPAPER=paper;
        this.vROCK=rock;
        this.vSCISSORS=scissors;
    }
    @Override
    public Outcome compete(RoShamBo2 it) {
        switch (it){
            default:
            case PAPER:return vPAPER;//返回比对对象是paper的结果
            case SCISSORS:return vSCISSORS;//返回比对对象是scissors的结果
            case ROCK:return vROCK;//返回比对对象是rock的结果
        }
    }
    public static void main(String[] args){
        RoShamBo.play(RoShamBo2.class,20);
    }
}
