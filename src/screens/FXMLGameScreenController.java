package screens;

import games.hangman.HangmanMain;
import games.hanoi.TowerHanoiMain;
import games.puzzle.PuzzleMain;
import games.simonsays.SimonSaysMain;
import games.sudoku.sudokuMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FXMLGameScreenController {
    @FXML Button hangmanButton;
    @FXML Button towerButton;
    @FXML Button puzzleButton;
    @FXML Button simonButton;

    private static final String HANGMAN_DEFAULT = "-fx-background-image: url('/assets/images/buttons/hangman_btn.png');";
    private static final String HANGMAN_HOVER = "-fx-background-image: url('/assets/images/buttons/hangman_hover_btn.png');";
    private static final String TOWER_DEFAULT = "-fx-background-image: url('/assets/images/buttons/towerofhanoi_btn.png');";
    private static final String TOWER_HOVER = "-fx-background-image: url('/assets/images/buttons/towerofhanoi_hover_btn.png');";
    private static final String PUZZLE_DEFAULT = "-fx-background-image: url('/assets/images/buttons/puzzle_btn.png');";
    private static final String PUZZLE_HOVER = "-fx-background-image: url('/assets/images/buttons/puzzle_hover_btn.png');";
    private static final String SIMON_DEFAULT = "-fx-background-image: url('/assets/images/buttons/simon_btn.png');";
    private static final String SIMON_HOVER = "-fx-background-image: url('/assets/images/buttons/simon_hover_btn.png');";

    public void homeButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMainscreen.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        window.setScene(home);
        window.show();
    }

    public void startHangman(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLHangManDirections.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        window.setScene(home);
        window.show();
    }

    public void startHanoi(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTowerOfHanoiDirections.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        window.setScene(home);
        window.show();
    }

    public void startJigsaw(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPuzzleDirections.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        window.setScene(home);
        window.show();
    }

    public void startSimonSays(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSimonSaysDirections.fxml"));
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        window.setScene(home);
        window.show();
    }

   /* public void writeMoneyGained(String[] info){
        List<String> money = new ArrayList<>();
        File moneyFile = new File("src/scoreboard/money.txt");
        boolean set_new = false;
        try{
            Scanner sc = new Scanner(moneyFile);
            while(sc.hasNextLine()){
                money.add(sc.hasNextLine());

            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }*/

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

    public void hangmanDefault() {
        hangmanButton.setStyle(HANGMAN_DEFAULT);
    }

    public void hangmanHover() {
        hangmanButton.setStyle(HANGMAN_HOVER);
    }

    public void towerDefault() {
        towerButton.setStyle(TOWER_DEFAULT);
    }

    public void towerHover() {
        towerButton.setStyle(TOWER_HOVER);
    }

    public void puzzleDefault() {
        puzzleButton.setStyle(PUZZLE_DEFAULT);
    }

    public void puzzleHover() {
        puzzleButton.setStyle(PUZZLE_HOVER);
    }

    public void simonDefault() {
        simonButton.setStyle(SIMON_DEFAULT);
    }

    public void simonHover() {
        simonButton.setStyle(SIMON_HOVER);
    }

}