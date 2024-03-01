package quoridor.view.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class StartView extends BorderPane {
    private Label startPageTitle;
    private Button playGameButton;
    private VBox menuBox;
    private HBox enterNameHBox;
    private TextField enterName;
    private Label enterNameLabel;
    private Button mainMenuButton;
    private Button instructions;


    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.startPageTitle = new Label("Start Game");
        this.enterName = new TextField();
        this.playGameButton = new Button("Play");
        this.menuBox = new VBox();
        this.enterNameHBox = new HBox();
        this.enterNameLabel = new Label("Enter Name:");
        this.mainMenuButton = new Button("Main Menu");
        this.instructions = new Button("View Instructions");
    }

    private void layoutNodes() {
        this.startPageTitle.setTextAlignment(TextAlignment.CENTER);
        this.startPageTitle.setStyle("-fx-font: 75 monospace;");
        this.startPageTitle.setMinHeight(240);
        setAlignment(startPageTitle, Pos.CENTER);

        this.enterNameLabel.setStyle("-fx-font: 28 monospace;");
        this.enterName.setStyle("-fx-font: 22 monospace;");
        this.enterName.setPrefWidth(225);

        this.enterNameHBox.getChildren().addAll(enterNameLabel, enterName);
        this.enterNameHBox.setSpacing(30);
        this.enterNameHBox.setPadding(new Insets(40, 0, 30, 0));
        this.enterNameHBox.setAlignment(Pos.CENTER);

        this.menuBox.setSpacing(30);
        this.menuBox.setPadding(new Insets(10, 80, 10, 80));
        this.menuBox.setAlignment(Pos.TOP_CENTER);
        this.menuBox.getChildren().addAll(enterNameHBox, playGameButton, instructions, mainMenuButton);

        this.playGameButton.setMinSize(400, 60);
        this.playGameButton.setStyle("-fx-font: 24 monospace;");
        this.instructions.setMinSize(400, 60);
        this.instructions.setStyle("-fx-font: 24 monospace;");
        this.mainMenuButton.setMinSize(400, 60);
        this.mainMenuButton.setStyle("-fx-font: 24 monospace;");

        this.setStyle(("-fx-background-color: #cecece;"));

        setTop(startPageTitle);
        setCenter(menuBox);
    }

    Label getStartPageTitle() {
        return startPageTitle;
    }

    Button getPlayGameButton() {
        return playGameButton;
    }

    TextField getEnterName() {
        return enterName;
    }

    Button getMainMenuButton(){
        return mainMenuButton;
    }

    public Button getInstructions() {
        return instructions;
    }
}
