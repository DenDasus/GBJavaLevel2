package chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm");

    public void sendMsg(ActionEvent actionEvent) {
        if(!(textField.getText().equals("") || textField.getText().matches("^\\s*"))) {
            textArea.appendText(formatTime.format(new Date()) + ": " + textField.getText() + "\n");
        }
        textField.clear();
        textField.requestFocus();
    }
}
