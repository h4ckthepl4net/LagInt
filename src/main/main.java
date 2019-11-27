package main;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import classes.common.classes.LocaleBindingFactory;

public class main extends Application {

    private static MainController mainController = null;

    private static Stage _currentStage;
    private static void set_currentStage(Stage stg) {
        main._currentStage = stg;
    }
    private static Stage get_currentStage() {
        return main._currentStage;
    }

    private static Scene _currentScene;
    private static void set_currentScene(Scene scn) {
        main._currentScene = scn;
        main.get_currentStage().setScene(scn);
        main.get_currentStage().show();
    }
    static Scene get_currentScene() {
        return main._currentScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lagrange interpolation (Lagrange polynomial)");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
        main.set_currentStage(primaryStage);
        LocaleBindingFactory.setResources(ResourceBundle.getBundle("bundles/locale", new Locale("hy")));
        this.initMain();
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((Thread exc, Throwable t) -> System.out.println("Uncaught error: " + t.getMessage() + "(" + t.getLocalizedMessage() + ")" + " - " + t.getCause()));
        launch(args);
    }

    private void initMain() throws Exception {
        GridPane mainPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/main.fxml"));
            mainPane = loader.load();
            main.mainController = loader.getController();
        } catch(Exception exc) {
            System.out.println(exc.getMessage()+ " : " + exc.getCause());
        }
        if(mainPane != null) {
            main.set_currentScene(new Scene(mainPane, 900, 500));
        } else {
            throw new Exception("Can not load body");
        }
    }

}
