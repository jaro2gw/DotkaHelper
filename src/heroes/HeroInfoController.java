package heroes;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jaro on 2017-02-12.
 */
public class HeroInfoController implements Initializable {
    @FXML
    Hyperlink webLink;
    @FXML
    Pane      bordersPane;
    @FXML
    Text      name, description, atk, cats;
    @FXML
    private ImageView heroImg, exitBtn;
    private HostServices hostServices;
    private Hero         hero;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        hero = HeroInfo.getHero();
        name.setText(hero.getName().toUpperCase());
        initDescription();
        initImgs();
        initBorders();
        initWebLink();
    }

    private void initDescription() {
        atk.setText(hero.getAttackType() + "");
        atk.setFont(Font.font("Lucida Console", FontWeight.BOLD, 18));

        StringBuilder text = new StringBuilder();
        for (Categories categories : hero.getCategories()) text.append(", ").append(categories);
        cats.setText(text.toString());
        cats.setFont(Font.font(18));

        description.setText("Faction: " + hero.getFaction() + "\nAttribute: " + hero.getAttribute());
    }

    private void initWebLink() {
        webLink.setOnMouseClicked(event -> {
            String url = "http://www.dota2.com/hero/" + hero.getName().replace(" ", "_").replace("\'", "");
            hostServices.showDocument(url);
        });
    }

    private void initBorders() {
        Attribute atr = HeroInfo.getHero().getAttribute();
        String    color;
        if (atr == Attribute.Strength) color = "#680d0d";
        else if (atr == Attribute.Agility) color = "#116623";
        else color = "#2252a2";
        bordersPane.setStyle("-fx-border-color: black, " + color + "; -fx-border-insets : 0, 4;-fx-border-width: 4, 2; -fx-border-style: solid, solid;");
    }

    private void initImgs() {
        heroImg.setImage(new Image("DOTA_PICS/PORTRAITS/" + hero.getPath() + "_full.png"));
        heroImg.setEffect(new InnerShadow(50, Color.BLACK));

        ColorAdjust clr = new ColorAdjust();
        exitBtn.setEffect(clr);
        exitBtn.setOnMouseEntered(event -> clr.setBrightness(+0.25));
        exitBtn.setOnMouseExited(event -> clr.setBrightness(0));
        exitBtn.setOnMouseClicked(event -> HeroInfo.getStage().close());
    }

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
}
