package net;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by fanxuehui on 2017/8/15.
 */
public class netTest{

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getLocalHost();
            Socket socket = new Socket("time-A.timefreq.bldrdoc.gov",13);
            InputStream inputStream = socket.getInputStream();
            Scanner in = new Scanner(inputStream);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
