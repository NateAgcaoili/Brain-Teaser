package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import screens.profile.FXMLProfileView;
import screens.settings.FXMLSettingsController;
import screens.shop.FXMLShopController;
import store.MoneyManager;
import store.avatar.AvatarManager;

import java.io.IOException;

public class FXMLMainscreenController {

    public final static AvatarManager avatarManager = new AvatarManager();
    public final static MoneyManager moneyManager = new MoneyManager();

    @FXML
    private AnchorPane profileView;

    @FXML
    public void initialize() {
        try {
            refreshButtonPushed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshButtonPushed() throws IOException {
        FXMLProfileView profileController = new FXMLProfileView(avatarManager.getSelected(), moneyManager.get());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile/FXMLProfileView.fxml"));
        loader.setRoot(profileView);
        loader.setController(profileController);
        loader.load();
    }

    public void SettingsButtonPushed(ActionEvent event) throws IOException {
        FXMLSettingsController settingsController = new FXMLSettingsController(avatarManager);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("settings/FXMLSettings.fxml"));
        loader.setController(settingsController);
//        Parent gameParent = FXMLLoader.load(getClass().getResource("settings/FXMLSettings.fxml"));
        Scene gameScene = new Scene(loader.load());

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.setResizable(false);
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
        FXMLShopController shopController = new FXMLShopController(avatarManager, moneyManager);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shop/FXMLShop.fxml"));
        loader.setController(shopController);
//        Parent gameParent = FXMLLoader.load(getClass().getResource("shop/FXMLShop.fxml"));
        Scene gameScene = new Scene(loader.load());

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

}
