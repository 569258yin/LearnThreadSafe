package com.yh.execute;

import java.lang.annotation.Target;
import java.util.*;
import java.util.concurrent.*;

/**
 * ����invokeAll �ύһ�����񣬲������ó�ʱʱ��
 * Created by kevinyin on 2017/8/5.
 */
public class ResverTimeTask {

    private ExecutorService exec = Executors.newCachedThreadPool();

    public List<TraverlQuote> getRankedTravelQuote
            (TravelInfo travelInfo,Set<TravelCompany> companies,
             Comparator<TraverlQuote> ranking,long time, TimeUnit unit) throws InterruptedException {

        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company :
                companies) {
            tasks.add(new QuoteTask(company,travelInfo));
        }
        //���г�ʱ���ƣ���ʱ�䳬�����κ�δ��ɵ����񶼽�ȡ��
        List<Future<TraverlQuote>> futures = exec.invokeAll(tasks, time, unit);
        
        List<TraverlQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIterator = tasks.iterator();
        for (Future<TraverlQuote> f :
                futures) {
            QuoteTask task = taskIterator.next();
            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
                quotes.add(task.getFailureQutote(e.getCause()));
            } catch (CancellationException e) {
                quotes.add(task.getTimeOutQuote(e.getCause()));
            }
        }

        Collections.sort(quotes,ranking);
        return quotes;
    }


    class TraverlQuote {

    }

    class TravelCompany {
        public TraverlQuote solicitQuote(TravelInfo tarvelInfo) {
            return new TraverlQuote();
        }
    }

    class TravelInfo {

    }

    class QuoteTask implements Callable<TraverlQuote> {

        private final TravelCompany company;
        private final TravelInfo tarvelInfo;

        public QuoteTask(TravelCompany company, TravelInfo tarvelInfo) {
            this.company = company;
            this.tarvelInfo = tarvelInfo;
        }

        @Override
        public TraverlQuote call() throws Exception {
            return company.solicitQuote(tarvelInfo);
        }

        public TraverlQuote getFailureQutote(Throwable cause) {
            return null;
        }

        public TraverlQuote getTimeOutQuote(Throwable cause) {
            return null;
        }
    }

}
