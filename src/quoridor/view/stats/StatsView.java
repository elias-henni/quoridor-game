package quoridor.view.stats;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import quoridor.quoridorModel.Game;

public class StatsView extends BorderPane {
    private Label statsPageTitle;
    private Button searchButton;
    private Button mainMenuButton;
    private TextField enterName;
    private VBox bottomBar;
    private HBox searchBox;
    private Label enterNameLabel;
    private BarChart wonGames;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private XYChart.Series dataSeries1;
    private GridPane gridPane;
    private BarChart gameDuration;
    private XYChart.Series dataSeries2;
    private CategoryAxis xAxis2;
    private NumberAxis yAxis2;


    public StatsView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        this.statsPageTitle = new Label("Stats Page");
        this.searchButton = new Button("Search");
        this.enterName = new TextField();
        this.gridPane = new GridPane();
        this.bottomBar = new VBox();
        this.searchBox = new HBox();
        this.enterNameLabel = new Label("Enter Name:");
        this.mainMenuButton = new Button("Main Menu");

        this.xAxis = new CategoryAxis();
        xAxis.setLabel("name");
        this.yAxis = new NumberAxis();
        yAxis.setLabel("moves");
        this.wonGames = new BarChart(xAxis,yAxis);
        this.dataSeries1 = new XYChart.Series();

        dataSeries1.setName("Best #moves");
        wonGames.getData().add(dataSeries1);

        this.xAxis2 = new CategoryAxis();
        xAxis2.setLabel("game");
        this.yAxis2 = new NumberAxis();
        yAxis2.setLabel("Moves");
        this.gameDuration = new BarChart(xAxis2,yAxis2);
        this.dataSeries2 = new XYChart.Series();

        dataSeries2.setName("Average Game");
        gameDuration.getData().add(dataSeries2);

    }

    private void layoutNodes() {
        this.statsPageTitle.setTextAlignment(TextAlignment.CENTER);
        this.statsPageTitle.setStyle("-fx-font: 60 monospace;");
        this.statsPageTitle.setMinHeight(160);
        BorderPane.setMargin(statsPageTitle, new Insets(40,0,0,0));
        setAlignment(statsPageTitle, Pos.CENTER);


//        wonGames.setMaxHeight(300);
//        wonGames.setMaxWidth(350);
//        gridPane.add(wonGames, 0,0,1,1);
//        gridPane.add(gameDuration,2,2,1,1);

        this.enterNameLabel.setStyle("-fx-font: 26 monospace;");
        setAlignment(enterNameLabel, Pos.CENTER);

        this.enterName.setPrefWidth(225);
        this.enterName.setStyle("-fx-font: 22 monospace;");
        setAlignment(enterName, Pos.CENTER);

        this.searchButton.setMinSize(150,45);
        this.searchButton.setStyle("-fx-font: 26 monospace;");
        setAlignment(searchButton, Pos.CENTER);

        this.searchBox.getChildren().addAll(enterNameLabel, enterName, searchButton);
        this.searchBox.setSpacing(40);
        this.searchBox.setAlignment(Pos.CENTER);

        this.mainMenuButton.setMinSize(400, 60);
        this.mainMenuButton.setStyle("-fx-font: 24 monospace;");
        setAlignment(mainMenuButton, Pos.CENTER);

        this.bottomBar.getChildren().addAll(searchBox, mainMenuButton);
        this.bottomBar.setSpacing(30);
        this.bottomBar.setAlignment(Pos.CENTER);
        this.bottomBar.setPadding(new Insets(40,0,50,0));

        setTop(statsPageTitle);
        setCenter(wonGames);
        setBottom(bottomBar);

        this.setStyle(("-fx-background-color: #cecece;"));
    }

    Button getSearchButton(){
        return searchButton;
    }

    Button getMainMenuButton() {
        return mainMenuButton;
    }

    public String getEnterName() {
        return enterName.getText();
    }

    public void setDataSeries1(String name,int score) {
        dataSeries1.getData().add(new XYChart.Data(name,score));
    }

    public void setDataSeries2(String game, long time){
        dataSeries2.getData().add(new XYChart.Data(game, time));
    }
}