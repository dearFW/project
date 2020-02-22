package com.neuedu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by heystephen on 2020/2/22.
 *
 */
public class Sever1 {
    public static void main(String[] args) {
        try {
            ServerSocket sever  =  new ServerSocket (3307 ) ;
            //阻塞方法   等待客户端连接
          Socket scoket =  sever.accept();
          System.out.println("客户端连接啦");
          InputStream is = scoket.getInputStream();
          InputStreamReader isr = new InputStreamReader(is);
          BufferedReader br =  new BufferedReader(isr);
          String str = br.readLine();
          System.out.println("客户端说"+str);

          while (true){

              System.out.println("客户端说"+str);
              str = br.readLine();
          }


          /*  System.out.println("客户端1输出的内容为"+str);
          br.close();
          isr.close();
          is.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
