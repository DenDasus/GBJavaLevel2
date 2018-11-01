package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    
    @FXML
    VBox messagesVBox;
    @FXML
    TextField textField;
    @FXML
    Pane pane;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
    HBox authPanel;
    @FXML
    HBox messageSendPanel;
    @FXML
    ListView<String> clientsList;
    @FXML
    ScrollPane messagesBoxScrollPane;

    Socket socket;

    DataInputStream in;
    DataOutputStream out;
    
    final String separator = "=!=";
    final String IP_ADRESS = "localhost";
    final int PORT = 8189;
    
    private boolean authorized = false;
    private String myNickName;
    
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
        
        if(!authorized) {
            authPanel.setVisible(true);
            authPanel.setManaged(true);
            messageSendPanel.setVisible(false);
            messageSendPanel.setManaged(false);
            clientsList.setVisible(false);
            clientsList.setManaged(false);
        } else {
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            messageSendPanel.setVisible(true);
            messageSendPanel.setManaged(true);
            clientsList.setVisible(true);
            clientsList.setManaged(true);
            clientsList.setPrefWidth(150);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            setAuthorized(false);
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Thread socketListener = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) break;
                            if (str.equals("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                MessageCell msg = new MessageCell("System", "12:56", (str + "\n"), false, true);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        messagesVBox.getChildren().add(msg);
                                    }
                                });
                            }
                        }
                        
                        while (true) {
                            String str = in.readUTF();
                            
                            if(str.startsWith("/")){
                                MessageCell msg = null;
                                
                                if(str.startsWith("/bMsg")) {
                                    String[] parts = str.split(separator, 4);
                                    boolean income = !parts[2].equals(myNickName);
                                    msg = new MessageCell(parts[2], parts[1], parts[3], false, income);
                                }
    
                                if(str.startsWith("/pMsg")) {
                                    String[] parts = str.split(separator, 4);
                                    boolean income = !parts[2].equals(myNickName);
                                    msg = new MessageCell(parts[2], parts[1], parts[3], true, income);
                                }
                                
                                if(str.startsWith("/sysMsg")) {
                                    String[] parts = str.split(separator, 4);
                                    if(parts[2].equals("clientsList")) {
                                        String[] list = parts[3].split(" ");
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                clientsList.getItems().clear();
                                                for (int i = 0; i < list.length; i++) {
                                                    clientsList.getItems().add(list[i]);
                                                }
                                            }
                                        });
                                    }
                                }
                                
                                if(msg != null) {
                                    final MessageCell msgPtr = msg;
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            messagesVBox.getChildren().add(msgPtr);
                                        }
                                    });
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            socketListener.setDaemon(true);
            socketListener.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void tryToLogin() {
        String login = loginField.getText();
        String pass = passwordField.getText();
        if(login.matches("^[\\w]{3,15}$") && !pass.equals("")) {
            try {
                out.writeUTF("/auth " + login + " " + pass);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String messageStr = "Имя пользователя должно состоять из букв латинского алфавита или цифр. Длина имени от 3 до 15 символов.";
            MessageCell msg = new MessageCell("System", "", (messageStr + "\n"), false, true);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    messagesVBox.getChildren().add(msg);
                }
            });
        }
        loginField.clear();
        passwordField.clear();
        
        myNickName = login;
    }
}
