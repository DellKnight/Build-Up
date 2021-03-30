package com.file;

import com.four.CreateThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 *接收客户端的文件信息
 * @author Ping.Dai
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            //创建serversocket连接
            ServerSocket ss = new ServerSocket(8888);
            //死循环不断获得客户端请求
            while(true){
                Socket socket = ss.accept();
                //把socket封装成任务对象再交给线程池处理
                new WorkThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WorkThread extends Thread{

    private Socket socket;

    public WorkThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            //获得一个数据输入流
            DataInputStream dis  = new DataInputStream(socket.getInputStream());
            //读取客户端发送过来的文件类型
            String suffix = dis.readUTF();
            //定义一个字节输出管道
            OutputStream os = new FileOutputStream("C:\\Users\\sniperYin\\Desktop\\"+ UUID.randomUUID().toString()+suffix);
            //从数据输入流读取文件输出到字节输出流
            byte[] b = new byte[1024*1024];
            int leng;
            while((leng=dis.read(b))>0){
                System.out.println("文件写入");
                os.write(b,0,leng);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
