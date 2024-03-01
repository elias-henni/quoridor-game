package quoridor.view.instructions;

import quoridor.quoridorModel.Game;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;
import quoridor.view.start.StartPresenter;
import quoridor.view.start.StartView;

public class InstructionsPresenter {
    private Game model;
    private InstructionsView view;

    public InstructionsPresenter(
            Game model, InstructionsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getMainMenuButton().setOnAction(event -> {changeToMainMenu();});
    }

    private void updateView() {

    }

    private void changeToMainMenu() {
        StartView menuView = new StartView();
        StartPresenter menuPresenter = new StartPresenter(model, menuView);
        view.getScene().setRoot(menuView);
        menuView.getScene().getWindow();
    }
}
