package quoridor.quoridorModel;

import java.time.Duration;
import java.time.LocalTime;

/**
 * creates a turn system
 * @author Elias Henni
 */
public class Turn {
    // TODO Test class, unsure class
    private boolean move;
    private Movement movement;
    private Fence fence;
    private final LocalTime startTime;
    private Duration duration;

    private int currentWallCrossX;
    private int currentWallCrossY;

    public Turn() {
        this.startTime = LocalTime.now();
    }

    public Turn (Movement movement) {
        this();
        this.movement = movement;
    }

    public Turn (Fence fencePlaced) {
        this();
        this.fence = fencePlaced;
    }

    public void movePawn(Movement movement) {

    }

    public Movement getMovement() {
        return movement;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getCurrentWallCrossX() {
        return currentWallCrossX;
    }

    public int getCurrentWallCrossY() {
        return currentWallCrossY;
    }

    public void setCurrentWallCross(int x, int y) {
        this.currentWallCrossX = x;
        this.currentWallCrossY = y;
    }

    public void addFence(Fence fence) {

    }
    public void end() {
        final LocalTime endTime = LocalTime.now();
        duration = Duration.between(startTime, endTime);
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public void setFence(Fence fence) {
        this.fence = fence;
    }

    @Override
    public String toString() {
        return String.format("Movement: %s   Fence: %s   Time: %s\n", movement, fence, duration);
    }
}
