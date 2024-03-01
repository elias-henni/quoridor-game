package quoridor.view.instructions;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.TextAlignment;
import java.io.File;

public class InstructionsView extends BorderPane {
    private Label instructionsTitleText;
    private VBox instructionsContent;
    private Label instruction1;
    private Label instruction2;
    private Label instruction3;
    private Label instruction4;
    private Label instruction5;
    private Label instruction6;
    private Label instruction7;
    private Label instruction8;
    private Label instruction9;
    private Label instruction10;
    private Label instruction11;

    private Button mainMenuButton;
    private static MediaView video;
    private MediaPlayer mediaPlayer;

    public InstructionsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.instructionsTitleText = new Label("Instructions");
        this.mainMenuButton = new Button("Go back");
//        Media media = new Media(new File("media/howToPlay.mp4").toURI().toString());
//        this.mediaPlayer = new MediaPlayer(media);
//        this.mediaPlayer.setAutoPlay(true);
//        video = new MediaView(mediaPlayer);
//        video.setFitWidth(500);

    }

    private void layoutNodes() {
        this.instructionsTitleText.setTextAlignment(TextAlignment.CENTER);
        this.instructionsTitleText.setStyle("-fx-font: 60 monospace;");
        this.instructionsTitleText.setMinHeight(180);
        BorderPane.setMargin(instructionsTitleText, new Insets(60,0,0,0));
        setAlignment(instructionsTitleText, Pos.CENTER);


        this.mainMenuButton.setMinSize(400, 60);
        this.mainMenuButton.setStyle("-fx-font: 24 monospace;");
        BorderPane.setAlignment(mainMenuButton, Pos.CENTER);
        BorderPane.setMargin(mainMenuButton, new Insets(40,0,80,0));

        this.setStyle(("-fx-background-color: #cecece;"));

        this.instructionsContent = new VBox();
        this.instruction1 = new Label("The goal of the game is to reach the opposite side first");
        this.instruction2 = new Label("Your pawn starts at the bottom");
        this.instruction3 = new Label("During each turn you can choose to move pawn/place wall");
        this.instruction4 = new Label("Drag the pawn 1 block to any direction to move");
        this.instruction5 = new Label("To place a wall, click the 'your walls' button");
        this.instruction6 = new Label("You can exit wall-placement mode by clicking your pawn");
        this.instruction7 = new Label("Next you choose a point for your wall");
        this.instruction8 = new Label("Then you choose horizontal/vertical wall position");
        this.instruction9 = new Label("A wall blocks 2 edges");
        this.instruction10 = new Label("You won't be able to block the opponent's path completely");
        this.instruction11 = new Label("You both have 10 walls");

        this.instruction2.setStyle("-fx-font: 18 monospace;");
        this.instruction1.setStyle("-fx-font: 18 monospace;");
        this.instruction3.setStyle("-fx-font: 18 monospace;");
        this.instruction4.setStyle("-fx-font: 18 monospace;");
        this.instruction5.setStyle("-fx-font: 18 monospace;");
        this.instruction6.setStyle("-fx-font: 18 monospace;");
        this.instruction7.setStyle("-fx-font: 18 monospace;");
        this.instruction8.setStyle("-fx-font: 18 monospace;");
        this.instruction9.setStyle("-fx-font: 18 monospace;");
        this.instruction10.setStyle("-fx-font: 18 monospace;");
        this.instruction11.setStyle("-fx-font: 18 monospace;");


        this.instructionsContent.getChildren().addAll(instruction1, instruction2, instruction3, instruction4, instruction5, instruction6, instruction7, instruction8, instruction9,
                instruction10, instruction11);

        this.instructionsContent.setAlignment(Pos.CENTER);
        this.instructionsContent.setSpacing(5);


        setTop(instructionsTitleText);
        setCenter(instructionsContent);
        setBottom(mainMenuButton);
    }

    void stopVideo() {
        this.mediaPlayer.pause();
    }

    Label getInstructionsTitleText() {
        return instructionsTitleText;
    }

    Button getMainMenuButton() {
        return mainMenuButton;
    }
}

