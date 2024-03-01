package quoridor.view.gameOver;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import quoridor.quoridorModel.*;
import quoridor.view.board.BoardPresenter;
import quoridor.view.board.BoardView;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class GameOverPresenter {
    private Game model;
    private GameOverView view;
//    private Database_connection db;

    public GameOverPresenter(
            Game model, GameOverView view) {
        this.model = model;
        this.view = view;
//        this.db = model.getDatabase_connection();
        if (this.model.getPlayer().getGameOutcome() == 0) {
            view.gameOverOutcome(false);
        } else if(this.model.getPlayer().getGameOutcome() == 1) {
            view.gameOverOutcome(true);
        }
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getMainMenuButton().setOnAction(event -> changeToMainMenuView());
        view.getPlayAgainButton().setOnAction(actionEvent -> playAgain());
        view.getExitButton().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setContentText("If you exit all game data will be lost");

            ButtonType yes = new ButtonType("Yes!");
            ButtonType no = new ButtonType("No!");

            alert.getButtonTypes().setAll(yes,no);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yes) {
                view.getScene().getWindow().hide();
            }
        });
    }

    private void changeToMainMenuView() {
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuPresenter startPresenter = new MainMenuPresenter(new Game(new Player(PlayerType.USER, null,null, 10), new Board(9,9)), mainMenuView);
        view.getScene().setRoot(mainMenuView);
        mainMenuView.getScene().getWindow();
    }

    private void playAgain() {
        Integer playerID = model.getPlayer().getPlayerID();
        String name = model.getPlayer().getName();
        Integer gameID = model.getPlayer().getGameID();
        ColorPalette boardColor = model.getBoard().getBoardColor();
        ColorPalette pawnColor = model.getBoard().getPawnColor();
        ColorPalette wallColor = model.getBoard().getWallColor();
        int amountOfFences = model.getAmountOfFencesPerPlayer();
        model = new Game(new Player(PlayerType.USER, playerID,name, amountOfFences), new Board(9,9));
        model.getPlayer().setGameID(gameID);
        model.setAmountOfFencesPerPlayer(amountOfFences);
        model.getBoard().setBoardColor(boardColor);
        model.getBoard().setPawnColor(pawnColor);
        model.getBoard().setWallColor(wallColor);
        BoardView boardView = new BoardView();
        BoardPresenter startPresenter = new BoardPresenter(model, boardView);
        view.getScene().setRoot(boardView);
        boardView.getScene().getWindow();
    }

    private void updateView() {


//        System.out.println(model.getPlayer().getGameID() + " " + model.getPlayer().getScore() + " " +
//                model.getPlayer().getGameOutcome() + " " + model.getPlayer().getPlayerID());

//        try {
//            db.connect();
//            db.setPlayerGameData(model.getPlayer().getGameID(), model.getPlayer().getScore(),
//                    model.getPlayer().getGameOutcome(), model.getPlayer().getPlayerID());
//            view.getErrorText().setText("Saved Player data");
//        } catch (SQLException e) {
//            System.out.println("Didn't save data");
//        }
//
//        try {
//            db.connect();
//            for (int y = 0; y <model.getPlayer().getTurnsTaken().size(); y++){
//                db.insertMoveData(y, model.getPlayer().getTurnsTaken().get(y).getDuration().toMillis(),
//                        model.getPlayer().getPlayerID(),
//                        model.getPlayer().getGameID(),
//                        movementType(y));
////                System.out.println(y);
//                view.setDataSeries1(y,model.getPlayer().getTurnsTaken().get(y).getDuration().toMillis());
//
//                view.setAverage(view.getAverage() + model.getPlayer().getTurnsTaken().get(y).getDuration().toMillis());
//            }
//        } catch (SQLException e) {
//            System.out.println("Didn't save moves");
//        }

        view.getPlayerNameAndMoves().setText("Average time for moves: "
                + view.getAverage() / model.getPlayer().getTurnsTaken().size() + "ms \n" +
                "Amount of moves: " + model.getPlayer().getTurnsTaken().size());
    }
    public int movementType(int y){
        if (model.getPlayer().getTurnsTaken().get(y).getMovement() == null){
            return 0;
        }
        return 1;
    }




}
