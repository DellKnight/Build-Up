package com.four;

import java.util.concurrent.*;

/**
 * @author Ping.Dai
 */
public class CreateThreadPool {

    private ExecutorService executorService;

    public CreateThreadPool(int maxThreadNum,int queSize) {
        executorService = new ThreadPoolExecutor(3,
                maxThreadNum,120L,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queSize));
    }

    public void execute(Runnable target){
        executorService.execute(target);
    }

}
