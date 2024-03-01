package quoridor.quoridorModel;

/**
 * creates fence
 * @author Elias Henni, Kevin degan
 */
public class Fence {
    private int x;
    private int y;
    boolean isHorizontal;

    public Fence(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }
}
