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
    private static final String serverIP = "192.168.0.132";
    private static final int serverPort = 8080;
    private String msg;

    public Connect(String msg) {
        super();
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            //InetAddress serverAddr = InetAddress.getByName(serverIP);
            //Socket sock = new Socket(serverAddr, serverPort);
            Socket sock = new Socket ("192.168.0.132", 8080);
            boolean result = sock.isConnected();

            if(result) System.out.println("서버에 연결됨");

            else System.out.println("서버에 연결실패");
            try {
                //Socket sock = new Socket(serverAddr, serverPort);
                PrintWriter out = new PrintWriter(new BufferedWriter(new
                        OutputStreamWriter(sock.getOutputStream())), true);
                out.println(msg);
                out.flush();
                DataInputStream dis = new DataInputStream(new
                        FileInputStream(new File("/storage/emulated/0/DCIM/Camera/" + msg + ".jpg")));
                DataOutputStream dos = new
                        DataOutputStream(sock.getOutputStream());

                byte[] buf = new byte[1024];
                while (dis.read(buf) > 0) {
                    dos.write(buf);
                    dos.flush();
                }
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sock.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

