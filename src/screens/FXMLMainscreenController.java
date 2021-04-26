package screens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import store.MoneyManager;
import store.avatar.AvatarManager;

import java.io.IOException;

public class FXMLMainscreenController {

    @FXML
    private VBox vBox;

    @FXML
    private ImageView avatar;

    @FXML
    private Text money;

    @FXML
    private Button refresh;

    @FXML
    public void initialize() {
//        avatar.setImage(new AvatarManager().getSelected().getImage());

        AvatarManager avatarManager = new AvatarManager();
        avatar.setImage(avatarManager.getSelected().getImage());
        updateMoney();
    }

    public void update() {
        AvatarManager avatarManager = new AvatarManager();
        avatar.setImage(avatarManager.getSelected().getImage());
        updateMoney();
    }

    public void updateMoney() {
        money.setText("Money: " + new MoneyManager().get());
    }

    public void refreshMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainscreen.fxml"));
        Scene aboutScene = new Scene(root);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

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


}
