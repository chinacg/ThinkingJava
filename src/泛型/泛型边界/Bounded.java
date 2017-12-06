package 泛型.泛型边界;

import java.awt.*;

/**
 * Created by wulei on 16/2/17.
 */
public class Bounded extends Dimension implements HasColor,Weight {
    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }
}
