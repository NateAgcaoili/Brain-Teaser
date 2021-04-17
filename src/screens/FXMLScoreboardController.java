package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FXMLScoreboardController {

    @FXML
    private Label hangman_score;
    @FXML
    private Label towers_hanoi_score;
    @FXML
    private Label jigsaw_score;
    @FXML
    private Label simon_score;

    @FXML
    private void initialize() {
        ArrayList<Integer> highscores = get_highscores();

        hangman_score.setText(String.valueOf(highscores.get(0)));
        towers_hanoi_score.setText(String.valueOf(highscores.get(1)));
        jigsaw_score.setText(String.valueOf(highscores.get(2)));
        simon_score.setText(String.valueOf(highscores.get(3)));

    }

    private ArrayList<Integer> get_highscores() {
        ArrayList<Integer> highscores = new ArrayList<Integer>();
        // read high score file
        File scores_file = new File("src/scoreboard/highscores.txt");

        try {
            Scanner sc = new Scanner(scores_file);
            // parse and set each high score into the array
            while (sc.hasNextLine()) {

                String[] line = sc.nextLine().split("-");
                int score = Integer.parseInt(line[1]);
                highscores.add(score);
            }
            sc.close();
        }
        catch (IOException e) {
            System.out.print(new File(".").getAbsolutePath());
            System.out.print(e.getMessage());
        }

        return highscores;
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

    public void reset_highscores(ActionEvent event) throws IOException {
        List<String> scores = new ArrayList<String>();
        File scores_file = new File("src/scoreboard/highscores.txt");

        try {
            Scanner sc = new Scanner(scores_file);

            while (sc.hasNextLine()) {
                scores.add(sc.nextLine());
            }
            sc.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for(int i = 0; i < scores.size(); i++) {
            String[] line = scores.get(i).split("-");
            StringBuilder stb = new StringBuilder();
            stb.append(line[0] + "-" + "0");
            scores.set(i, stb.toString());

        }
        try {
            FileWriter fw = new FileWriter(scores_file);
            for (String line : scores) {
                fw.write(line + "\n");
            }
            fw.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(scores);
        initialize();

    }

}
