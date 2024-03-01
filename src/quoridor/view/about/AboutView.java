package quoridor.view.about;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class AboutView extends BorderPane {
    private Label aboutTitleText;
    private GridPane aboutUsContent;
    private Label esteban;
    private Label elias;
    private Label kevin;
    private ImageView estebanPicture;
    private ImageView eliasPicture;
    private ImageView kevinPicture;
    private Button mainMenuButton;

    public AboutView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.aboutTitleText = new Label("The Quoridor Digital Team");
        this.mainMenuButton = new Button("Main Menu");
        this.aboutUsContent = new GridPane();
        this.esteban = new Label("User Experience:\nEsteban Verschueren");
        this.elias = new Label("Project Manager:\nElias Henni");
        this.kevin = new Label("Infrastructure:\nKevin Degan");

        this.estebanPicture = new ImageView(new Image("images/estebanPicture.png", 100, 100, false, false));
        this.eliasPicture = new ImageView(new Image("images/eliasPicture.png", 100, 100, false, false));
        this.kevinPicture = new ImageView(new Image("images/kevinPicture.png", 100, 100, false, false));
    }

    private void layoutNodes() {
        this.aboutTitleText.setTextAlignment(TextAlignment.CENTER);
        this.aboutTitleText.setStyle("-fx-font: 37 monospace;");
        this.aboutTitleText.setMinHeight(180);
        BorderPane.setMargin(aboutTitleText, new Insets(60,0,0,0));
        setAlignment(aboutTitleText, Pos.CENTER);

        this.esteban.setMaxSize(60,60);
        this.elias.setMaxSize(60,60);
        this.kevin.setMaxSize(60,60);

        this.elias.setStyle("-fx-font: 18 monospace;");
        this.elias.setTextAlignment(TextAlignment.CENTER);
        this.elias.setAlignment(Pos.CENTER);
        this.elias.setMinWidth(225);
        this.esteban.setStyle("-fx-font: 18 monospace;");
        this.esteban.setTextAlignment(TextAlignment.CENTER);
        this.esteban.setAlignment(Pos.CENTER);
        this.esteban.setMinWidth(225);
        this.kevin.setStyle("-fx-font: 18 monospace;");
        this.kevin.setTextAlignment(TextAlignment.CENTER);
        this.kevin.setAlignment(Pos.CENTER);
        this.kevin.setMinWidth(225);

        this.aboutUsContent.add(esteban, 0,0);
        this.aboutUsContent.add(estebanPicture, 1,0);
        this.aboutUsContent.add(elias, 0,1);
        this.aboutUsContent.add(eliasPicture, 1,1);
        this.aboutUsContent.add(kevin, 0,2);
        this.aboutUsContent.add(kevinPicture, 1,2);
        this.aboutUsContent.setHgap(10);
        this.aboutUsContent.setVgap(40);
        this.aboutUsContent.setAlignment(Pos.CENTER);
        this.aboutUsContent.setMaxWidth(565);

        this.mainMenuButton.setMinSize(400, 60);
        this.mainMenuButton.setStyle("-fx-font: 24 monospace;");
        BorderPane.setAlignment(mainMenuButton, Pos.CENTER);
        BorderPane.setMargin(mainMenuButton, new Insets(50,0,80,0));

        this.setStyle(("-fx-background-color: #cecece;"));

        setTop(aboutTitleText);
        setCenter(aboutUsContent);
        setBottom(mainMenuButton);
    }

    Label getAboutTitleText() {
        return aboutTitleText;
    }

    Button getMainMenuButton() {
        return mainMenuButton;
    }
}

