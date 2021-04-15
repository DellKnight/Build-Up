package com.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper实现分布式锁
 * @author Ping.Dai
 */
public class DcsLock {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZooKeeper zooKeeper = null;

    private static final String root = "/Lock";

    private static final String prefix = "/Lock_";

    private String lock;

    public DcsLock() {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:21813", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                        countDownLatch.countDown();
                        System.out.println("zookeeper连接成功");
                    }
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            //监听节点删除事件
            if(watchedEvent.getType()==Event.EventType.NodeDeleted){
                synchronized (this) {
                    //唤醒所有在该监听器上等待的线程
                    notifyAll();
                }
            }
        }
    };

    public void tryLock() throws KeeperException, InterruptedException {

        Stat rootStat = zooKeeper.exists(root,false);
        if (rootStat == null) {
            //创建根节点
            zooKeeper.create(root,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        //在根节点下创建临时有序节点 /Lock/Lock_0000000001....
        lock = zooKeeper.create(root+prefix,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("子节点创建成功:"+lock);
        //获取根节点下的所有子节点
        List<String> list = zooKeeper.getChildren(root,false);
        //子节点排序 {Lock_0000000001，Lock_0000000002，Lock_0000000003...}
        Collections.sort(list);
        //查看当前节点在集合中的排序
        int index = list.indexOf(lock.substring(root.length()+1));
        if(index==0){
           //自己是第一个节点
           System.out.println("获取锁成功:"+lock);
        }else{
           //获取上一个节点的路径
           String pre_path = list.get(index-1);
           //注:需判断此时上一个节点是否存在
           Stat preStat = zooKeeper.exists(root+"/"+pre_path,watcher);
           if (preStat != null) {
               //上一个节点仍然存在
               System.out.println("获取锁失败,开始等待:" + lock);
               synchronized (watcher) {
                   //在监听器上等待
                   watcher.wait();
               }
           }
           //上一个节点不存在(被删除),继续递归尝试获取锁
           tryLock();
        }
    }

    public void releaseLock() throws InterruptedException, KeeperException {
        zooKeeper.delete(lock,-1);
        zooKeeper.close();
        System.out.println("锁已经释放:"+lock);
    }

    public static void main(String[] args) throws KeeperException, InterruptedException {
        DcsLock dcsLock = new DcsLock();
        dcsLock.tryLock();
        /*do something...*/
        dcsLock.releaseLock();
    }
}
