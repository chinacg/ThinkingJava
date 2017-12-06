package 枚举类型.使用EnumSet替代标志;

import java.util.EnumSet;

import static net.mindview.util.Print.print;
import static 枚举类型.使用EnumSet替代标志.AlarmPoints.*;

/**
 * Created by wulei on 16/3/28.
 * EnumSet 通过 enum 创建一种替代传统的
 * 基于 int 的"位标志".可以向其中添加删除对象,其中的对象
 * 是不重复的.
 */
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points =
                EnumSet.noneOf(AlarmPoints.class);//清空
        points.add(BATHROOM);
        print(points);
        points.addAll(EnumSet.of(STAIT1, STAIR2, KITCHEN));
        print(points);
        points = EnumSet.allOf(AlarmPoints.class);
        points.removeAll(EnumSet.of(STAIT1, STAIR2, KITCHEN));
        print(points);
        points = EnumSet.complementOf(points);
        print(points);
    }/*output:
    [BATHROOM]
[STAIT1, STAIR2, BATHROOM, KITCHEN]
[LOBBY, OFFICE1, OFFICE2, OFFICE3, OFFICE4, BATHROOM, UTILITY]
[STAIT1, STAIR2, KITCHEN]

    */
}
