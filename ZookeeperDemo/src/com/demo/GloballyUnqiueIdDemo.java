package com.demo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

/**
 * 生成分布式id
 * @author Ping.Dai
 */
public class GloballyUnqiueIdDemo implements Watcher {

    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static ZooKeeper zooKeeper = null;
    private static final String root = "/config";

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
            System.out.printf("zookeeper连接成功");
            countDownLatch.countDown();
        }
    }

    public GloballyUnqiueIdDemo() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000,this);
        countDownLatch.await();
    }

    public String generateId() throws KeeperException, InterruptedException {
        String path = zooKeeper.create(root,new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        return path;
    }
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        GloballyUnqiueIdDemo demo = new GloballyUnqiueIdDemo();
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    System.err.println("\r"+demo.generateId().substring(7));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
