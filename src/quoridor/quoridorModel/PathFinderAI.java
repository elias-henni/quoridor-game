package quoridor.quoridorModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * finds shortest path for AI
 * @author Esteban Verschueren
 */
public class PathFinderAI {


    Board board;
    Square target;
    Game game;

    // Collect the current game and board everytime to find path with current game state
    public PathFinderAI(Game game) {
        this.game = game;
        this.board = game.getBoard();
        this.target = game.getBoard().getSquare(4,0);
    }

    public boolean endSquareFound(Square currentLocation, boolean playerEndZone) {
        if (playerEndZone) {
            for (int x = 0; x < 9; x++) {
                if (findPath(currentLocation.getX(), currentLocation.getY(), new Square(x, 0))) {
                    return true;
                }
            }
        } else {
            for (int x = 0; x < 9; x++) {
                if (findPath(currentLocation.getX(), currentLocation.getY(), new Square(x, 8))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findPath(int currentPawnLocationX, int currentPawnLocationY, Square endSquare) {
        // First loop through all squares on board and reset their variables
        // Reset the gCost back to 0 and isGCostSet to false
        this.target = game.getBoard().getSquare(currentPawnLocationX,currentPawnLocationY);
        for (Square[] row : board.getSquares()) {
            for (Square square : row) {
                square.setIsGCostSet(false);
                square.setGCost((0));

            }
        }
        boolean outcomeFound = false;
        // distance pawn is from the target
        int lengthAway = 0;
        // The Set open is used to keep the current squares that are being evaluated
        // The Set closed is used to hold squares that already have been evaluated
        Set<Square> open = new HashSet<>();
        Set<Square> closed = new HashSet<>();
        // Adds the target square to open to start the loop
        open.add(board.getSquare(target.getX(), target.getY()));

        while (!outcomeFound) {
            // Used as the list of valid neighbors of the current squares in open
            List<Square> neighbors = new ArrayList<>();
            // Loop through all squares in open and find valid neighbors
            for (Square square : open) {
                // Sets the gCost and isGCostSet of square
                board.getSquare(square.getX(), square.getY()).setGCost(lengthAway);
                square.setIsGCostSet(true);
                // List neighborCandidates uses getNeighbors to find the possible neighbor candidates
                // So we don't go outside of board
                List<Square> neighborCandidates = getNeighbors(square);
                // Goes through all neighborCandidates and filters each one to make sure
                //      1. It's a valid direction (Used to check for walls)
                //      2. It's not already in open (just evaluated or up next)
                //      3. It's not already in closed (already evaluated)
                for (Square nc : neighborCandidates) {
                    if (((game.isValidDirection(square, board.getSquare(nc.getX(), nc.getY())))
                            && !open.contains(board.getSquare(nc.getX(), nc.getY())))
                            && !closed.contains(board.getSquare(nc.getX(), nc.getY()))) {
                        // If condition is met, add the neighbor candidate to the List neighbors
                        neighbors.add(board.getSquare(nc.getX(), nc.getY()));
                    }
                }
                // add square to closed
                closed.add(square);
            }
            // empty Set open
            open.clear();
            // add all neighbors (that were found from the last group in open) to open
            open.addAll(neighbors);
            // Loop through every square on board to find an outcome
            for (Square[] row : board.getSquares()) {
                for (Square square : row) {
                    // If current pawn location has gCost set, then path is found
                    if (square.getIsGCostSet() && square.getX() == endSquare.getX() && square.getY() == endSquare.getY() && open.size() == 0) {
                        return true;
                        // If Set open is empty then outcomeFound is true (Brings you out of loop)
                        // If kicked out of loop the default returns false
                    } else if (open.size() == 0) {
                        outcomeFound = true;
                    }
                }
            }
            // Before loop, update lengthAway
            lengthAway++;
        }
//        System.out.println("no outcome found");
        return false;
    }

    // Is used to return square neighbors that are in bounds of board
    // Takes a square as a parameter
    public List<Square> getNeighbors(Square square) {
        // List neighbors holds the the valid neighbors and is returned by method
        List<Square> neighbors = new ArrayList<>();
        // Checks move down
        if (game.isValidDirection(square, new Square(square.getX(), square.getY() + 1))) {
            neighbors.add(board.getSquare(square.getX(), square.getY() + 1));
        }
        // Checks move up
        if (game.isValidDirection(square, new Square(square.getX(), square.getY() - 1))) {
            neighbors.add(board.getSquare(square.getX(), square.getY() - 1));
        }
        // Checks move right
        if (game.isValidDirection(square, new Square(square.getX() + 1, square.getY()))) {
            neighbors.add(board.getSquare(square.getX() + 1, square.getY()));
        }
        // Checks move left
        if (game.isValidDirection(square, new Square(square.getX() - 1, square.getY()))) {
            neighbors.add(board.getSquare(square.getX() - 1, square.getY()));
        }
        return neighbors;
    }
    public void setTarget(Square square){
        this.target = square;
    }

    public Board getBoard() {
        return board;
    }
}
