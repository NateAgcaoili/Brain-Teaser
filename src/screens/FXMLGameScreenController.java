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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FXMLGameScreenController {

    public void homeButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainscreen.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        window.setScene(home);
        window.show();
    }

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



    public void write_highscores(String[] info){
        List<String> scores = new ArrayList<String>();
        File scores_file = new File("src/scoreboard/highscores.txt");
        boolean set_new = false;
        try {
            Scanner sc = new Scanner(scores_file);

            while (sc.hasNextLine()) {
                scores.add(sc.nextLine());
                // Checks if the line matches the correct game name and gets old score
            }
            sc.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for(int i = 0; i < scores.size(); i++) {
            String line = scores.get(i);
            if (line.contains(info[0])) {
                float old_highscore = Integer.parseInt(line.split("-")[1]);
                float new_score = Integer.parseInt(info[1]);

                if (new_score > old_highscore) {
                    StringBuilder stb = new StringBuilder();
                    stb.append(info[0] + "-" + info[1]);
                    scores.set(i, stb.toString());
                    set_new = true;
                    break;
                }
            }
        }

        if (set_new) {
            try {
                FileWriter fw = new FileWriter(scores_file);
                for (String line : scores) {
                    fw.write(line+"\n");
                }

                fw.close();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }



    }

}