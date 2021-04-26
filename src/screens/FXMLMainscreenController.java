package screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class FXMLMainscreenController {
    @FXML private Button gamesButton;
    @FXML private Button vocabularyButton;
    @FXML private Button dictionaryButton;
    @FXML private Button shopButton;
    @FXML private Button scoreboardButton;

    private static final String ON_MOUSE_ENTER_STYLE = "-fx-background-color: #FFC0CB;";
    private static final String DEFAULT_GAMES_STYLE = "-fx-background-color: #fcf17c;";
    private static final String DEFAULT_VOCABULARY_STYLE = "-fx-background-color: #7cfca7;";
    private static final String DEFAULT_DICTIONARY_STYLE = "-fx-background-color: #fc7c7c;";
    private static final String DEFAULT_SHOP_STYLE = "-fx-background-color: #7f78ff;";
    private static final String DEFAULT_SCOREBOARD_STYLE = "-fx-background-color: #fcc777;";

    public void aboutButtonPushed(ActionEvent event) throws IOException {
        Parent aboutParent = FXMLLoader.load(getClass().getResource("FXMLAbout.fxml"));
        Scene aboutScene = new Scene(aboutParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }
    public void gameButtonPushed(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        Scene gameScene = new Scene(gameParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.show();
    }
    public void DailyChallengeButtonPushed(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("FXMLDailyChallenge.fxml"));
        Scene gameScene = new Scene(gameParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setResizable(false);
        window.show();
    }
    public void DictionaryButtonPushed(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("FXMLDictionary.fxml"));
        Scene gameScene = new Scene(gameParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setResizable(false);
        window.show();
    }
    public void ShopButtonPushed(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("FXMLShop.fxml"));
        Scene gameScene = new Scene(gameParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setResizable(false);
        window.show();
    }
    public void ScoreboardButtonPushed(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("FXMLScoreboard.fxml"));
        Scene gameScene = new Scene(gameParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setResizable(false);
        window.show();
    }
    public void SettingsButtonPushed(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("FXMLSettings.fxml"));
        Scene gameScene = new Scene(gameParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setResizable(false);
        window.show();
    }

    public void gameHover() {
        gamesButton.setStyle(ON_MOUSE_ENTER_STYLE);
    }

    public void idleGames() {
        gamesButton.setStyle(DEFAULT_GAMES_STYLE);
    }

    public void vocabularyHover() {
        vocabularyButton.setStyle(ON_MOUSE_ENTER_STYLE);
    }

    public void idleVocabulary() {
        vocabularyButton.setStyle(DEFAULT_VOCABULARY_STYLE);
    }

    public void dictionaryHover() {
        dictionaryButton.setStyle(ON_MOUSE_ENTER_STYLE);
    }

    public void idleDictionary() {
        dictionaryButton.setStyle(DEFAULT_DICTIONARY_STYLE);
    }

    public void shopHover() {
        shopButton.setStyle(ON_MOUSE_ENTER_STYLE);
    }

    public void idleShop() {
        shopButton.setStyle(DEFAULT_SHOP_STYLE);
    }

    public void scoreboardHover() {
        scoreboardButton.setStyle(ON_MOUSE_ENTER_STYLE);
    }

    public void idleScoreboard() {
        scoreboardButton.setStyle(DEFAULT_SCOREBOARD_STYLE);
    }


}