package quoridor;

import javafx.scene.image.Image;
import quoridor.quoridorModel.Board;
import quoridor.quoridorModel.Game;
import quoridor.quoridorModel.Player;
import quoridor.quoridorModel.PlayerType;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Game model = new Game(new Player(PlayerType.USER, null,null, 10), new Board(9,9));
        MainMenuView view = new MainMenuView();
        new MainMenuPresenter(model, view);
        primaryStage.setTitle("Quoridor");
        primaryStage.getIcons().add(new Image("/images/redpion.png"));
        primaryStage.setScene(new Scene(view));
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(700);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

