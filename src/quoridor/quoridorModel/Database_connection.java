//package quoridor.quoridorModel;
//
//import java.io.File;
//import java.sql.*;
//
///**
// * creates connection and interaction with database
// * @author Esteban Verschueren
// */
//public class Database_connection {
//    // CONNECTION
//    public Connection connect() throws SQLException {
//        File wallet = new File("Wallet_QuoridorDB");
//        String url = "jdbc:oracle:thin:@quoridordb_medium?TNS_ADMIN=" + wallet;
//        String uName = "ADMIN";
//        String uPass = "QuoridorDigital1";
//        return DriverManager.getConnection(url, uName, uPass);
//    }
//
//    // Finds the next ID possible to assign to new player
//    public Integer findID() throws SQLException {
//        Integer playerID = null;
//        String query = "SELECT MAX(PLAYER_ID) AS PLAYER_ID\n" +
//                "FROM INT_PLAYER";
//        try (Connection conn = connect();
//              Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                playerID = rs.getInt("PLAYER_ID") + 1;
//            }
//        } catch(SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return playerID;
//    }
//
//    // Adds new player into the db, with name and playerID
//    public void insertPlayer(int playerID, String playerName) {
//        String SQL = "INSERT INTO INT_PLAYER(PLAYER_ID,PLAYER_NAME)" +
//                "VALUES (?,?)";
//        try (Connection conn = connect();
//             PreparedStatement cx = conn.prepareStatement(SQL)) {
//
//            cx.setInt(1, playerID);
//            cx.setString(2, playerName);
//            cx.execute();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    // Sets player game at the end of the game
//    public void setPlayerGameData (int gameID, int score, int gameOutcome, int playerID) {
//        String SQL = "INSERT INTO INT_GAME_DATA(GAME_ID,SCORE, GAMES_OUTCOME, PLAYER_ID)" +
//                "VALUES (?,?,?,?)";
//        try (Connection conn = connect();
//             PreparedStatement cx = conn.prepareStatement(SQL)) {
//
//            cx.setInt(1, gameID);
//            cx.setInt(2, score);
//            cx.setInt(3, gameOutcome);
//            cx.setInt(4, playerID);
//            cx.execute();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    // TODO Will be fore top score or leaderboard
//    public String topScore() throws SQLException {
//        String playerName = null;
//        String query = "select PLAYER_NAME from INT_PLAYER where PLAYER_ID like 1";
////                "select PLAYER_ID, PLAYER_NAME, PLAYER_MOVES" +
////                "    from INT_PLAYER\n" +
////                "    order by PLAYER_SCORE )\n" +
////                "where ROWNUM <= 5";
//        try ( Connection conn = connect();
//              Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                playerName = rs.getString("PLAYER_NAME");
//            }
//        }catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return playerName;
//    }
//
//    // Checks if name is already in db
//    public void nameCheck(Player player) throws SQLException {
//        String query = "SELECT PLAYER_ID\n" +
//                "FROM INT_PLAYER\n" +
//                "WHERE PLAYER_NAME like'" + player.getName() + "'";
//
//        try (Connection conn = connect();
//              Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                player.setPlayerID(rs.getInt("PLAYER_ID"));
//            }
//        } catch(SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    // Goes through the players game IDs in db and returns the next gameID in available
//    public Integer getNewGameID(int playerID) throws SQLException {
//        Integer gameID = null;
//        String query = "SELECT MAX(GAME_ID) AS GAME_ID\n" +
//                "FROM INT_GAME_DATA\n" +
//                "WHERE PLAYER_ID LIKE '" + playerID + "'";
//        try ( Connection conn = connect();
//              Statement stmt = conn.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                gameID = rs.getInt("GAME_ID");
//                if (gameID == 0){
//                    gameID = 1;
//                }
//                else gameID = gameID + 1;
//            }
//        } catch(SQLException throwables){
//            throwables.printStackTrace();
//        }
//        return gameID;
//    }
//
//    public void insertMoveData(int move, long duration, int player_ID, int game_ID, int move_type){
//        String SQL = "INSERT INTO INT_MOVES_DATA(MOVE,DURATION, PLAYER_ID, GAME_ID, MOVE_TYPE)" +
//                "VALUES (?,?,?,?,?)";
//        try (Connection conn = connect();
//             PreparedStatement cx = conn.prepareStatement(SQL)) {
//
//            cx.setInt(1, move);
//            cx.setLong(2, duration);
//            cx.setInt(3, player_ID);
//            cx.setInt(4, game_ID);
//            cx.setInt(5,move_type);
//            cx.execute();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//}
