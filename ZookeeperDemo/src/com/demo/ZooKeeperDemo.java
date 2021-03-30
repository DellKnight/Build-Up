package com.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperDemo implements Watcher{

    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static ZooKeeper zooKeeper = null;

    public static void main(String[] args) {

        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 50000, new ZooKeeperDemo());

            countDownLatch.await();

            zooKeeper.getChildren("/idea", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("我监控的节点改变了");
                    System.out.println("类型:"+watchedEvent.getType());
                    System.out.println("路径:"+watchedEvent.getPath());
                    try {
                        zooKeeper.getChildren(watchedEvent.getPath(),this,null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            zooKeeper.getChildren("/idea", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("我监控的节点又改变了");
                    System.out.println("类型:"+watchedEvent.getType());
                    System.out.println("路径:"+watchedEvent.getPath());
                    try {
                        zooKeeper.getChildren(watchedEvent.getPath(),this,null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
           /* zooKeeper.exists("/watch",new myWatcher1());*/
            Thread.sleep(50000);
            zooKeeper.close();
            System.out.println("结束");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                System.out.println("连接成功");
                countDownLatch.countDown();
            }else if(watchedEvent.getState()==Event.KeeperState.Disconnected){
                System.out.println("连接失败");
            }
        }catch (Exception e){

        }
    }

    static class myWatcher implements  Watcher{

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("我变化了,eventType"+watchedEvent.getType());
            try {
                zooKeeper.exists(watchedEvent.getPath(),this);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class myWatcher1 implements  Watcher{

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("我又变化了,eventType"+watchedEvent.getType());
            try {
                zooKeeper.exists(watchedEvent.getPath(),this);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
