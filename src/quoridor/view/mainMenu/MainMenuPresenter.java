package quoridor.view.mainMenu;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import quoridor.quoridorModel.Game;
import quoridor.view.about.AboutPresenter;
import quoridor.view.about.AboutView;
import quoridor.view.settings.SettingsPresenter;
import quoridor.view.settings.SettingsView;
import quoridor.view.start.StartPresenter;
import quoridor.view.start.StartView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import quoridor.view.stats.StatsPresenter;
import quoridor.view.stats.StatsView;

import java.util.Optional;

public class MainMenuPresenter {
    private Game model;
    private MainMenuView view;

    public MainMenuPresenter(Game model, MainMenuView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getSettingsButton().setOnAction(event -> {setSettingsView();});
        view.getExitButton().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setContentText("If you exit all game data will be lost");

            ButtonType yes = new ButtonType("Yes!");
            ButtonType no = new ButtonType("No!");

            alert.getButtonTypes().setAll(yes,no);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yes) {
                view.getScene().getWindow().hide();
            }
        });
        view.getCreditsButton().setOnAction(event -> {setAboutView();});
        view.getStartButton().setOnAction(event -> {changeToStartView();});
        view.getStatisticsButton().setOnAction(event -> {
            changeToStatsView();
        });
    }

    private void updateView() {

    }

    private void setSettingsView() {
        SettingsView settingsView = new SettingsView();
        SettingsPresenter settingsPresenter = new SettingsPresenter(model, settingsView);
        view.getScene().setRoot(settingsView);
        settingsView.getScene().getWindow();
    }

    private void setAboutView() {
        AboutView aboutView = new AboutView();
        AboutPresenter settingsPresenter = new AboutPresenter(model, aboutView);
        view.getScene().setRoot(aboutView);
        aboutView.getScene().getWindow();
    }

    private void changeToStartView() {
        StartView startView = new StartView();
        StartPresenter startPresenter = new StartPresenter(model, startView);
        view.getScene().setRoot(startView);
        startView.getScene().getWindow();
    }
    private void changeToStatsView() {
        StatsView statsView = new StatsView();
        StatsPresenter statsPresenter = new StatsPresenter(model, statsView);
        view.getScene().setRoot(statsView);
        statsView.getScene().getWindow();
    }
}