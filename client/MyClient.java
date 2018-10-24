package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String ipaddr = "localhost";
    private int port = 3135;
    private boolean isStopped = false;
    private Scanner scanner = new Scanner(System.in);

    public MyClient() {
        try {
            socket = new Socket(ipaddr, port);
            System.out.println("Connected to server!");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!isStopped) {
                            String inputString = in.readUTF();
                            if (inputString.equals("/close")) {
                                break;
                            }
                            System.out.println(inputString);
                        }
                    } catch (IOException e) {
                        System.out.println("Ошибка соединения!");
                    } finally {
                        isStopped = true;
                        System.exit(0);
                    }
                }
            }).start();

            while (!isStopped) {
                String consoleString = scanner.nextLine();
                if (consoleString.equals("/close")) {
                    break;
                }
                out.writeUTF(consoleString);
            }
        } catch (IOException e) {
            System.out.println("Ошибка соединения!");
        } finally {
            isStopped = true;
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {

            }
            System.exit(0);
        }
    }
}
