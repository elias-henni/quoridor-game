package quoridor.quoridorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * stores player data
 * @author Elias henni, Esteban Verschueren, Kevin degan
 */
public class Player {
    private Pawn pawn;
    private String name;
    private int fences;

    private Integer gameID;
    private int score;
    private int gameOutcome;
    private Integer playerID;

    private List<Turn> turnsTaken = new ArrayList<>();

    public Player(PlayerType playerType, Integer playerID, String name, int amountOfFences) {
        this.name = name;
        this.fences = amountOfFences;
        this.playerID = playerID;
        this.pawn = new Pawn(playerType);
    }

    public Player(PlayerType playerType, Integer playerID, String name, Integer gameID, int amountOfFences) {
        this.name = name;
        this.fences = amountOfFences;
        this.playerID = playerID;
        this.gameID = gameID;
        this.pawn = new Pawn(playerType);
    }

    public Turn getCurrentTurn() {
        return turnsTaken.get(turnsTaken.size() - 1);
    }

    public void movePawn(Square nextSquare) {
        pawn.setCurrentPosition(nextSquare);
    }

    public void movePawn2(int x, int y){
        pawn.setCurrentX(x);
        pawn.setCurrentY(y);
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public int getFences() {
        return fences;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFences(int fences) {
        this.fences = fences;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameOutcome(int gameOutcome) {
        this.gameOutcome = gameOutcome;
    }

    public Integer getPlayerID() {
        return playerID;
    }

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public int getScore() {
        return score;
    }

    public int getGameOutcome() {
        return gameOutcome;
    }

    public List<Turn> getTurnsTaken() {
        return turnsTaken;
    }


}
