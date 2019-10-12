package main.body.footer;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.main;
import main.body.footer.footer.MessageType;

public class FooterController {

    private footer model = new footer();

    @FXML
    private HBox footer;

    public void setMessage(String message, MessageType mode) throws Exception {
        Color textColor = Color.BLACK;
        String iconURL = "file:assets/icons/";
        if(mode == MessageType.TEXT) {
            textColor = Color.rgb(70, 88, 204);
            iconURL += "text.png";
        } else if (mode == MessageType.WARNING) {
            textColor = Color.rgb(204,129,2);
            iconURL += "warning.png";
        } else if (mode == MessageType.ERROR) {
            textColor = Color.rgb(208, 2, 2);
            iconURL += "error.png";
        }

        Text txt = new Text(message);
        txt.setFont(new Font("Verdana", 15));
        txt.setFill(textColor);

        if(this.footer != null) {
            this.footer.getChildren().clear();
            ImageView imgView = new ImageView();
            Image img = new Image(iconURL);
            imgView.setImage(img);
            imgView.setFitHeight(20);
            imgView.setFitWidth(20);
            this.footer.getChildren().add(imgView);
            this.footer.getChildren().add(txt);
            this.model.lastMessage = message;
            this.model.lastMessageType = mode;
        } else {
            throw new Exception("No footer found");
        }
    }
}
