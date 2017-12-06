package 枚举类型.使用enum的职责链;

import net.mindview.util.Enums;

import java.util.Iterator;

/**
 * Created by wulei on 16/3/28.
 * 邮件类,包含一个随机创建用于测试的邮件.而generator()方法
 * 生成一个Iterable对象,该对象在你调用next()方法时,在其内部使用
 * randomMail()来创建Mail对象.这样的结构使我们可以通过调用
 * Mail.generator()方法,很容易的构造出一个foreach循环
 */
public class Mail {
    //带NO的枚举实例降低了随机选取概率
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    enum Address {INCRRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id = counter++;


    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ",General Delivery:" + generalDelivery +
                ", Address Scanablility:" + scannability +
                ",Address Readability:" + readability +
                ",Address Address:" + address +
                ",Return address:" + returnAddress;
    }

    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;
            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
