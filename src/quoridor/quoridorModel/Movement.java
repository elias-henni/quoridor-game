package quoridor.quoridorModel;

/**
 * creates movements for player
 * @author Elias Henni, Esteban Verschueren
 */
public class Movement {
    private Square previous;
    private Square next;

    public Movement(Square previous, Square next) {
        if(previous == null || next == null) {
            throw new IllegalArgumentException("the square cannot be null.");
        }
        this.previous = previous;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.format("Movement %s %s", previous, next);
    }

    /*get the square the player moved from. */
    public Square getPrevious() {
        return this.previous;
    }

    /*get the square the player moved to. */
    public Square getNext() {
        return this.next;
    }
}
