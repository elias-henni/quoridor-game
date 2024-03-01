package quoridor.quoridorModel;

/**
 * creates a board
 * @author Elias Henni, Kevin Degan
 */
public class Board {
    private int height;
    private int width;
    private Square[][] squares;
    private Fence[][] horizontalFences;
    private Fence[][] verticalFences;
    private ColorPalette boardColor;
    private ColorPalette pawnColor;
    private ColorPalette wallColor;

    /* New Board that initializes the squares, horizontalFences, and verticalFences arrays.
     horizontalFences has a height one less then width.
     verticalFences has a width one less then height. */
    public Board(int width, int height) {
        this.squares = new Square[width][height];
        this.horizontalFences = new Fence[width][height - 1];
        this.verticalFences = new Fence[width - 1][height];
        this.height = height;
        this.width = width;
        this.boardColor = ColorPalette.GRAY;
        this.pawnColor = ColorPalette.BLUE;
        this.wallColor = ColorPalette.YELLOW;
        setSquarePositions();
    }

    /* Assign coordinates to the squares. */
    private void setSquarePositions() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Square square = new Square(x, y);
                squares[x][y] = square;
            }
        }
    }

    public void addPawn(int x, int y) {
        squares[x][y].setContainsPlayer(true);
    }

    public void movePawn(int oldX, int oldY, int newX, int newY) {
        squares[oldX][oldY].setContainsPlayer(false);
        squares[newX][newY].setContainsPlayer(true);
    }

    /* Throws Exception if x or y is less than zero.
       Gets a square with a given coordinate. */
    public Square getSquare(int x, int y) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("The position cannot be less than zero.");
        }
        return squares[x][y];
    }

    /* Checks if the position on the board contains a fence.
      Boolean "horizontalFence" if the fence is horizontal. */
    public boolean containsFence(int x, int y, boolean horizontalFence) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("The position cannot be less than zero.");
        }
        if(horizontalFence) {
            return horizontalFences[x][y] != null;
        } else {
            return verticalFences[x][y] != null;
        }
    }

    /* Places a new fence on to the board. */
    public void setFence(int x, int y, boolean horizontalFence) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("The position cannot be less than zero or greater then eight.");
        }
        if(horizontalFence) {
            horizontalFences[x][y] = new Fence(x, y);
            horizontalFences[x + 1][y] = new Fence(x + 1, y);
        } else {
            verticalFences[x][y] = new Fence(x, y);
            verticalFences[x][y + 1] = new Fence(x, y + 1);
        }
    }

    /* Returns a fence given the x and y coordinates. */
    public Fence getFence(int x, int y, boolean horizontalFence) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("The position cannot be less than zero.");
        }
        if(horizontalFence) {
            return horizontalFences[x][y];
        } else {
            return verticalFences[x][y];
        }
    }

    /* Removes a fence given the x and y coordinates. */
    public void removeFence(int x, int y, boolean horizontalFence) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("The position cannot be less than zero or greater then seven.");
        }
        if(horizontalFence) {
            horizontalFences[x][y] = null;
            horizontalFences[x + 1][y] = null;
        } else {
            verticalFences[x][y] = null;
            verticalFences[x][y + 1] = null;
        }
    }

    // Prints the current board with all the squares, in the same orientation as on BoardView
    @Override
    public String toString() {
        StringBuilder sbBoard = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            StringBuilder sbRow = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                sbRow.append(squares[j][i]);
            }
            sbBoard.append(sbRow);
            sbBoard.append("\n");
        }
        return sbBoard.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Square[][] getSquares() {
        return squares;
    }

    public Fence[][] getHorizontalFences() {
        return horizontalFences;
    }

    public Fence[][] getVerticalFences() {
        return verticalFences;
    }

    public ColorPalette getBoardColor() {
        return boardColor;
    }

    public void setBoardColor(ColorPalette boardColor) {
        this.boardColor = boardColor;
    }

    public ColorPalette getPawnColor() {
        return pawnColor;
    }

    public ColorPalette getWallColor() {
        return wallColor;
    }

    public void setPawnColor(ColorPalette pawnColor) {
        this.pawnColor = pawnColor;
    }

    public void setWallColor(ColorPalette wallColor) {
        this.wallColor = wallColor;
    }
}
