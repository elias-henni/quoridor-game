package quoridor.view.start;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
//import quoridor.quoridorModel.Database_connection;
import quoridor.quoridorModel.Game;
import quoridor.view.board.BoardPresenter;
import quoridor.view.board.BoardView;
import quoridor.view.instructions.InstructionsPresenter;
import quoridor.view.instructions.InstructionsView;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;

import java.sql.SQLException;
import java.util.Random;
import java.util.Optional;

public class StartPresenter {
    private Game model;
    private StartView view;
//    private Database_connection db;

    public StartPresenter (Game model, StartView view) {
        this.model = model;
        this.view = view;
//        this.db = model.getDatabase_connection();
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getMainMenuButton().setOnAction(event -> {
            changeToMainMenu();
        });

        view.getInstructions().setOnAction(actionEvent -> {
            changeToInstructions();
        });

        view.getPlayGameButton().setOnAction(event -> {
            if (!view.getEnterName().getText().equals("")) {
                // Sets player name from text field
                model.getPlayer().setName(view.getEnterName().getText());
//                try {
//                    // does name check to see if player already exists
//                    // If player exists it sets the playerID to the excising ID in the db
//                    db.nameCheck(model.getPlayer());
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }

                // if it does exist the playerID wont be set
                if (model.getPlayer().getPlayerID() != null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Is this you?");
                    alert.setHeaderText("This name is already in use, is this you?");
                    alert.setContentText("If no press no and enter a new name please");

                    ButtonType yes = new ButtonType("Yes!");
                    ButtonType no = new ButtonType("No!", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(yes, no);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == yes) {
                        changeToBoardView();
                    }
                }

                // if it does not exist
                else {
//                    try {
//                        model.getPlayer().setPlayerID(db.findID());
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    }
//                    db.insertPlayer(model.getPlayer().getPlayerID(), model.getPlayer().getName());
//                    System.out.println("Name saved");
                    changeToBoardView();
                }
            }
        });

    }

    private void updateView() {

    }

    private void changeToBoardView() {
        BoardView boardView = new BoardView();
        BoardPresenter startPresenter = new BoardPresenter(model, boardView);
        view.getScene().setRoot(boardView);
        boardView.getScene().getWindow();
    }

    private void changeToMainMenu() {
        MainMenuView menuView = new MainMenuView();
        MainMenuPresenter menuPresenter = new MainMenuPresenter(model, menuView);
        view.getScene().setRoot(menuView);
        menuView.getScene().getWindow();
    }

    private void changeToInstructions() {
        InstructionsView instructionsView = new InstructionsView();
        InstructionsPresenter menuPresenter = new InstructionsPresenter(model, instructionsView);
        view.getScene().setRoot(instructionsView);
        instructionsView.getScene().getWindow();
    }
}



/*
    When enter name:
        1 check if it exists
        2 if it exists pop up ‘is this you?’ (INT_PLAYER_DATA)
        3 if it is you then get player_ID (INT_PLAYER_DATA)

        3 if it isn’t you then ask for new name
        4 check if it exists (INT_PLAYER_DATA)
        5 if it does not get largest player_ID (INT_PLAYER_DATA)
        6 assign largest player_ID+1 to that name (INT_PLAYER_DATA)

        When start game:
        1 Check largest Game_ID corresponding to player_ID
        2 Assign largest Game_ID+1 to this game

        When finished:
        1 add game data to INT_GAME_DATA
*/

