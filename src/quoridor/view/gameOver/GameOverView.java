package quoridor.view.gameOver;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

public class GameOverView extends BorderPane {
    private HBox buttonBar;
    private Label gameOverTitle;
    private Button mainMenuButton;
    private Button playAgainButton;
    private Button exitButton;
    private Label playerNameAndMoves;
    private Label errorText;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private LineChart moveTime;
    private XYChart.Series dataseries1;
    private GridPane gridPane;
    private long average;



    public GameOverView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.gameOverTitle = new Label("Game Over Screen");
        this.exitButton = new Button("Exit");
        this.playAgainButton = new Button("Play Again");
        this.mainMenuButton = new Button("Main Menu");
        this.playerNameAndMoves = new Label("Average time for moves: " + average + "ms \n" +
                "Amount of moves: "  );
        this.errorText = new Label("TODO");
        this.buttonBar = new HBox();
        this.gridPane = new GridPane();

        this.xAxis = new NumberAxis();
        xAxis.setLabel("move");
        this.yAxis = new NumberAxis();
        yAxis.setLabel("time(ms)");
        this.moveTime = new LineChart(xAxis,yAxis);
        this.dataseries1 = new XYChart.Series();

        dataseries1.setName("time per move");
        moveTime.getData().add(dataseries1);

    }

    private void layoutNodes() {

        moveTime.setMaxHeight(300);
        moveTime.setMaxWidth(350);
        gridPane.add(moveTime, 1,1,1,1);
        gridPane.add(playerNameAndMoves, 1,3,1,1);




        this.gameOverTitle.setTextAlignment(TextAlignment.CENTER);
        this.gameOverTitle.setStyle("-fx-font: 60 monospace;");
        this.gameOverTitle.setMinHeight(160);
        BorderPane.setMargin(gameOverTitle, new Insets(40,0,0,0));
        setAlignment(gameOverTitle, Pos.CENTER);

        this.playerNameAndMoves.setStyle("-fx-font: 18 monospace;");
        this.playerNameAndMoves.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(playerNameAndMoves, Pos.CENTER);

        this.buttonBar.getChildren().addAll(playAgainButton, mainMenuButton, exitButton);
        this.buttonBar.setSpacing(46);
        this.buttonBar.setPadding(new Insets(20,20,30,20));

        this.playAgainButton.setMinSize(190, 60);
        this.playAgainButton.setStyle("-fx-font: 24 monospace;");

        this.mainMenuButton.setMinSize(190, 60);
        this.mainMenuButton.setStyle("-fx-font: 24 monospace;");

        this.exitButton.setMinSize(190, 60);
        this.exitButton.setStyle("-fx-font: 24 monospace;");

        setAlignment(buttonBar, Pos.CENTER);
        setCenter(buttonBar);

        setTop(gameOverTitle);
        setCenter(gridPane);
        setBottom(buttonBar);

        this.setStyle(("-fx-background-color: #cecece;"));
    }

    public void gameOverOutcome(boolean win) {
        if (win) {
            this.gameOverTitle.setText("You Win!");
        } else {
            this.gameOverTitle.setText("You Lose!");
        }
    }

    Label getGameOverTitle() { return gameOverTitle; }

    Button getMainMenuButton() {
        return mainMenuButton;
    }

    Button getPlayAgainButton() {
        return playAgainButton;
    }

    Label getQuoridorTitleText() {
        return gameOverTitle;
    }

    Button getExitButton() {
        return exitButton;
    }

    Label getPlayerNameAndMoves() {
        return playerNameAndMoves;
    }

    Label getErrorText(){return errorText;}

    public void setDataSeries1(int move,long time) {
        dataseries1.getData().add(new XYChart.Data(move,time));
    }

    public void setAverage(long average) {
        this.average = average;
    }

    public long getAverage() {
        return average;
    }
}
