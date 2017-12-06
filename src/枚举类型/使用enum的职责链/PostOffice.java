package 枚举类型.使用enum的职责链;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/29.
 * 职责链由enum MailHandler实现,而enum定义的次序决定了各个解决
 * 策略在应用时的次序.对每一封邮件,都要按此顺序尝试每个解决策略,
 * 直到其中一个能够成功地处理该邮件,如果所有策略都失败了,那么该邮件
 * 将被判定为一封死信.
 */
public class PostOffice {
    enum MailHandler{
        GENERAL_DELIVERY {
            @Override
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery for"+m);
                        return true;
                    default:
                        return false;
                }
            }
        },MACHINE_SCAN {
            @Override
            boolean handle(Mail m) {
                switch (m.scannability){
                    case UNSCANNABLE:return false;
                    default:
                        switch (m.address){
                            case INCRRECT:return false;
                            default:
                                print("Delivering "+m+" automatically");
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail m) {
                switch (m.readability){
                    case ILLEGIBLE:return false;
                    default:
                        switch (m.address){
                            case INCRRECT:return false;
                            default:
                                print("Delivering "+m+" normally");
                                return true;
                        }
                }
            }
        },RETURN_TO_SENDER {
            @Override
            boolean handle(Mail m) {
                switch (m.returnAddress){
                    case MISSING:return false;
                    default:
                        print("Returning "+m+" to sender");
                        return true;
                }
            }
        };
        abstract boolean handle(Mail m);
    }
    static void handle(Mail m){
        for(MailHandler handler:MailHandler.values())
            if(handler.handle(m))
                return;
        print(m+" is a dead letter");
    }
    public static void main(String[] args){
        for(Mail mail:Mail.generator(10)){
            print(mail.details());
            handle(mail);
            print("********");
        }

    }
}
