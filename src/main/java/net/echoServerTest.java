package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by fanxuehui on 2017/8/15.
 */
public class echoServerTest {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8989);

            Socket socket = serverSocket.accept();

            try {
                InputStream inputStream = socket.getInputStream();
                Scanner scanner = new Scanner(inputStream);

                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream,true);

                System.out.println("Hell0,enter bye to exit");

                boolean done = false;
                while (!done && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    printWriter.println("ECHO:"+line);
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
