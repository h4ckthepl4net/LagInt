package main.body.content.info;

import java.net.URL;
import java.util.ResourceBundle;

import classes.common.classes.BaseController;

public class InfoController extends BaseController {

    private info model = new info();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        super.initialize(location, resourceBundle);
        this.model.dontShowInfoAgain = this.classPrefs.getBoolean("dont_show_info_again", false);
    }

    public void toggleDontShowAgain() {
        this.model.dontShowInfoAgain = !this.model.dontShowInfoAgain;
        this.classPrefs.putBoolean("dont_show_info_again", this.model.dontShowInfoAgain);
    }

}
