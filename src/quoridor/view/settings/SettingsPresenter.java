package quoridor.view.settings;

import quoridor.quoridorModel.ColorPalette;
import quoridor.quoridorModel.Game;
import quoridor.view.mainMenu.MainMenuPresenter;
import quoridor.view.mainMenu.MainMenuView;

public class SettingsPresenter {
    private Game model;
    private SettingsView view;

    public SettingsPresenter(
            Game model, SettingsView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getMainMenuButton().setOnAction(event -> {changeToMainMenu();});

        view.getSaveButton().setOnAction(actionEvent -> {
            // Amount of walls
            Integer amountOfWalls = view.getAmountOfWalls().getValue();
            model.setAmountOfFencesPerPlayer(amountOfWalls);
            model.getPlayer().setFences(amountOfWalls);
            System.out.println("walls saved");
            model.getCom().setFences(amountOfWalls);

            // Board Color
            String boardColor = view.getBoardColor().getValue();
            switch (boardColor) {
                case "Gray" : model.getBoard().setBoardColor(ColorPalette.GRAY); break;
                case "Orange" : model.getBoard().setBoardColor(ColorPalette.ORANGE); break;
                case "Blue" : model.getBoard().setBoardColor(ColorPalette.BLUE); break;
                case "Yellow" : model.getBoard().setBoardColor(ColorPalette.YELLOW); break;
                case "Purple" : model.getBoard().setBoardColor(ColorPalette.PURPLE); break;
                case "Dark Night" : model.getBoard().setBoardColor(ColorPalette.DARKNIGHT); break;
            }
            System.out.println("Board Color saved");

            // Pawn color
            String pawnColor = view.getPawnColor().getValue();
            switch (pawnColor) {
                case "Gray" : model.getBoard().setPawnColor(ColorPalette.GRAY); break;
                case "Orange" : model.getBoard().setPawnColor(ColorPalette.ORANGE); break;
                case "Blue" : model.getBoard().setPawnColor(ColorPalette.BLUE); break;
                case "Yellow" : model.getBoard().setPawnColor(ColorPalette.YELLOW); break;
                case "Purple" : model.getBoard().setPawnColor(ColorPalette.PURPLE); break;
                case "Dark Night" : model.getBoard().setPawnColor(ColorPalette.DARKNIGHT); break;
            }
            System.out.println("Pawn Color saved");

            // Wall color
            String wallColor = view.getWallColor().getValue();
            switch (wallColor) {
                case "Gray" : model.getBoard().setWallColor(ColorPalette.GRAY); break;
                case "Orange" : model.getBoard().setWallColor(ColorPalette.ORANGE); break;
                case "Blue" : model.getBoard().setWallColor(ColorPalette.BLUE); break;
                case "Yellow" : model.getBoard().setWallColor(ColorPalette.YELLOW); break;
                case "Purple" : model.getBoard().setWallColor(ColorPalette.PURPLE); break;
                case "Dark Night" : model.getBoard().setWallColor(ColorPalette.DARKNIGHT); break;
            }
            System.out.println("Wall Color saved");

        });
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
