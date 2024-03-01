package quoridor.quoridorModel;

/**
 * creates squares for board
 * @author Elias Henni
 */
public class Square {
    private boolean containsPlayer;
    private int x;
    private int y;

    private int gCost;
    private boolean isGCostSet;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%s) ",gCost);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /*sets whether this square contains a player. */
    public void setContainsPlayer(boolean contains) {
        this.containsPlayer = contains;
    }

    /*if this THIS contains a player. */
    public boolean containsPlayer() {
        return containsPlayer;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }


    public boolean getIsGCostSet() {
        return isGCostSet;
    }


    public void setIsGCostSet(boolean gCostSet) {
        this.isGCostSet = gCostSet;
    }

}
