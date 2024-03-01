package quoridor.view.mainMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class MainMenuView extends BorderPane {
    private Label quoridorTitleText;
    private Button startButton;
    private Button creditsButton;
    private Button settingsButton;
    private Button exitButton;
    private VBox menuBox;
    private Button statisticsButton;

    public MainMenuView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.quoridorTitleText = new Label("Quoridor");
        this.startButton = new Button("Start");
        this.creditsButton = new Button("Credits");
        this.settingsButton = new Button("Settings");
        this.exitButton = new Button("Exit");
        this.menuBox = new VBox();
        this.statisticsButton = new Button("Statistics");
    }

    private void layoutNodes() {
        this.quoridorTitleText.setTextAlignment(TextAlignment.CENTER);
        this.quoridorTitleText.setStyle("-fx-font: 75 monospace;");
        this.quoridorTitleText.setMinHeight(275);
        setTop(quoridorTitleText);
        setAlignment(quoridorTitleText, Pos.CENTER);

        setCenter(menuBox);
        this.menuBox.setSpacing(20);
        this.menuBox.setPadding(new Insets(0,0,125,0));
        this.menuBox.setAlignment(Pos.CENTER);
        this.menuBox.getChildren().addAll(startButton, settingsButton, creditsButton, statisticsButton, exitButton);

        this.startButton.setMinSize(400, 60);
        this.startButton.setStyle("-fx-font: 24 monospace;");
        this.settingsButton.setMinSize(400, 60);
        this.settingsButton.setStyle("-fx-font: 24 monospace;");
        this.creditsButton.setMinSize(400, 60);
        this.creditsButton.setStyle("-fx-font: 24 monospace;");
        this.statisticsButton.setMinSize(400, 60);
        this.statisticsButton.setStyle("-fx-font: 24 monospace;");
        this.exitButton.setMinSize(400, 60);
        this.exitButton.setStyle("-fx-font: 24 monospace;");

        this.setStyle(("-fx-background-color: #cecece;"));
    }

    Label getQuoridorTitleText() {
        return quoridorTitleText;
    }

    Button getStartButton() {
        return startButton;
    }

    Button getCreditsButton() {
        return creditsButton;
    }

    Button getSettingsButton() {
        return settingsButton;
    }

    Button getExitButton() {
        return exitButton;
    }

    Button getStatisticsButton(){
        return statisticsButton;
    }
}

