package quoridor.view.stats;

//import quoridor.quoridorModel.Database_connection;
import quoridor.quoridorModel.Game;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsPresenter {

    private Game model;
    private StatsView view;
//    private Database_connection db;


    private String search;
    private int searchID;
    private int playerMoves;
    private int time;
    private int maxGameID;
    private int i = 1;
    private int lastGame;
    private int avgGame;

    public StatsPresenter(
            Game model, StatsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
//        db = new Database_connection();

    }

    private void addEventHandlers() {
        view.getMainMenuButton().setOnAction(actionEvent -> {
            changeToMainMenu();
        });
        view.getSearchButton().setOnAction(actionEvent -> {
         search = view.getEnterName();

//            try {
//                searchID();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            System.out.println(search);
//            try {
//                searchName();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
            view.setDataSeries1(search ,playerMoves);

//
//            try {
//                searchMaxGameID();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            for (int i = 1; i <= maxGameID; i++){
//                try {
//                    searchTime();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//
//                view.setDataSeries2(i, time);
//            }
//

//            try {
//                searchMaxGameID();
//            }catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            try {
//                searchLastGame();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            view.setDataSeries2("Last Game" , lastGame);
//
//
//            try {
//                searchAvgGame();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            view.setDataSeries2("Avg Game", avgGame);


            updateView();
        });
    }
    private void updateView() {

    }

    private void changeToMainMenu() {
        MainMenuView menuView = new MainMenuView();
        MainMenuPresenter menuPresenter = new MainMenuPresenter(model, menuView);
        view.getScene().setRoot(menuView);
        menuView.getScene().getWindow();
    }



//    public int searchName() throws SQLException {
//        String query = "SELECT min(SCORE) AS SCORE \n" +
//                "FROM INT_GAME_DATA\n" +
//                "WHERE PLAYER_ID LIKE'" + searchID + "'" +
//                "AND GAMES_OUTCOME LIKE 1";
//        try (Connection conn = db.connect();
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                playerMoves = rs.getInt("SCORE");
//            }
//        }catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return playerMoves;
//    }


//    public int searchID() throws SQLException {
//        String query = "SELECT PLAYER_ID\n" +
//                "FROM INT_PLAYER\n" +
//                "WHERE PLAYER_NAME LIKE '" + search + "'";
//        try (Connection conn = db.connect();
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                searchID = rs.getInt("player_ID");
//            }
//        }catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return searchID;
//    }



//    public int searchTime() throws SQLException {
//            String query = "SELECT  SUM(duration) AS DURATION\n" +
//                    "FROM INT_MOVES_DATA\n" +
//                    "WHERE player_id LIKE \n'" + searchID  + "'" +
//                    "AND game_id LIKE '" + i +  "'";
//
//            try (Connection conn = db.connect();
//                 Statement stmt = conn.createStatement()) {
//
//                ResultSet rs = stmt.executeQuery(query);
//                while (rs.next()) {
//                    time = rs.getInt("DURATION");
//                }
//            }catch(SQLException throwables){
//                throwables.printStackTrace();
//            }
//            return time;
//    }

//    public int searchMaxGameID() throws SQLException {
//        String query = "SELECT MAX(GAME_ID) as GAME_ID\n" +
//                "FROM INT_GAME_DATA\n" +
//                "WHERE PLAYER_ID LIKE '" + searchID + "'";
//        try (Connection conn = db.connect();
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                searchID = rs.getInt("GAME_ID");
//            }
//        }catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return searchID;
//    }


//    public int searchLastGame() throws SQLException {
//        String query = "SELECT SCORE \n" +
//                "FROM INT_GAME_DATA\n" +
//                "WHERE PLAYER_ID LIKE '" + searchID + "' \n" +
//                "AND WHERE GAME_ID LIKE '" + maxGameID + "'";
//        try (Connection conn = db.connect();
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                lastGame = rs.getInt("SCORE");
//            }
//        }catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return lastGame;
//    }

//    public int searchAvgGame() throws SQLException {
//        String query = "SELECT AVG(SCORE) AS SCORE \n" +
//                "FROM INT_GAME_DATA\n" +
//                "WHERE PLAYER_ID LIKE '" + searchID + "'";
//        try (Connection conn = db.connect();
//             Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                avgGame = rs.getInt("SCORE");
//            }
//        }catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return avgGame;
//    }


}
