package net;

import org.omg.PortableServer.THREAD_POLICY_ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Created by fanxuehui on 2017/8/16.
 */
public class interruptibleSocketTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new InterruptibleSocketFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class InterruptibleSocketFrame extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancleButton;
    private JTextArea messages;
    private TestServer server;
    private Thread connectThread;

    public InterruptibleSocketFrame(){
        setSize(WIDTH,HEIGHT);
        setTitle("interruptibleSocketTest");

        JPanel northPanel = new JPanel();
        add(northPanel,BorderLayout.NORTH);

        messages = new JTextArea();
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("interrruptible");
        blockingButton = new JButton("blocking");

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);

        //interrupt按钮
        interruptibleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancleButton.setEnabled(true);
                connectThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connectInterruptibly();
                        } catch (Exception e) {
                            messages.append("\ninterruptibleSocketTest.connectInterruptibly:"+e);
                        }
                    }
                });
                connectThread.start();
            }
        });

        //block 按钮
        blockingButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancleButton.setEnabled(true);
                connectThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connectBlocking();
                        } catch (Exception e) {
                            messages.append("\ninterruptibleSocketTest.connectBlocking:"+e);
                        }
                    }
                });
                connectThread.start();
            }
        });
        cancleButton = new JButton("cancle");
        cancleButton.setEnabled(false);
        northPanel.add(cancleButton);
        cancleButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectThread.interrupt();
                cancleButton.setEnabled(false);
            }
        });
        server = new TestServer();
        new Thread(server).start();
    }

    public void connectInterruptibly() throws Exception {
        messages.append("Interrput:\n");
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8989));
        try {
            in = new Scanner(channel);
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("interrupt Reading ");
                if (in.hasNextLine()) {
                    messages.append(in.nextLine()+"\n");
                }
            }
        } finally {
            channel.close();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messages.append("Channel closed\n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
    }

    public void connectBlocking() throws Exception {
        messages.append("Block:\n");
        Socket socket = new Socket("localhost",8989);
        try {
            in = new Scanner(socket.getInputStream());
            while (!Thread.currentThread().isInterrupted()) {
                messages.append("block Reading ");
                if (in.hasNextLine()) {
                    messages.append(in.nextLine()+"\n");
                }
            }
        } finally {
            socket.close();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messages.append("Socket closed\n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
    }


    class TestServer implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(8989);
                while (true) {
                    Socket incoming = serverSocket.accept();
                    Runnable r = new TestServerHandler(incoming) ;
                    Thread t = new Thread(r);
                    t.start();
                }
            } catch (IOException e) {
                messages.append("\nTestServer.run:"+e);
            }
        }
    }

    class TestServerHandler implements Runnable {

        private Socket incoming;
        private int counter;

        public TestServerHandler(Socket i) {
            incoming = i;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = incoming.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream,true);
                while (counter < 100) {
                    counter++;
                    if (counter <=10) {
                        out.println(counter);
                    }
                    Thread.sleep(100);
                }
                incoming.close();
                messages.append("Closing server\n");
            } catch (Exception e) {
                messages.append("\nTestServerHandler:"+e);
            }

        }
    }

}

