package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

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
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lagrange interpolation (Lagrange polynomial)");
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(700);
        main.set_currentStage(primaryStage);
        main.set_currentScene(new Scene(FXMLLoader.load(getClass().getResource("/main/main.fxml")), 900, 500));
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((Thread exc, Throwable t) -> System.out.println("Uncaught error: " + t.getMessage() + "(" + t.getLocalizedMessage() + ")" + " - " + t.getCause()));
        launch(args);
    }

}
