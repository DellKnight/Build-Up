package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeapOverFlowDemo {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        for(int i=0;i<1024;i++){
            TimeUnit.SECONDS.sleep(1);
            list.add(new byte[1024*1024]);
        }
    }
}
