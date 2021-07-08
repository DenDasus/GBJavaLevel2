package client;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;

import static javafx.geometry.Pos.CENTER_LEFT;
import static javafx.geometry.Pos.CENTER_RIGHT;

public class MessageCell extends HBox {
    private String nameStr;
    private String timeStr;
    private String messageStr;
    private boolean privateMsg;
    
    public MessageCell(String nameStr, String timeStr, String messageStr, boolean privateMsg, boolean income) {
        this.nameStr = nameStr;
        this.timeStr = timeStr;
        this.messageStr = messageStr;
        this.privateMsg = privateMsg;
    
        Text message = new Text(messageStr);
        Text time = new Text(timeStr);
        Text inf;
        if(privateMsg) {
            inf = new Text("private message from " + nameStr + " @ ");
        } else {
            inf = new Text("message from " + nameStr + " @ ");
        }
        
        HBox information = new HBox(inf, time);
        VBox msgBox = new VBox(message);
        VBox vBox = new VBox(information, message);

        message.setStyle("-fx-font: 16 arial;");
        
        if(income) {
            information.setAlignment(CENTER_LEFT);
            vBox.setAlignment(Pos.CENTER_LEFT);
        } else {
            information.setAlignment(CENTER_RIGHT);
            vBox.setAlignment(Pos.CENTER_RIGHT);
        }

        message.setText(messageStr);

        setHgrow(vBox, Priority.ALWAYS);
        this.getChildren().add(vBox);
        this.getStyleClass().add("messageBox");
    }
}
