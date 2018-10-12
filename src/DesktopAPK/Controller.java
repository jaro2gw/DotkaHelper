package DesktopAPK;

import heroes.*;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    private HeroInfo info;
    @FXML
    private ImageView atrStr, atrAgi, atrInt, factionRadiant, factionDire;
    @FXML
    private GridPane STR_RADIANT, STR_DIRE, AGI_RADIANT, AGI_DIRE, INT_RADIANT, INT_DIRE;
    @FXML
    private Text heroName, heroTags, heroAttackType;
    private HostServices hostServices;

    private GridPane getGridPane(Faction faction, Attribute attribute) {
        if (faction.equals(Faction.Radiant)) {
            if (attribute.equals(Attribute.Strength)) return STR_RADIANT;
            else if (attribute.equals(Attribute.Agility)) return AGI_RADIANT;
            else return INT_RADIANT;
        } else {
            if (attribute.equals(Attribute.Strength)) return STR_DIRE;
            else if (attribute.equals(Attribute.Agility)) return AGI_DIRE;
            else return INT_DIRE;
        }
    }

    private List<Hero> heroes = new InitTags().initHeroes();

    private List<Hero> getHeroes(Faction faction, Attribute attribute) {
        return heroes.stream()
                .filter(hero -> hero.getFaction() == faction && hero.getAttribute() == attribute)
                .collect(Collectors.toList());
    }

    private ImageView createImageView(Hero tempHero) {
        ImageView imageView = tempHero.getImageView();
        imageView.setCursor(Cursor.HAND);

        Effect dropShadow = new DropShadow(25, Color.BLACK);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setInput(dropShadow);

        imageView.setEffect(colorAdjust);
        imageView.setOnMouseEntered(event -> {
            colorAdjust.setBrightness(+0.25);

            heroName.setText(tempHero.getName().toUpperCase());

            heroAttackType.setText(tempHero.getAttackType() + "");

            StringBuilder text = new StringBuilder();
            for (Categories category : tempHero.getCategories()) text.append(", ").append(category);
            heroTags.setText(text.toString());
        });
        imageView.setOnMouseExited(event -> colorAdjust.setBrightness(0));
        imageView.setOnMouseClicked(event -> {
            try {
                if (info != null) HeroInfo.getStage().close();
//                HeroInfo info = new HeroInfo(tempHero);
                info = new HeroInfo(tempHero, this.hostServices);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        imageView.setPreserveRatio(true);

        return imageView;
    }

    private void initHeroes() {
        for (Faction faction : Faction.values()) {
            for (Attribute attribute : Attribute.values()) {

                GridPane tempGrid = getGridPane(faction, attribute);
                List<Hero> tempHeroes = getHeroes(faction, attribute);

                for (int i = 0; i < tempHeroes.size(); i++) {
                    Hero tempHero = tempHeroes.get(i);

                    ImageView img = createImageView(tempHero);

                    img.fitWidthProperty().bind(tempGrid.widthProperty().subtract(12).divide(4));
                    img.fitHeightProperty().bind(tempGrid.heightProperty().subtract(faction == Faction.Radiant ? 18 : 15).divide(faction == Faction.Radiant ? 6 : 5));

                    tempGrid.add(img, i % 4, i / 4);
                }
            }
        }
    }

    private void initTooltips() {
        Tooltip.install(atrStr, new Tooltip("STRENGTH"));
        Tooltip.install(atrAgi, new Tooltip("AGILITY"));
        Tooltip.install(atrInt, new Tooltip("INTELLIGENCE"));
        Tooltip.install(factionRadiant, new Tooltip("RADIANT"));
        Tooltip.install(factionDire, new Tooltip("DIRE"));
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        initTooltips();
        initHeroes();
    }

    void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public HostServices getHostServices() {
        return hostServices;
    }
}
