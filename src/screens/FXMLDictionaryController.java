package screens;

import games.hangman.WordReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLDictionaryController {

    @FXML
    private VBox vbox;

    @FXML
    private void initialize() {
        vbox.getChildren().clear();
        WordReader reader = new WordReader("/dictionary/dictionary.txt");

        for (String word : reader.getWordsWithDefinition()) {
            Label label = new Label(word);
            vbox.getChildren().add(label);
            vbox.getChildren().add(new Separator());
        }
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent aboutParent = FXMLLoader.load(getClass().getResource("FXMLMainscreen.fxml"));
        Scene mainScene = new Scene(aboutParent);
        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.setResizable(false);
        window.show();
    }
}
