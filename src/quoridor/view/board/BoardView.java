package quoridor.view.board;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import quoridor.quoridorModel.ColorPalette;
import quoridor.quoridorModel.Square;

import java.util.List;

public class BoardView extends BorderPane {
    private static final int COLUMNS = 9;
    private static final int ROWS = 9;

    private final double tileWidth;
    private final double tileHeight;
    private static final double CANVAS_WIDTH = 600.0;
    private static final double CANVAS_HEIGHT = 600.0;
    private Canvas boardCanvas;
    private Canvas wallsCanvas;
    private Canvas setWallCanvas;
    private Canvas setWallP2Canvas;

    private Label enemyWallsLabel;
    private Button playerWallsButton;
    private HBox topBar;
    private Ellipse playerPawn;
    private Ellipse AIPawn;
    private StackPane boardPane;
    private Button quickMenu;

    public BoardView() {
        this.tileWidth = CANVAS_WIDTH / COLUMNS;
        this.tileHeight = CANVAS_HEIGHT / ROWS;

        this.initialiseNodes();
        this.layoutNodes();
    }


    private void initialiseNodes() {
        this.enemyWallsLabel = new Label("Enemy walls: 10");
        this.playerWallsButton = new Button("Your walls: 10");
        this.playerPawn = new Ellipse(22, 22);
        this.AIPawn = new Ellipse(22, 22);
        this.topBar = new HBox();
        this.boardPane = new StackPane();
        this.boardCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.wallsCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.setWallCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.setWallP2Canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.quickMenu = new Button("Quick Menu");
    }

    private void layoutNodes() {
        // Adds all canvases to boardPane which is of type Stack Pane
        this.boardPane.getChildren().addAll(boardCanvas, wallsCanvas, setWallP2Canvas, setWallCanvas, AIPawn, playerPawn);

        this.topBar.getChildren().addAll(enemyWallsLabel, quickMenu, playerWallsButton);
        this.topBar.setSpacing(30);

        this.enemyWallsLabel.setTextAlignment(TextAlignment.CENTER);
        this.enemyWallsLabel.setStyle("-fx-font: 21 monospace;");
        this.enemyWallsLabel.setMinHeight(150);

        this.playerWallsButton.setMinSize(170, 60);
        this.playerWallsButton.setStyle("-fx-font: 21 monospace;");

        this.quickMenu.setMinSize(170, 60);
        this.quickMenu.setStyle("-fx-font: 21 monospace;");

        this.topBar.setAlignment(Pos.CENTER);
        GridPane.setMargin(enemyWallsLabel, new Insets(0, 20, 0, 20));
        GridPane.setMargin(playerWallsButton, new Insets(0, 20, 0 ,20));
        GridPane.setMargin(quickMenu, new Insets(0, 20, 0 ,20));
        setTop(topBar);

        setAlignment(boardPane, Pos.CENTER);
        setCenter(boardPane);

        this.playerPawn.setStroke(Color.BLACK);
        this.playerPawn.setStrokeWidth(4);

        this.AIPawn.setStroke(Color.BLACK);
        this.AIPawn.setStrokeWidth(4);
        this.AIPawn.setFill(Color.RED);

        this.setStyle(("-fx-background-color: #cecece;"));
    }

    // Display canvas is the lower most canvas on the Stack Pane
    // Display canvas draws the squares
    // Takes current position as parameters to color the current position square
    //Random r = new Random();
    //gc.setFill(Color.rgb(r.nextInt(55) + 201, r.nextInt(55) + 201,r.nextInt(55) + 201));
    void displayCanvas(List<Square> currentValidMovements, Color colorPalette) {
        final GraphicsContext gc = this.boardCanvas.getGraphicsContext2D();
        gc.fillRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);
        for (int column = 0; column < COLUMNS; column++) {
            for (int row = 0; row < ROWS; row++) {
                boolean neighborColor = false;
                for (Square neighbors : currentValidMovements) {
                    if (column == neighbors.getX() && row == neighbors.getY()) {
                        gc.setFill(Color.rgb(162, 242, 194));
                        neighborColor = true;
                    }
                }
                if (!neighborColor) {
                    gc.setFill(colorPalette);
                }
                gc.fillRect(column * tileWidth, row * tileHeight, tileWidth, tileHeight);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(4);
                gc.strokeRect(column * tileWidth, row * tileHeight, tileWidth, tileHeight);
            }
        }
    }
    // drawWall uses column, row, and wall orientation to draw a wall to the wallsCanvas
    void drawWall(int column, int row, boolean horizontalWall, ColorPalette colorPalette) {
        GraphicsContext gc = this.wallsCanvas.getGraphicsContext2D();
        if (horizontalWall) {
            gc.setStroke(colorPalette.getColor());
            gc.setLineWidth(8);
            gc.strokeLine(column * tileWidth, row * tileHeight, (column + 2) * tileWidth, row * tileHeight);
        } else {
            gc.setStroke(colorPalette.getColor());
            gc.setLineWidth(8);
            gc.strokeLine(column * tileWidth, row * tileHeight, column * tileWidth, (row + 2) * tileHeight);
        }
    }

    void eraseWalls() {
        GraphicsContext gc = this.wallsCanvas.getGraphicsContext2D();
        gc.clearRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    void eraseWallsMode() {
        GraphicsContext gc = this.setWallCanvas.getGraphicsContext2D();
        gc.clearRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    void eraseWallsModeP2() {
        GraphicsContext gc = this.setWallP2Canvas.getGraphicsContext2D();
        gc.clearRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    void setWallsMode(boolean enabled, List<Integer> x, List<Integer> y, ColorPalette colorPalette) {
        GraphicsContext gc = this.setWallCanvas.getGraphicsContext2D();
        int counter = 0;
        if (enabled) {
            for (int i = 1; i < COLUMNS; i++) {
                for (int j = 1; j < ROWS; j++) {
                    if (x.size() != counter && x.get(counter) == i && y.get(counter) == j) {
                        gc.setStroke(colorPalette.getColor());
                        gc.setLineWidth(20);
                        gc.strokeOval(66.66 * i, 66.66 * j, 1, 1);
                        counter++;
                    }
                }
            }
        } else {
            gc.clearRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);
        }
    }

    void setWallsP2Mode(boolean enabled, int x, int y, boolean horizontal, boolean vertical, ColorPalette colorPalette) {
        GraphicsContext gc = this.setWallP2Canvas.getGraphicsContext2D();
        if (enabled) {
            if (horizontal) {
                gc.setStroke(colorPalette.getColor());
                gc.setLineWidth(8);
                gc.strokeLine((x - 1) * tileWidth, y * tileHeight, (x + 1) * tileWidth, y * tileHeight);
            }
            if (vertical) {
                gc.setStroke(colorPalette.getColor());
                gc.setLineWidth(8);
                gc.strokeLine(x * tileWidth, (y - 1) * tileHeight, x * tileWidth, (y + 1) * tileHeight);
            }
        } else {
            gc.clearRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_HEIGHT);
        }
    }
    void setPawnColor(ColorPalette color) {
        this.playerPawn.setFill(color.getColor());
    }

    Button getQuickMenu() {
        return quickMenu;
    }

    Ellipse getAIPawn() {
        return AIPawn;
    }

    Label getAboutTitleText() {
        return enemyWallsLabel;
    }

    Button getMainMenuButton() {
        return playerWallsButton;
    }

    Ellipse getPlayerPawn() {
        return playerPawn;
    }

    Parent getBoardPane() {
        return boardPane;
    }

    Button getPlayerWallsButton() {
        return playerWallsButton;
    }

    Canvas getBoardCanvas() {
        return boardCanvas;
    }

    Canvas getWallsCanvas() {
        return wallsCanvas;
    }

    Canvas getSetWallCanvas() {
        return setWallCanvas;
    }

    Canvas getSetWallP2Canvas() {
        return setWallP2Canvas;
    }

     Label getEnemyWallsLabel() {
        return enemyWallsLabel;
    }

}

