package quoridor.quoridorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * game-logic
 * @author Elias Henni, Esteban verschueren
 */
public class Game {
    public static final int COLUMNS = 9;
    public static final int ROWS = 9;
    private Player com;
    private Player player;
    private Board board;
    private Player winner;
    private int amountOfFencesPerPlayer = 10;
//    private Database_connection database_connection;
    private PathFinderAI pathFinderAI;
    private OpponentAI opponentAI;

    private List<Integer> greenDotX;
    private List<Integer> greenDotY;

    public Game(Player player, Board board) {
        this.player = player;
        this.board = board;
        this.com = new Player(PlayerType.COMPUTER, null, null, amountOfFencesPerPlayer);
        board.addPawn(4,8);
        player.getPawn().setCurrentPosition(board.getSquare(4,8));
        player.getPawn().setCurrentX(4);
        player.getPawn().setCurrentY(8);
//        database_connection = new Database_connection();
        this.pathFinderAI = new PathFinderAI(this);
        this.opponentAI = new OpponentAI(this);
    }

    public Game() {
    }

    public void startTurn(Player player) {
        player.getTurnsTaken().add(new Turn());
    }
    public void endTurn(Player player, Movement movement, Fence fencePlaced) {
        if (movement != null) {
            player.getTurnsTaken().get(player.getTurnsTaken().size() - 1).setMovement(movement);
        } else if (fencePlaced != null) {
            player.getTurnsTaken().get(player.getTurnsTaken().size() - 1).setFence(fencePlaced);
        }
        player.getTurnsTaken().get(player.getTurnsTaken().size() - 1).end();
        if (player.getPawn().getCurrentPosition().getY() == 0) {
            setWinner(player);
        } else {
            opponentAI.updateModel(this);
            opponentAI.calculatePlayerPath();
            opponentAI.calculateAIPath();
//            opponentAI.moveAI();
            startTurn(player);
        }
    }

    public void takeAITurn() {
        if (opponentAI.getPlayerPath() < opponentAI.getAIPath()) {
            opponentAI.moveAI();
        } else {
            Fence fence = opponentAI.setWallAI();
            board.setFence(fence.getX(), fence.getY(), true);
        }
    }

    /*checks if a move is possible.
     * "first" is the square where the player is at the moment.
     * "second" is the square where the player wants to go.
     * throws Exception if the square is null. */
    public boolean isValidDirection(Square first, Square second) {
        if (first == null | second == null) {
            throw new IllegalArgumentException("The square cannot be null.");
        }
        int firstX = first.getX();
        int firstY = first.getY();
        int upcomingX = second.getX();
        int upcomingY = second.getY();

        /*check if the new position is outside the board. */
        if (upcomingX >= board.getWidth() || upcomingY >= board.getHeight() || upcomingX < 0 || upcomingY < 0) {
            return false;
        }
        /* if (board.getSquare(upcomingX, upcomingY).containsPlayer()) {
            return false;
        }*/
        if (upcomingX == firstX) {
            if (upcomingY == firstY - 1) { //going upwards
                return !board.containsFence(firstX, firstY - 1, true);
            }
            if (upcomingY == firstY + 1) { //going downwards
                return !board.containsFence(firstX, firstY, true);
            }

        }
        /*going left*/
        if (upcomingY == firstY) {
            if (upcomingX == firstX - 1) {
                return !board.containsFence(firstX - 1, firstY, false);
            }
            /*going right*/
        if (upcomingX == firstX + 1) {
            return !board.containsFence(firstX, firstY, false);
        }

        }
        return false;
    }

    public List<Integer> currentValidWallCrosses(boolean isX) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int column = 1; column < 9; column++) {
            for (int row = 1; row < 9; row++) {
                if (validWallCross(column,row)) {
                    x.add(column);
                    y.add(row);
                }
            }
        }
        if (isX) {
            return x;
        } else {
            return y;
        }
    }

    public boolean validWallCross(int xCord, int yCord) {
        if ((board.containsFence(xCord - 1, yCord - 1, true) && // left
                board.containsFence(xCord, yCord - 1, true)) || // right
                (board.containsFence(xCord - 1, yCord - 1, false) && // up
                        board.containsFence(xCord - 1, yCord, false))) { // down
            return false;
        } else if ((board.containsFence(xCord - 1, yCord - 1, true) && // left
                board.containsFence(xCord - 1, yCord - 1, false))) { // up
            return false;
        } else if ((board.containsFence(xCord - 1, yCord - 1, true) && // left
                board.containsFence(xCord - 1, yCord, false))) { // down
            return false;
        } else if ((board.containsFence(xCord, yCord - 1, true) && // right
                board.containsFence(xCord - 1, yCord - 1, false))) { // up
            return false;
        } else if ((board.containsFence(xCord, yCord - 1, true) && // right
                board.containsFence(xCord - 1, yCord, false))) { // down
            return false;
        } else if (!(validWallOfCross(xCord, yCord, false) || validWallOfCross(xCord, yCord, true))) {
            return false;
        }
        return true;
    }

    public boolean validWallOfCross(int xCord, int yCord, boolean horizontalWall) {
        boolean blocksPath = false;
        if (horizontalWall) {
            if (board.containsFence(xCord - 1, yCord - 1, true) || board.containsFence(xCord, yCord - 1, true)) {
                return false;
            }
            board.setFence(xCord - 1, yCord - 1, true);
            if (!(pathFinderAI.endSquareFound(board.getSquare(player.getPawn().getCurrentPosition().getX(),
                    player.getPawn().getCurrentPosition().getY()), true)) || !(pathFinderAI.endSquareFound(board.getSquare(opponentAI.getAILocationX(),
                    opponentAI.getAILocationY()), false))) {
//                System.out.println("This wall would block the path");
                blocksPath = true;
            }
            board.removeFence(xCord - 1, yCord - 1, true);
            if ((board.containsFence(xCord - 1, yCord - 1, true) || // left
                    board.containsFence(xCord, yCord - 1, true)) || blocksPath) { // right
                return false;
            }
        } else {
            if (board.containsFence(xCord - 1, yCord - 1, false) || board.containsFence(xCord - 1, yCord, false)) {
                return false;
            }
            board.setFence(xCord - 1, yCord - 1, false);
            if (!(pathFinderAI.endSquareFound(board.getSquare(player.getPawn().getCurrentPosition().getX(),
                    player.getPawn().getCurrentPosition().getY()), true)) || !(pathFinderAI.endSquareFound(board.getSquare(opponentAI.getAILocationX(),
                    opponentAI.getAILocationY()), false))) {
//                System.out.println("This wall would block the path");
                blocksPath = true;
            }
            board.removeFence(xCord - 1, yCord - 1, false);
            if ((board.containsFence(xCord - 1, yCord - 1, false) || // up
                    board.containsFence(xCord - 1, yCord, false)) || blocksPath) { // down
                return false;
            }
        }
        return true;
    }

    public List<Square> currentValidMovements() {
        List<Square> neighbors = pathFinderAI.getNeighbors(player.getPawn().getCurrentPosition());
        List<Square> result = new ArrayList<>();
        for (Square neighbor : neighbors) {
            if (isValidDirection(board.getSquare(player.getPawn().getCurrentPosition().getX(), player.getPawn().getCurrentPosition().getY()),
                    board.getSquare(neighbor.getX(), neighbor.getY()))) {
                result.add(neighbor);
            }
        }
        return result;
    }

    public void setWinner(Player player) {
        winner = player;
    }
    public Board getBoard() {
        return board;
    }
    public Player getPlayer() {
        return player;
    }

    public Player getCom() {
        return com;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Integer> getGreenDotX() {
        return greenDotX;
    }

    public List<Integer> getGreenDotY() {
        return greenDotY;
    }

    public void setGreenDotX(List<Integer> greenDotX) {
        this.greenDotX = greenDotX;
    }

    public void setGreenDotY(List<Integer> greenDotY) {
        this.greenDotY = greenDotY;
    }

//    public Database_connection getDatabase_connection() {
//        return database_connection;
//    }

    public void setAmountOfFencesPerPlayer(int amountOfFencesPerPlayer) {
        this.amountOfFencesPerPlayer = amountOfFencesPerPlayer;
    }

    public int getAmountOfFencesPerPlayer() {
        return amountOfFencesPerPlayer;
    }

    public OpponentAI getOpponentAI() {
        return opponentAI;
    }
}

