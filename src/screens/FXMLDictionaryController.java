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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class FXMLDictionaryController {

    @FXML
    private VBox vbox;

    @FXML
    private void initialize() {
        vbox.getChildren().clear();
        WordReader reader = new WordReader("/dictionary/dictionary.txt");

        // Being able to parse through dict in alphabetical order
        Map<String,String> dict = reader.getWordsWithDefinition();
        Set<String> words = dict.keySet();
        List<String> defs = new ArrayList<String>(words);
        Collections.sort(defs);



        for (String word : defs) {
            String capitalize = word.substring(0,1).toUpperCase() + word.substring(1);
            Label label = new Label(capitalize + ": "+  dict.get(word));
            label.setFont(Font.font("System", FontWeight.BOLD, 40));
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
