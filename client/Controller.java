package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

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
    TextArea textArea;

    @FXML
    TextField textField;

    Socket socket;

    DataInputStream in;
    DataOutputStream out;

    String userName = "";

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            userName = userNameInputDialog();

            out.writeUTF("/userName " + userName);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverClosed")) break;
                            textArea.appendText(str + "\n");
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
            }).start();
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

    private String userNameInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Chat");
        dialog.setHeaderText("Здравствуте!");
        dialog.setContentText("Пожалуйста, введите свое имя:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String user = result.get();
            if (!user.matches("^[\\w]{3,15}$")) {
                informationDialog("Имя пользователя должно состоять из букв латинского алфавита или цифр. Длина имени от 3 до 15 символов.");
                user = userNameInputDialog();
            }
            return user;
        }
        return "User";
    }

    private void informationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Chat");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
