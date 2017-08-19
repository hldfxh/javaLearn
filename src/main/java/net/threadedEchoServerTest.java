package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by fanxuehui on 2017/8/16.
 */
public class threadedEchoServerTest {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8989);
            int i = 1;
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("socket num:"+i);
                ThreadEchoHandler echoHandler = new ThreadEchoHandler(socket);
                Thread thread = new Thread(echoHandler);
                thread.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}

class ThreadEchoHandler implements Runnable {
    private Socket socket;

    public ThreadEchoHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            try {
                InputStream inputStream = socket.getInputStream();
                Scanner scanner = new Scanner(inputStream);

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream, true);

                System.out.println("Hello,enter bye to exit");

                boolean done = false;
                while (!done && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    printWriter.println("ECHO:" + line);
                    if (line.trim().equals("bye")) {
                        done = true;
                    }
                }
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}