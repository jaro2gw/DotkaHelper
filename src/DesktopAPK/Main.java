package DesktopAPK;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("dotka.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setHostServices(getHostServices());

        primaryStage.setTitle("Dotka Helper");
        primaryStage.getIcons().add(new Image("DOTA_PICS/DOTA_ICON.png"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(this::close);
        primaryStage.show();
    }

    static public Stage getStage() {
        return stage;
    }

    private void close(WindowEvent windowEvent) {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
