package com.chart;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server {

    public static List<Socket> allList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        while (true){
            Socket socket = ss.accept();
            allList.add(socket);
            //把socket封装成任务对象再交给线程池处理
            new WorkThread(socket).start();
            
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
            //从客户端接收消息
            InputStream is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String msg;
            while((msg=bf.readLine())!=null){
                //服务端接收到消息推送给所有客户端
                for(Socket socket:Server.allList){
                    PrintStream  ps =new PrintStream(socket.getOutputStream());
                    ps.println("消息");
                    ps.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}