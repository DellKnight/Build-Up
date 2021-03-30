package com.demo;

import java.util.concurrent.ExecutionException;

/**
 * @author Ping.Dai
 */
public class TestDcs {

    private static int count = 0;

    public static void main(String[] args) throws Exception {

        for(int i=0;i<10;i++){
            final int j = i;
            new Thread(()->{
                try{
                    DcsLock dcsLock = new DcsLock();
                    dcsLock.tryLock();
                    count+=1;
                    dcsLock.releaseLock();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("count="+count);
    }
}
