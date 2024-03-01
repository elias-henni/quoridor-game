package quoridor.view.about;

import quoridor.quoridorModel.Game;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;

public class AboutPresenter {
    private Game model;
    private AboutView view;

    public AboutPresenter (
            Game model, AboutView view) {
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
        MainMenuView menuView = new MainMenuView();
        MainMenuPresenter menuPresenter = new MainMenuPresenter(model, menuView);
        view.getScene().setRoot(menuView);
        menuView.getScene().getWindow();
    }
}
