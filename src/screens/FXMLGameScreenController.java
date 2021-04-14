package screens;

import games.hangman.HangmanMain;
import games.hanoi.TowerHanoiMain;
import games.puzzle.PuzzleMain;
import games.simonsays.SimonSaysMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLGameScreenController {

    public void homeButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainscreen.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        window.setScene(home);
        window.show();
    }

    public void startHangman(ActionEvent event) throws IOException {
        HangmanMain hangman = new HangmanMain();
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
        Parent aboutParent = FXMLLoader.load(getClass().getResource("FXMLAbout.fxml"));
        Scene aboutScene = new Scene(aboutParent);

        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(aboutScene);
        window.show();
    }

}