package 并发.n8任务执行;

import java.util.*;
import java.util.concurrent.*;

/**
 * ExcuteServie 中的InvokeAll 方法将多个任务提交到线程池中，并获得结果。
 * InvokeAll 方法的参数为一组任务，并返回一组Future. 这两个集合有着相同的结构
 * invokeAll 按照任务集合中迭代器的顺序将所有的Future 添加到返回的集合中，从而
 * 使调用者能将各个Future与其表示的Callable关联起来。当所有任务都执行完毕时，
 * 或者调用线程被中断时，又或者超过指定时限时，invokeAll将返回。当超过指定时限后，
 * 任何还未完成的任务都会取消。当invokeAll返回后，每个任务要么正常地完成，要么
 * 取消，而客户端代码可以调用get或isCancelled来判断究竟是何种状况。
 *
 */
public class ExecuteServiceInvokeAllSample { // 在预定时间内请求旅游报价

    public List<TravelQuote> getRankedTravelQuotes(
            TravelInfo travelInfo, Set<TravelCompany> companies,
            Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws InterruptedException {
            List<QuoteTask> tasks = new ArrayList<>();
        companies.stream()
                .forEach((c)->tasks.add(new QuoteTask(c,travelInfo)));
        List<Future<TravelQuote>> futures =
                exec.invokeAll(tasks, time, unit); //将一组任务一起提交给futures

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIterator = tasks.iterator();
        for (Future<TravelQuote> f : futures){
            QuoteTask task = taskIterator.next();
            try{
                quotes.add(f.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            }catch (CancellationException e){
                quotes.add(task.getTimeoutQuote(e));
            }
        }
        Collections.sort(quotes,ranking);
        return quotes;
    }


    private class QuoteTask implements Callable<TravelQuote>{// 任务定义
        private final TravelCompany company;
        private final TravelInfo travelInfo;

        public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
            this.company = company;
            this.travelInfo = travelInfo;
        }

        @Override
        public TravelQuote call() throws Exception {
            return company.solicitQuote(travelInfo);
        }
        public TravelQuote getTimeoutQuote(Exception e){
            return new TravelQuote(e.getMessage());
        }
        public TravelQuote getFailureQuote(Throwable t){
            return new TravelQuote(t.getMessage());
        }
    }
    private class TravelQuote{
        public TravelQuote(String resultMessage) {
            this.resultMessage = resultMessage;
        }

        private final String resultMessage;
    }
    private class TravelCompany {
        TravelQuote solicitQuote(TravelInfo info){return null;}
    }
    private class TravelInfo{}
    private final ExecutorService exec;
    public ExecuteServiceInvokeAllSample(ExecutorService exec) {
        this.exec = exec;
    }
}
