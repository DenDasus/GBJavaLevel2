package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private MainServer server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    
    private String userNick;
    
    public String getUserNick() {
        return userNick;
    }
    
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
    
    public ClientHandler(MainServer server, Socket socket) {
        final ClientHandler instance = this;

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            String str = in.readUTF();
                            if(str.startsWith("/auth")) {
                                String[] parts = str.split(" ");
                                String userName = parts[1];
                                String pass = parts[2];
                                
                                String newNick = AuthService.getNickByUserNameAndPass(userName, pass); //Нужна проверка в БД
                                
                                if(newNick != null) {
                                    if(!server.isNickBusy(newNick)) { //проверяем, занят лим ник
                                        sendMsg("/authok");
                                        setUserNick(newNick);
                                        server.addClient(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Данный логин уже занят!");
                                    }
                                } else {
                                    sendMsg("Неверный логин или пароль!");
                                }
                            }
                        }
                        
                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/")) {
                                if (str.equals("/end")) {
                                    out.writeUTF("/serverClosed");
                                    break;
                                }
                                if(str.startsWith("/w ")) {
                                    String[] parts = str.split(" ",3);
                                    server.sendMsg(ClientHandler.this, parts[1], parts[2]);
                                }
                            } else {
                                server.broadCastMsg(ClientHandler.this, str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                            out.close();
                            socket.close();
                            server.deleteClient(instance);
                            System.out.println("Клиент отключился!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
