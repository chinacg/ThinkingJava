package 新IO.对象序列化.使用持久性.static字段的序列化问题;

/**
 * Created by wulei on 16/3/21.
 */
public class Circle extends Shape {
    private static int color = RED;

    public Circle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}
