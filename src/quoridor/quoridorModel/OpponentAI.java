package quoridor.quoridorModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * creates AI actions
 * @author Esteban Verschueren
 */
public class OpponentAI {
/*    1: calculates for every y = 0
2: Picks the shortest path(PlayerPath)

3: calculates for every y = 8
4: Picks the shortest path(AIPath)

5: if (PlayerPath => AIPath) {
              AI moves to next tile using shortest path
        Else {
              AI places wall on the PlayerPath.

            Rules: When Placing wall place it at the highest length.*/

    private int neighbour1x = -1;
    private int neighbour2x = -1;
    private int neighbour3x = -1;
    private int neighbour4x = -1;
    private int neighbour1y = -1;
    private int neighbour2y = -1;
    private int neighbour3y = -1;
    private int neighbour4y = -1;


    private int AILocationX;
    private int AILocationY;

    private int playerX;
    private int playerPath;

    private int AIX;
    private int AIPath;


    PathFinderAI pathFinderAI;
    private Board board;
    private Game model;

    public OpponentAI(Game model) {
        this.pathFinderAI = new PathFinderAI(model);
        this.model = model;
        this.board = model.getBoard();
    }

    void removeAll(List<Integer> list, Integer element) {
        int index;
        while ((index = list.indexOf(element)) >= 0) {
            list.remove(index);
        }
    }

    public void calculatePlayerPath() {
        pathFinderAI.findPath(pathFinderAI.game.getPlayer().getPawn().getCurrentPosition().getX(),
                pathFinderAI.game.getPlayer().getPawn().getCurrentPosition().getY(), board.getSquare(4,8));


        List<Integer> endPath = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            endPath.add(pathFinderAI.board.getSquare(x, 0).getGCost());
        }
        removeAll(endPath, 0);
        playerX = endPath.indexOf(Collections.min(endPath));
        playerPath = endPath.get(playerX);

    }

    public void updateModel(Game model) {
        this.model = model;
        this.board = model.getBoard();
    }

    public void calculateAIPath() {
        pathFinderAI = new PathFinderAI(model);
        pathFinderAI.findPath(AILocationX, AILocationY,new Square(4,8));


        List<Integer> endPathAI = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            endPathAI.add(board.getSquare(x, 8).getGCost());
        }
        removeAll(endPathAI, 0);
        AIX = endPathAI.indexOf(Collections.min(endPathAI));
        AIPath = endPathAI.get(AIX);
    }

    public void moveAI() {
        if (AILocationX - 1 != -1) {
            //left
            neighbour1x = AILocationX - 1;
            neighbour1y = AILocationY;
//            System.out.println("neighbour1 " + neighbour1x + " " + neighbour1y);
        }
        if (AILocationX + 1 != 9) {
            //right
            neighbour2x = AILocationX + 1;
            neighbour2y = AILocationY;
//            System.out.println("neighbour2 " + neighbour2x + " " + neighbour2y);
        }
        if (AILocationY - 1 != -1) {
            //up
            neighbour3x = AILocationX;
            neighbour3y = AILocationY - 1;
//            System.out.println("neighbour3 " + neighbour3x + " " + neighbour3y);

        }
        if (AILocationY + 1 != 9) {
            //down
            neighbour4x = AILocationX;
            neighbour4y = AILocationY + 1;
//            System.out.println("neighbour4 " + neighbour4x + " " + neighbour4y);

        }

        int endPath1 = 100;
        int endPath1x;
        if ((neighbour1x != -1 && neighbour1y != -1) && model.isValidDirection(model.getBoard().getSquare(AILocationX, AILocationY), model.getBoard().getSquare(neighbour1x, neighbour1y))) {
            pathFinderAI.findPath(neighbour1x, neighbour1y,board.getSquare(4,8));
//            System.out.println(pathFinderAI.board);


            List<Integer> endPath1s = new ArrayList<>();
            for (int x = 0; x < 9; x++) {
                endPath1s.add(board.getSquare(x, 8).getGCost());

            }
            removeAll(endPath1s, 0);
            endPath1x = endPath1s.indexOf(Collections.min(endPath1s));

            endPath1 = endPath1s.get(endPath1x);
//            System.out.println("left " + endPath1);

        }
        int endPath2 = 100;
        int endPath2x;
        if ((neighbour2x != -1 && neighbour2y != -1) && model.isValidDirection(model.getBoard().getSquare(AILocationX, AILocationY), model.getBoard().getSquare(neighbour2x, neighbour2y))) {
            pathFinderAI.findPath(neighbour2x, neighbour2y, board.getSquare(4,8));
//            System.out.println(pathFinderAI.board);

            List<Integer> endPath2s = new ArrayList<>();
            for (int x = 0; x < 9; x++) {
                endPath2s.add(board.getSquare(x, 8).getGCost());

            }

            removeAll(endPath2s, 0);
            endPath2x = endPath2s.indexOf(Collections.min(endPath2s));

            endPath2 = endPath2s.get(endPath2x);
//            System.out.println("right " + endPath2);

        }
        int endPath3 = 100;
        int endPath3x;
        if ((neighbour3x != -1 && neighbour3y != -1) && model.isValidDirection(model.getBoard().getSquare(AILocationX, AILocationY), model.getBoard().getSquare(neighbour3x, neighbour3y))) {
            pathFinderAI.findPath(neighbour3x, neighbour3y, board.getSquare(4,8));
//            System.out.println(pathFinderAI.board);

            List<Integer> endPath3s = new ArrayList<>();
            for (int x = 0; x < 9; x++) {
                endPath3s.add(board.getSquare(x, 8).getGCost());
            }
            removeAll(endPath3s, 0);
            endPath3x = endPath3s.indexOf(Collections.min(endPath3s));

            endPath3 = endPath3s.get(endPath3x);
//            System.out.println("up" + endPath3);

        }
        int endPath4 = 100;
        int endPath4x;
        if ((neighbour4x != -1 && neighbour4y != -1) && model.isValidDirection(model.getBoard().getSquare(AILocationX, AILocationY), model.getBoard().getSquare(neighbour4x, neighbour4y))) {
            pathFinderAI.findPath(neighbour4x, neighbour4y, board.getSquare(4,8));
//            System.out.println(pathFinderAI.board);

            List<Integer> endPath4s = new ArrayList<>();
            for (int x = 0; x < 9; x++) {
                endPath4s.add(board.getSquare(x, 8).getGCost());
            }
            removeAll(endPath4s, 0);
            endPath4x = endPath4s.indexOf(Collections.min(endPath4s));

            endPath4 = endPath4s.get(endPath4x);
//            System.out.println("down " + endPath4);

        }
        if (endPath1 <= endPath2 && endPath1 <= endPath3 && endPath1 <= endPath4) {
            //left
            AILocationX = neighbour1x;
            AILocationY = neighbour1y;
        }
        if (endPath2 <= endPath1 && endPath2 <= endPath3 && endPath2 <= endPath4) {
            //right
            AILocationX = neighbour2x;
            AILocationY = neighbour2y;
        }
        if (endPath3 <= endPath2 && endPath3 <= endPath1 && endPath3 <= endPath4) {
            //up
            AILocationX = neighbour3x;
            AILocationY = neighbour3y;
        }
        if (endPath4 <= endPath2 && endPath4 <= endPath3 && endPath4 <= endPath1) {
            //down
            AILocationX = neighbour4x;
            AILocationY = neighbour4y;
        }
//        System.out.println("new location = " + AILocationX + " " + AILocationY);


    }
    int wallY;
    public void calcWallY(){

        //RULE: Set the walls behind yourself
        if (AILocationY >= 1 && playerX !=8){
            wallY = 0;
            //walls on top
            vertical = false;
        }

        //RULE: set walls next to the enemy to prolong his path
        else if (model.getPlayer().getPawn().getCurrentPosition().getY() >= 8 ||
                model.getPlayer().getPawn().getCurrentPosition().getY() >= 6){
            calculatePlayerPath();
            if (playerX == 8){
                if ((model.getPlayer().getPawn().getCurrentPosition().getY() - 1) % 2 == 0){
                    wallY = model.getPlayer().getPawn().getCurrentPosition().getY() - 1;
                    wallX = model.getPlayer().getPawn().getCurrentPosition().getX();
                }
                else wallY = model.getPlayer().getPawn().getCurrentPosition().getY();
                wallX = model.getPlayer().getPawn().getCurrentPosition().getX();
                vertical = true;
            }

            Random random = new Random();
            wallY = random.nextInt(1) + 1;
            vertical = false;

        }

        //RULE: When no obvious wallplacement exists place wall in front of the enemy
        else if (model.getPlayer().getPawn().getCurrentPosition().getY() >= 7 || model.getPlayer().getPawn().getCurrentPosition().getY() >= 5){
            Random random = new Random();
            wallY = random.nextInt(1) + 2;
        }
        else wallY = model.getPlayer().getPawn().getCurrentPosition().getY() -1;
    }

    int wallX;
    public void calcWallX(){

        //RULE: Always place the walls next to each other, not on top
        calculatePlayerPath();
        if (!(playerX == 0)){
            if ((playerX - 1) % 2 == 0){
                wallX = playerX - 1;
            }
            else wallX = playerX;
        }
    }
private boolean vertical;

    public Fence setWallAI() {
        calculatePlayerPath();
        calcWallY();
        calcWallX();


//        !(board.containsFence(wallX - 1 ,wallY,true) &&
//                !(board.containsFence(wallX  ,wallY,true)))

        if (!(board.containsFence(wallX , wallY,true))){

            if (wallX != 8 && vertical == false){
//                System.out.println("Horizontal Fence");
                board.setFence(wallX , wallY,true);
//                System.out.println("Horizontal: " + wallX + " " + wallY);

                return new Fence(wallX , wallY);
            }
            else
//                System.out.println("Vertical Fence");
            board.setFence(wallX - 1 ,wallY + 1 ,false);
//            System.out.println("Horizontal: " + wallX + " " + wallY);

            return new Fence(wallX - 1, wallY + 1);

        }
        else return new Fence(7,7);
    }

    public boolean Horizontal(){
        if (wallX != 8){
            return true;
        }
        else return false;
    }

    public boolean checkIfRight(){

        if (!(pathFinderAI.endSquareFound(model.getBoard().getSquare(model.getPlayer().getPawn().getCurrentPosition().getX(),
                model.getPlayer().getPawn().getCurrentPosition().getY()), true)) ||
                !(pathFinderAI.endSquareFound(model.getBoard().getSquare(model.getOpponentAI().getAILocationX(),
                        model.getOpponentAI().getAILocationY()), false))){

//            System.out.println("removed: " + wallX + " " + wallY);
            model.getBoard().removeFence(wallX,wallY,true);
            return false;
        }
        else return true;
    }





    public int getAILocationX() {
        return AILocationX;
    }

    public int getAILocationY() {
        return AILocationY;
    }

    public void setAILocationX(int AILocationX) {
        this.AILocationX = AILocationX;
    }

    public void setAILocationY(int AILocationY) {
        this.AILocationY = AILocationY;
    }

    public int getPlayerPath() {
        return playerPath;
    }

    public int getAIPath() {
        return AIPath;
    }
}