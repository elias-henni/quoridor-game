package quoridor.view.board;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import quoridor.quoridorModel.*;
import quoridor.view.gameOver.GameOverPresenter;
import quoridor.view.gameOver.GameOverView;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;
//import quoridor.view.sound.SoundPlayer;

import java.sql.SQLException;
import java.util.*;

public class BoardPresenter {
    private Game model;
    private BoardView view;

    public BoardPresenter (Game model, BoardView view) {
        this.model = model;
        this.view = view;
        setPawnStartPosition();
        setAIStartPosition();
        addEventHandlers();
        updateView();

        this.model.getPlayer().setScore(0); //Resets the score to 0 when the game starts.
        view.getEnemyWallsLabel().setText("Enemy walls: " + model.getCom().getFences());
        // Creates the gameID for this game.
//        try {
//            this.model.getPlayer().setGameID(this.model.getDatabase_connection().getNewGameID(this.model.getPlayer().getPlayerID()));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        System.out.println("This is the id " + this.model.getPlayer().getGameID());
    }
    private void addEventHandlers() {
        // Set on mouse dragged is used to pick up the pawn and update its location
        // Is run every pixel dragged so the updateView is called to give the drag effect
        view.getPlayerPawn().setOnMouseDragged(e -> {
            // Sets the translate of X and Y to itself plus the change in X and Y from getX and getY
            view.getPlayerPawn().setTranslateX(view.getPlayerPawn().getTranslateX() + e.getX());
            view.getPlayerPawn().setTranslateY(view.getPlayerPawn().getTranslateY() + e.getY());
            // Updates the mouse X and Y position of the player object
            model.getPlayer().getPawn().setCanvasCordX(view.getPlayerPawn().getTranslateX());
            model.getPlayer().getPawn().setCanvasCordY(view.getPlayerPawn().getTranslateY());

            // Prints for testing
            //System.out.printf("X%s Y%s\n", model.getPlayer().getMouseX(), model.getPlayer().getMouseY());
            updateView();
        });

        view.getPlayerPawn().setOnMouseReleased(e -> {
            // score is now only movement
            model.getPlayer().setScore(model.getPlayer().getScore() + 1);


            // Checks if the movement attempt is valid
            // -- By passing the current square of the pawn on the board
            // -- And passing the next intended square
            // ---- Which is the location of last hover turned into row and column using
            // ---- TranslateXToColumn and TranslateYToRow
            if (model.isValidDirection(model.getBoard().getSquare(model.getPlayer().getPawn().getCurrentPosition().getX(), model.getPlayer().getPawn().getCurrentPosition().getY()),
                    model.getBoard().getSquare(translateXToColumn(model.getPlayer().getPawn().getCanvasCordX()), translateYToRow(model.getPlayer().getPawn().getCanvasCordY())))) {

                // Gets Coordinate and turns into Column and Row
                int newColumn = translateXToColumn(model.getPlayer().getPawn().getCanvasCordX());
                int newRow = translateYToRow(model.getPlayer().getPawn().getCanvasCordY());
                Square newSquare = new Square(newColumn, newRow);
                // Sets the new player X and Y Coords
                // By getting column and row number from variables newColumn and newRow
                // Then turning them back into X and Y that places the pawn perfectly in the center of a given square
                model.getPlayer().getPawn().setCanvasCordX(translateColumnToX(newColumn));
                model.getPlayer().getPawn().setCanvasCordY(translateRowToY(newRow));
//                System.out.println("new column" + newColumn);
//                System.out.println("new row" + newRow);

                // Sets new square position to player object
                model.getPlayer().movePawn(newSquare);
                model.getPlayer().movePawn2(newColumn,newRow);

                model.endTurn(model.getPlayer(), new Movement(model.getBoard().getSquare(model.getPlayer().getPawn().getLastPosition().getX(), model.getPlayer().getPawn().getLastPosition().getY()),
                           model.getBoard().getSquare(model.getPlayer().getPawn().getCurrentPosition().getX(), model.getPlayer().getPawn().getCurrentPosition().getY())), null);

//                SoundPlayer.playPieceSound();


            } else {
//                System.out.println("Cant move there");
                // If the movement is not valid then the players X and Y location is set back to last board position
                model.getPlayer().getPawn().setCanvasCordX(translateColumnToX(model.getPlayer().getPawn().getCurrentPosition().getX()));
                model.getPlayer().getPawn().setCanvasCordY(translateRowToY(model.getPlayer().getPawn().getCurrentPosition().getY()));

                int newColumn = translateXToColumn(model.getPlayer().getPawn().getCanvasCordX());
                int newRow = translateYToRow(model.getPlayer().getPawn().getCanvasCordY());

                model.getPlayer().getPawn().setCanvasCordX(translateColumnToX(newColumn));
                model.getPlayer().getPawn().setCanvasCordY(translateRowToY(newRow));
//                System.out.println("new column1 " + newColumn);
//                System.out.println("new row1 " + newRow);
                model.getPlayer().movePawn2(newColumn, newRow);
            }

            // If player reaches the other side -> Send them to end game view
            if (model.getPlayer().getPawn().getCurrentPosition().getY() == 0) {
                // This is a win so gamesOutcome is set to 1
//                System.out.println(model.getPlayer().getTurnsTaken());
                model.getPlayer().setGameOutcome(1);
                changeToEndGameView();
            }




//            System.out.println(model.getPlayer().getPawn().getCurrentX() + " " + model.getPlayer().getPawn().getCurrentY());
//            System.out.println(model.getPlayer().getPawn().getLastX() + " " + model.getPlayer().getPawn().getLastY());
//
//            System.out.println(model.getPlayer().getPawn().getCurrentX() - model.getPlayer().getPawn().getLastX());
//            System.out.println(model.getPlayer().getPawn().getCurrentY() - model.getPlayer().getPawn().getLastY());

            if (!(model.getPlayer().getPawn().getCurrentX() - model.getPlayer().getPawn().getLastX() == 0 &&
            model.getPlayer().getPawn().getCurrentY() - model.getPlayer().getPawn().getLastY() == 0)) {

                //RULE: First move always move froward!!!
                if(model.getPlayer().getTurnsTaken().size() == 2){
                    model.getOpponentAI().moveAI();
//                System.out.println("first move");
                }

                //RULE: Only move when AI path is shorter
                else if (model.getOpponentAI().getAIPath() < model.getOpponentAI().getPlayerPath() || model.getCom().getFences() == 0){
//                System.out.println("AI: " + model.getOpponentAI().getAIPath());
//                System.out.println("Player: " + model.getOpponentAI().getPlayerPath());
                    model.getOpponentAI().moveAI();
                }

                //RULE: Wall rules in OpponentAI.java
                else{
                    Fence fence = model.getOpponentAI().setWallAI();
                    if (model.getOpponentAI().checkIfRight()){
                        if (model.getOpponentAI().Horizontal()){
                            view.drawWall(fence.getX(), fence.getY() + 1, true,model.getBoard().getWallColor());
                            view.getEnemyWallsLabel().setText("Enemy walls: " + model.getCom().getFences());
                        }
                        else view.drawWall(fence.getX() + 1, fence.getY(), false, model.getBoard().getWallColor());
                        model.getCom().setFences(model.getCom().getFences() - 1);
                        view.getEnemyWallsLabel().setText("Enemy walls: " + model.getCom().getFences());
                        updateView();
                    }
                }

            }




            updateView();
            if(model.getOpponentAI().getAILocationY() == 8){
                model.getPlayer().setGameOutcome(0);
                model.getPlayer().getTurnsTaken().remove(model.getPlayer().getTurnsTaken().size() - 1);
                changeToEndGameView();
            }
        });

        // For now just subtracts one wall when wall is pressed
        view.getPlayerWallsButton().setOnAction(actionEvent -> {
            if (!(model.getPlayer().getFences() == 0)) {
                view.setWallsMode(true, model.currentValidWallCrosses(true), model.currentValidWallCrosses(false), model.getBoard().getWallColor());
                view.getSetWallCanvas().setDisable(false);
                view.getPlayerWallsButton().setDisable(true);
            }
            updateView();
        });

        // If you click the pawn you will go out of wall placement mode
        view.getPlayerPawn().setOnMouseClicked(mouseEvent -> {
            view.setWallsMode(false, model.currentValidWallCrosses(true), model.currentValidWallCrosses(false),  model.getBoard().getWallColor());
            view.getSetWallCanvas().setDisable(true);
            view.getPlayerWallsButton().setDisable(false);
            clearWallsP2Mode();
            updateView();
        });

        view.getSetWallCanvas().setOnMouseClicked(mouseEvent -> {
            if (validPickingWallPointP1(mouseEvent.getX(), mouseEvent.getY())) {
                view.setWallsMode(false, model.getGreenDotX(), model.getGreenDotY(),  model.getBoard().getWallColor());
                view.getSetWallCanvas().setDisable(true);
                view.getSetWallP2Canvas().setDisable(false);
                view.setWallsP2Mode(true, model.getPlayer().getCurrentTurn().getCurrentWallCrossX(), model.getPlayer().getCurrentTurn().getCurrentWallCrossY(),
                        model.validWallOfCross(model.getPlayer().getCurrentTurn().getCurrentWallCrossX(), model.getPlayer().getCurrentTurn().getCurrentWallCrossY(), true),
                        model.validWallOfCross(model.getPlayer().getCurrentTurn().getCurrentWallCrossX(), model.getPlayer().getCurrentTurn().getCurrentWallCrossY(), false),  model.getBoard().getWallColor());
            }
            updateView();
        });

        view.getSetWallP2Canvas().setOnMouseClicked(mouseEvent -> {
            if (validPickingWallPointP2(mouseEvent.getX(), mouseEvent.getY())) {
                model.getPlayer().setFences(model.getPlayer().getFences() - 1);
                view.getPlayerWallsButton().setText("Your walls: " + model.getPlayer().getFences());
                clearWallsP2Mode();
            }
            updateView();


            // If player reaches the other side -> Send them to end game view
            if (model.getPlayer().getPawn().getCurrentPosition().getY() == 0) {
                // This is a win so gamesOutcome is set to 1
//                System.out.println(model.getPlayer().getTurnsTaken());
                model.getPlayer().setGameOutcome(1);
                changeToEndGameView();
            }

            //RULE: First move always move froward!!!
            if(model.getPlayer().getTurnsTaken().size() == 2){
                model.getOpponentAI().moveAI();
//                System.out.println("first move");
            }

            //RULE: Only move when AI path is shorter
            else if (model.getOpponentAI().getAIPath() < model.getOpponentAI().getPlayerPath() || model.getCom().getFences() == 0){
//                System.out.println("AI: " + model.getOpponentAI().getAIPath());
//                System.out.println("Player: " + model.getOpponentAI().getPlayerPath());
                model.getOpponentAI().moveAI();
            }

            //RULE: Wall rules in OpponentAI.java
            else{
                Fence fence = model.getOpponentAI().setWallAI();
                if (model.getOpponentAI().checkIfRight()){
                    if (model.getOpponentAI().Horizontal()){
                        view.drawWall(fence.getX(), fence.getY() + 1, true,model.getBoard().getWallColor());
                        view.getEnemyWallsLabel().setText("Enemy walls: " + model.getCom().getFences());
                    }
                    else view.drawWall(fence.getX() + 1, fence.getY(), false, model.getBoard().getWallColor());
                    model.getCom().setFences(model.getCom().getFences() - 1);
                    view.getEnemyWallsLabel().setText("Enemy walls: " + model.getCom().getFences());
                    updateView();
                }
            }

            updateView();
            if(model.getOpponentAI().getAILocationY() == 8){
                model.getPlayer().setGameOutcome(0);
                model.getPlayer().getTurnsTaken().remove(model.getPlayer().getTurnsTaken().size() - 1);
                changeToEndGameView();
            }
        });

        view.getQuickMenu().setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Quick Menu");
            alert.setHeaderText("Quick Menu Options");
            alert.setContentText("Note that if you navigate to another page your current game data will be lost");

            ButtonType mainMenu = new ButtonType("Main menu");
            ButtonType restart = new ButtonType("Restart");
            ButtonType goBack = new ButtonType("Go back", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(mainMenu, restart, goBack);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == mainMenu) {
                changeToMainMenu();
            } else if (result.isPresent() && result.get() == restart) {
                resetGame();
            }
        });
    }

    private void updateView() {
        view.getPlayerPawn().setTranslateX(model.getPlayer().getPawn().getCanvasCordX());
        view.getPlayerPawn().setTranslateY(model.getPlayer().getPawn().getCanvasCordY());

        view.displayCanvas(model.currentValidMovements(), model.getBoard().getBoardColor().getColor());

        view.getPlayerWallsButton().setText("Your walls: " + model.getPlayer().getFences());
        setAIPosition();
    }

    private void setPawnStartPosition() {
        model.getPlayer().getPawn().setCanvasCordX(translateColumnToX(4));
        model.getPlayer().getPawn().setCanvasCordY(translateRowToY(8));
        view.getPlayerPawn().setTranslateX(model.getPlayer().getPawn().getCanvasCordX());
        view.getPlayerPawn().setTranslateY(model.getPlayer().getPawn().getCanvasCordY());

        view.displayCanvas(model.currentValidMovements(), model.getBoard().getBoardColor().getColor());
        view.setPawnColor(model.getBoard().getPawnColor());

        view.getSetWallCanvas().setDisable(true);
        view.getSetWallP2Canvas().setDisable(true);
        model.startTurn(model.getPlayer());
    }

    private void setAIStartPosition(){
        model.getOpponentAI().setAILocationX(4);
        model.getOpponentAI().setAILocationY(0);
        view.getAIPawn().setTranslateX(translateColumnToX(model.getOpponentAI().getAILocationX()));
        view.getAIPawn().setTranslateY(translateRowToY(model.getOpponentAI().getAILocationY()));
    }

    private void setAIPosition(){
        view.getAIPawn().setTranslateX(translateColumnToX(model.getOpponentAI().getAILocationX()));
        view.getAIPawn().setTranslateY(translateRowToY(model.getOpponentAI().getAILocationY()));
    }


    // TODO not sure if validPickingWallPointP1 and validPickingWallPointP2 should be moved
    // TODO most likely will be but not sure to where
    private boolean validPickingWallPointP1(double x, double y) {
        boolean isValid = false;
        for (int column = 1; column < 9; column++) {
            for (int row = 1; row < 9; row++) {
                if (((61 + (66 * (column - 1))) < x && x < (74 + (66 * (column - 1)))) &&
                        ((61 + (66 * (row - 1))) < y && y < (74 + (66 * (row - 1)))) && model.validWallCross(column,row)) {
                    isValid = true;
                    model.getPlayer().getCurrentTurn().setCurrentWallCross(column,row);
                    break;
                }
            }
        }
        return isValid;
    }

    private boolean validPickingWallPointP2(double x, double y) {

        PathFinderAI pathFinderAI = new PathFinderAI(model);
        boolean isValid = false;
        int pointX = (int) (model.getPlayer().getCurrentTurn().getCurrentWallCrossX() * 66.66);
        int pointY = (int) (model.getPlayer().getCurrentTurn().getCurrentWallCrossY() * 66.66);
        if ((((pointX - 66.66 < x && x < pointX - 4) && (pointY - 4 < y && y < pointY + 4)) ||
                ((pointX + 4 < x && x < pointX + 66.66) && (pointY - 4 < y && y < pointY + 4)))
                && model.validWallOfCross(model.getPlayer().getCurrentTurn().getCurrentWallCrossX(), model.getPlayer().getCurrentTurn().getCurrentWallCrossY(), true)) {
            isValid = true;
            model.getBoard().setFence(model.getPlayer().getCurrentTurn().getCurrentWallCrossX() - 1, model.getPlayer().getCurrentTurn().getCurrentWallCrossY() - 1, true);


//            checkWallPathAI();
//            checkWallPlayer();
            if (!(pathFinderAI.endSquareFound(model.getBoard().getSquare(model.getPlayer().getPawn().getCurrentPosition().getX(),
                    model.getPlayer().getPawn().getCurrentPosition().getY()), true)) || !(pathFinderAI.endSquareFound(model.getBoard().getSquare(model.getOpponentAI().getAILocationX(),
                    model.getOpponentAI().getAILocationY()), false))) {
//            if (wallPathAI <=0 || wallPathPlayer <= 0) {
                model.getBoard().removeFence(model.getPlayer().getCurrentTurn().getCurrentWallCrossX() - 1, model.getPlayer().getCurrentTurn().getCurrentWallCrossY(), true);
//                System.out.println("This wall would block the path");
                clearWallsP2Mode();
                isValid = false;
            } else {
                view.drawWall(model.getPlayer().getCurrentTurn().getCurrentWallCrossX() - 1, model.getPlayer().getCurrentTurn().getCurrentWallCrossY(), true, model.getBoard().getWallColor());
                model.endTurn(model.getPlayer(), null, model.getBoard().getFence(model.getPlayer().getCurrentTurn().getCurrentWallCrossX() - 1, model.getPlayer().getCurrentTurn().getCurrentWallCrossY() - 1, true));
            }
        } else if ((((pointY - 66.66 < y && y < pointY - 4) && (pointX - 4 < x && x < pointX + 4)) ||
                ((pointY + 4 < y && y < pointY + 66.66) && (pointX - 4 < x && x < pointX + 4)))
                && model.validWallOfCross(model.getPlayer().getCurrentTurn().getCurrentWallCrossX(), model.getPlayer().getCurrentTurn().getCurrentWallCrossY(), false)) {
            isValid = true;
            model.getBoard().setFence(model.getPlayer().getCurrentTurn().getCurrentWallCrossX() - 1, model.getPlayer().getCurrentTurn().getCurrentWallCrossY() - 1, false);

//            checkWallPlayer();
//            checkWallPathAI();
            if (!(pathFinderAI.endSquareFound(model.getBoard().getSquare(model.getPlayer().getPawn().getCurrentPosition().getX(),
                    model.getPlayer().getPawn().getCurrentPosition().getY()), true)) || !(pathFinderAI.endSquareFound(model.getBoard().getSquare(model.getOpponentAI().getAILocationX(),
                    model.getOpponentAI().getAILocationY()), false))) {
//            if (wallPathAI <= 0 || wallPathPlayer <= 0) {
                model.getBoard().removeFence(model.getPlayer().getCurrentTurn().getCurrentWallCrossX() - 1, model.getPlayer().getCurrentTurn().getCurrentWallCrossY() - 1, false);
//                System.out.println("This wall would block the path");
                clearWallsP2Mode();
                isValid = false;
            } else {
                view.drawWall(model.getPlayer().getCurrentTurn().getCurrentWallCrossX(), model.getPlayer().getCurrentTurn().getCurrentWallCrossY() - 1, false, model.getBoard().getWallColor());
                model.endTurn(model.getPlayer(), null, model.getBoard().getFence(model.getPlayer().getCurrentTurn().getCurrentWallCrossX() - 1, model.getPlayer().getCurrentTurn().getCurrentWallCrossY() - 1, false));
            }
        }
        return isValid;
    }

    private void clearWallsP2Mode() {
        view.setWallsP2Mode(false,0,0, false, false,  model.getBoard().getWallColor());
        view.getSetWallP2Canvas().setDisable(true);
        view.getPlayerWallsButton().setDisable(false);
        view.getPlayerPawn().setDisable(false);
    }

    // Takes an X coordinate and turns it into a column value (0-8)
    private int translateXToColumn(final double x) {
        final double width = this.view.getBoardCanvas().getWidth();
        final int columnResult = (int)((x + 300) / width * 9);
        if (columnResult >= 0 && columnResult < 9) {
            return columnResult;
        }
        else {
            return model.getPlayer().getPawn().getCurrentPosition().getX();
        }
    }

    // Takes a Y coordinate and turns it into a row value (0-8)
    private int translateYToRow(final double y) {
        final double height = this.view.getBoardCanvas().getHeight();
        final int rowResult = (int)((y + 300) / height * 9);
        if (rowResult >= 0 && rowResult < 9) {
            return rowResult;
        }
        else {
            return model.getPlayer().getPawn().getCurrentPosition().getY();
        }
    }

    // Takes a column value and turns it into the corresponding X coordinate
    private int translateColumnToX(final int x) {
        return (int)(((x * 66.66) + 33.33) - 300);
    }

    // Takes a row value and turns it into the corresponding Y coordinate
    private int translateRowToY(final int y) {
        return (int)(((y * 66.66) + 33.33) - 300);
    }


    // Changes to End Game View
    private void changeToEndGameView() {
        GameOverView gameOverView = new GameOverView();
        GameOverPresenter gameOverPresenter = new GameOverPresenter(model, gameOverView);
        view.getScene().setRoot(gameOverView);
        gameOverView.getScene().getWindow();
//        System.out.println("wants to change");
    }

    private void changeToMainMenu() {
        MainMenuView menuView = new MainMenuView();
        MainMenuPresenter menuPresenter = new MainMenuPresenter(new Game(new Player(PlayerType.USER, null,null, 10), new Board(9,9)), menuView);
        view.getScene().setRoot(menuView);
        menuView.getScene().getWindow();
    }

    private void resetGame() {
        Integer playerID = model.getPlayer().getPlayerID();
        String name = model.getPlayer().getName();
        Integer gameID = model.getPlayer().getGameID();
        ColorPalette boardColor = model.getBoard().getBoardColor();
        ColorPalette pawnColor = model.getBoard().getPawnColor();
        ColorPalette wallColor = model.getBoard().getWallColor();
        int amountOfFences = model.getAmountOfFencesPerPlayer();
        model = new Game(new Player(PlayerType.USER, playerID,name, amountOfFences), new Board(9,9));
        model.getCom().setFences(amountOfFences);
        view.getEnemyWallsLabel().setText("Enemy walls: " + model.getCom().getFences());
        model.getPlayer().setGameID(gameID);
        model.setAmountOfFencesPerPlayer(amountOfFences);
        model.getBoard().setBoardColor(boardColor);
        model.getBoard().setPawnColor(pawnColor);
        model.getBoard().setWallColor(wallColor);
        view.eraseWalls();
        view.eraseWallsMode();
        view.eraseWallsModeP2();
        view.getPlayerWallsButton().setDisable(false);
        setPawnStartPosition();
        setAIStartPosition();
        updateView();
    }

    private int wallPathPlayer;
    private int wallPathPlayerx;
    private void checkWallPlayer(){
        PathFinderAI pathFinderAI = new PathFinderAI(model);
        pathFinderAI.findPath(model.getPlayer().getPawn().getCurrentPosition().getX(), model.getPlayer().getPawn().getCurrentPosition().getY(), model.getBoard().getSquare(4,8));

        List<Integer> wallPlayer = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            wallPlayer.add(pathFinderAI.getBoard().getSquare(x, 0).getGCost());
        }
        wallPathPlayerx = wallPlayer.indexOf(Collections.max(wallPlayer));
        wallPathPlayer = wallPlayer.get(wallPathPlayerx);

//        System.out.println("player : " + wallPathPlayer);
    }

    private int wallPathAI;
    private int wallPathAIx;
    private void checkWallPathAI(){
        PathFinderAI pathFinderAI = new PathFinderAI(model);
        pathFinderAI.findPath(model.getOpponentAI().getAILocationX(),model.getOpponentAI().getAILocationY(), model.getBoard().getSquare(4,8));

        List<Integer> wallAI = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            wallAI.add(pathFinderAI.getBoard().getSquare(x, 8).getGCost());
        }
        wallPathAIx = wallAI.indexOf(Collections.max(wallAI));
        wallPathAI = wallAI.get(wallPathAIx);

//        System.out.println("AI : " + wallPathAI);
    }
}
