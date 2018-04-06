package com.example.root.voice_app;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Connect implements Runnable {

    private static final String serverIP="192.168.0.85";
    private static final int serverPort = 9000;
    private String msg;


    public Connect(String msg){
        super();
        this.msg = msg;
    }

    @Override

    public void run(){
        try{
            InetAddress serverAddr = InetAddress.getByName(serverIP);//서버 IP를 InetAddress의 serverAddr에 추가합니다.
            //socket생성
            Socket sock = new Socket(serverIP, serverPort);

            try{
                PrintWriter out = new PrintWriter(new BufferedWriter(new
                        OutputStreamWriter(sock.getOutputStream())), true);

                out.println(msg);
                out.flush();

                DataInputStream dis = new DataInputStream(new
                        FileInputStream(new File("/storage/emulated/0/DCIM/Camera/20170205_081837.jpg")));
                DataOutputStream dos = new
                        DataOutputStream(sock.getOutputStream());

                byte[] buf = new byte[1024];
                while(dis.read(buf)>0)
                {
                    dos.write(buf);
                    dos.flush();
                }
                dos.close();
            }

            catch(Exception e){
                e.printStackTrace();
            }

            finally
            {
                sock.close();
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}