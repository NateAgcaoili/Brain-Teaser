import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class FXMLMainscreenController {

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
