package classInfo.动态代理.基本代理方式;

/**
 * Created by wulei on 16/2/2.
 */
public class RealObject implements Interface {
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("somethingElse "+args);

    }
}
