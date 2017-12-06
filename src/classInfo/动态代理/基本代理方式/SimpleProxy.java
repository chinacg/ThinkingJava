package classInfo.动态代理.基本代理方式;

/**
 * Created by wulei on 16/2/2.
 */
public class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("SimpleProxy somethingElse " + args);
        proxied.somethingElse(args);
    }
}
