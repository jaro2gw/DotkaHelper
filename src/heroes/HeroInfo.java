package heroes;

import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Jaro on 2017-02-12.
 */
public class HeroInfo {
    private static Hero hero;

    static Hero getHero() {
        return hero;
    }

    private static Stage dialog;

    public HeroInfo(Hero hero, HostServices hostServices) throws Exception {
        HeroInfo.hero = hero;

//        Parent root = FXMLLoader.load(getClass().getResource("heroInfo.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("heroInfo.fxml"));
        Parent root = loader.load();
        HeroInfoController controller = loader.getController();
        controller.setHostServices(hostServices);

        dialog = new Stage();
        dialog.initStyle(StageStyle.UNDECORATED);


        dialog.setScene(new Scene(root));
        dialog.show();
    }

    public static Stage getStage() {
        return dialog;
    }
}
