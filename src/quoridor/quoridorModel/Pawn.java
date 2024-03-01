package quoridor.quoridorModel;

/**
 * creates pawn and stores pawn position
 * @author Elias Henni, Esteban Verschueren
 */
public class Pawn {
    private Square currentPosition;
    private Square lastPosition;
    private double canvasCordX;
    private double canvasCordY;
    private int currentX;
    private int currentY;
    private int lastX;
    private int lastY;

    public Pawn(PlayerType playerType) {
        if (playerType == PlayerType.USER) {
            currentPosition = new Square(4, 8);
        } else if (playerType == PlayerType.COMPUTER) {
            currentPosition = new Square(4, 0);
        }
    }

    public void setCurrentPosition(Square currentPosition) {
        lastPosition = getCurrentPosition();
        this.currentPosition = currentPosition;
    }

    public void setCurrentX(int X){
        lastX = getCurrentX();
        currentX = X;
    }

    public void setCurrentY(int Y){
        lastY = getCurrentY();
        currentY = Y;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public Square getCurrentPosition() {
        return currentPosition;
    }

    public Square getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Square lastPosition) {
        this.lastPosition = lastPosition;
    }

    public double getCanvasCordX() {
        return canvasCordX;
    }

    public double getCanvasCordY() {
        return canvasCordY;
    }

    public void setCanvasCordX(double canvasCordX) {
        this.canvasCordX = canvasCordX;
    }

    public void setCanvasCordY(double canvasCordY) {
        this.canvasCordY = canvasCordY;
    }
}
