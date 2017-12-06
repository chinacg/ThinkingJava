package 枚举类型.使用enum的状态机;

import 泛型.泛型接口.Generator;

import static net.mindview.util.Print.print;

/**
 * Created by wulei on 16/3/29.
 * 自动售货机类
 */
public class VendingMachine {

    private static State state = State.RESTING;
    private static int amount = 0;
    private static Input selection = null;

    enum StateDuration {TRANSIENT}//瞬时枚举

    enum State {
        RESTING {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            print("Insufficient money for " + selection);
                        else state = DISPENDING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENDING(StateDuration.TRANSIENT) {
            void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next() {
                if (amount > 0) {
                    print("Your change:" + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            void output() {
                print("Halted");
            }
        };
        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("Only call " +
                    "next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
        static void run(Generator<Input> gen){//传入Input类型的泛型Gnerator类来进行状态的切换
            while(state!=State.TERMINAL){
                state.next(gen.next());
                while (state.isTransient)
                    state.next();
                state.output();
            }
        }
        public static void main(String[] args){
            Generator<Input> gen=new RandomInputGenerator();
            run(gen);


        }

    }
    //基本的检查工作
    static class RandomInputGenerator implements Generator<Input>{
        @Override
        public Input next() {
            return Input.randomSelection();
        }
    }

}
