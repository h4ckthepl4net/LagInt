package main.body.footer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import classes.common.classes.BaseController;
import classes.footer.enums.MessageType;

public class FooterController extends BaseController {

    private footer model = new footer();

    public void setMessage(String message, MessageType mode) throws Exception {
        Color textColor = Color.BLACK;
        String iconURL = "resources/images/icons/footer/";
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

        if(this.mainPane != null) {
            HBox mainPane = (HBox)this.mainPane;
            mainPane.getChildren().clear();
            ImageView imgView = new ImageView();
            imgView.setImage(new Image(iconURL));
            imgView.setFitHeight(20);
            imgView.setFitWidth(20);
            mainPane.getChildren().add(imgView);
            mainPane.getChildren().add(txt);
            this.model.lastMessage = message;
            this.model.lastMessageType = mode;
        } else {
            throw new Exception("No footer found");
        }
    }
}
