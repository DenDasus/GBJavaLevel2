package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
    private ServerSocket server = null;
    private Socket socket = null;
    private DataOutputStream out;
    private DataInputStream in;
    private Scanner scanner = new Scanner(System.in);
    private boolean isStopped = false;

    public MyServer() {
        try {
            server = new ServerSocket(3135);
            System.out.println("Server started!");
            socket = server.accept();
            System.out.println("Client connected!");
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
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {

            }
            System.exit(0);
        }
    }
}
