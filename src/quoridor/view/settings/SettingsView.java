package quoridor.view.settings;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class SettingsView extends BorderPane {
    private Label settingsTitleText;
    private GridPane menuItems;
    private Label boardColorLabel;
    private ComboBox<String> boardColor;
    private Label amountOfWallsLabel;
    private ComboBox<Integer> amountOfWalls;
    private Label wallColorLabel;
    private ComboBox<String> wallColor;
    private Label pawnColorLabel;
    private ComboBox<String> pawnColor;
    private Button saveButton;
    private Button mainMenuButton;

    public SettingsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.settingsTitleText = new Label("Custom Settings");
        this.menuItems = new GridPane();
        this.boardColorLabel = new Label("Board color:");
        this.boardColor = new ComboBox<>();
        this.amountOfWallsLabel = new Label("Player walls:");
        this.amountOfWalls = new ComboBox<>();
        this.wallColorLabel = new Label("Wall color:");
        this.wallColor = new ComboBox<>();
        this.pawnColorLabel = new Label("Pawn color:");
        this.pawnColor = new ComboBox<>();
        this.saveButton = new Button("Save Settings");
        this.mainMenuButton = new Button("Main Menu");
    }

    private void layoutNodes() {
        this.settingsTitleText.setTextAlignment(TextAlignment.CENTER);
        this.settingsTitleText.setStyle("-fx-font: 60 monospace;");
        this.settingsTitleText.setMinHeight(200);
        BorderPane.setMargin(settingsTitleText, new Insets(30,0,0,0));
        setAlignment(settingsTitleText, Pos.CENTER);

        this.menuItems.setHgap(30);
        this.menuItems.setVgap(37);

        this.menuItems.add(amountOfWallsLabel, 0,0);
        this.menuItems.add(amountOfWalls, 1,0);
        this.menuItems.add(boardColorLabel, 0,1);
        this.menuItems.add(boardColor, 1,1);
        this.menuItems.add(wallColorLabel, 0,2);
        this.menuItems.add(wallColor, 1,2);
        this.menuItems.add(pawnColorLabel, 0,3);
        this.menuItems.add(pawnColor, 1,3);
        this.menuItems.add(saveButton, 0,4, 2, 1);
        this.menuItems.add(mainMenuButton, 0,5, 2, 1);

        this.saveButton.setMinSize(300, 60);
        this.saveButton.setStyle("-fx-font: 24 monospace;");
        GridPane.setHalignment(amountOfWallsLabel, HPos.RIGHT);
        GridPane.setHalignment(boardColorLabel, HPos.RIGHT);
        GridPane.setHalignment(wallColorLabel, HPos.RIGHT);
        GridPane.setHalignment(pawnColorLabel, HPos.RIGHT);
        GridPane.setHalignment(saveButton, HPos.CENTER);
        GridPane.setHalignment(mainMenuButton, HPos.CENTER);
        GridPane.setMargin(mainMenuButton, new Insets(20,0,0,0));

        this.mainMenuButton.setMinSize(400, 60);
        this.mainMenuButton.setStyle("-fx-font: 24 monospace;");

        this.amountOfWallsLabel.setStyle("-fx-font: 26 monospace;");
        this.boardColorLabel.setStyle("-fx-font: 26 monospace;");
        this.wallColorLabel.setStyle("-fx-font: 26 monospace;");
        this.pawnColorLabel.setStyle("-fx-font: 26 monospace;");

        this.amountOfWalls.setStyle("-fx-font: 20 monospace;");
        this.boardColor.setStyle("-fx-font: 20 monospace;");
        this.wallColor.setStyle("-fx-font: 20 monospace;");
        this.pawnColor.setStyle("-fx-font: 20 monospace;");

        this.wallColor.getItems().add("Gray");
        this.wallColor.getItems().add("Orange");
        this.wallColor.getItems().add("Blue");
        this.wallColor.getItems().add("Yellow");
        this.wallColor.getItems().add("Purple");
        this.wallColor.getItems().add("Dark Night");

        this.pawnColor.getItems().add("Gray");
        this.pawnColor.getItems().add("Orange");
        this.pawnColor.getItems().add("Blue");
        this.pawnColor.getItems().add("Yellow");
        this.pawnColor.getItems().add("Purple");
        this.pawnColor.getItems().add("Dark Night");

        this.boardColor.getItems().add("Gray");
        this.boardColor.getItems().add("Orange");
        this.boardColor.getItems().add("Blue");
        this.boardColor.getItems().add("Yellow");
        this.boardColor.getItems().add("Purple");
        this.boardColor.getItems().add("Dark Night");

        this.boardColor.getSelectionModel().select(0);
        this.pawnColor.getSelectionModel().select(2);
        this.wallColor.getSelectionModel().select(3);

        this.amountOfWalls.getItems().add(5);
        this.amountOfWalls.getItems().add(10);
        this.amountOfWalls.getItems().add(15);

        this.amountOfWalls.getSelectionModel().select(1);

        this.setStyle(("-fx-background-color: #cecece;"));

        this.menuItems.setAlignment(Pos.TOP_CENTER);
        setAlignment(mainMenuButton, Pos.CENTER);
        this.setTop(settingsTitleText);
        this.setCenter(menuItems);
    }

    ComboBox<String> getBoardColor() {
        return boardColor;
    }

    ComboBox<Integer> getAmountOfWalls() {
        return amountOfWalls;
    }

    ComboBox<String> getWallColor() {
        return wallColor;
    }

    ComboBox<String> getPawnColor() {
        return pawnColor;
    }

    Label getSettingsTitleText() {
        return settingsTitleText;
    }

    Button getSaveButton() {
        return saveButton;
    }

    Button getMainMenuButton() {
        return mainMenuButton;
    }
}
