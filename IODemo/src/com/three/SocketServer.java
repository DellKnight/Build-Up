package com.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Ping.Dai
 * 服务端接收多个客户端消息
 * 服务端每接收到一个客户端请求就创建一个线程去处理
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            System.out.println("服务端启动");
            //创建serversocket连接
            ServerSocket ss = new ServerSocket(9999);
            //不断获得客户端请求
            while(true){
                Socket socket = ss.accept();
                //创建一个独立线程
                new workThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class workThread extends Thread{

    private Socket socket;

    public workThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            //获得一个字节输入流
            InputStream is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String msg;
            while((msg=bf.readLine())!=null){
                System.out.println("Line:"+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}