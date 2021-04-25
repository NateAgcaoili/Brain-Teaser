package screens;

import com.sun.javafx.scene.SceneEventDispatcher;
import games.hangman.HangmanMain;
import games.hanoi.TowerHanoiMain;
import games.puzzle.PuzzleMain;
import games.simonsays.SimonSaysMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.net.URL;

public class FXMLDirectionsController {

    public void startHangman(ActionEvent event) throws IOException {

        HangmanMain hangman = new HangmanMain("/dictionary/dictionary.txt");
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            hangman.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        };
        window.show();
    }
    public void startHanoi(ActionEvent event) throws IOException {
        TowerHanoiMain hanoi = new TowerHanoiMain();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            hanoi.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        };
        window.show();
    }
    public void startJigsaw(ActionEvent event) throws IOException {
        PuzzleMain puzzle = new PuzzleMain();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            puzzle.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        };
        window.show();
    }
    public void startSimonSays(ActionEvent event) throws IOException {
        SimonSaysMain simonSays = new SimonSaysMain();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            simonSays.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        };
        window.show();
    }

    public void returnToGames(ActionEvent event) throws IOException {
        Parent gameParent = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        Scene gameScene = new Scene(gameParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameScene);
        window.show();
    }

}
