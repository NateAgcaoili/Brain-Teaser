package games.simonsays;

import games.GameOptions;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Nate Agcaoili
 */

public class SimonSaysMain extends Application {
    private static final int APP_W = 1280;
    private static final int APP_H = 720;
    int currentRound;
    int playerIndex;
    boolean running;
    boolean playerTurn;
    Text roundDisplay;
    Text message;
    Button playAgainButton;
    Button mainMenuButton;
    Button optionsButton;
    GameButton[] gameButtons;
    Color[] colors;
    ArrayList<Integer> simonSequence = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(), APP_W, APP_H);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createContent() throws Exception {
        Pane root = new Pane();
        currentRound = 1;
        playerIndex = 0;
        running = true;
        playerTurn = false;
        roundDisplay = new Text();
        roundDisplay.setFont(new Font(75));
        roundDisplay.setText("Round " + currentRound);
        roundDisplay.setX(480);
        roundDisplay.setY(125);
        message = new Text();
        message.setFont(new Font(70));
        message.setText("Simon's Turn");
        message.setX(480);
        message.setY(620);
        playAgainButton = new Button("PLAY AGAIN");
        playAgainButton.setOnAction(e -> onClickPlayAgain(e));
        playAgainButton.setPrefSize(200, 80);
        playAgainButton.setLayoutX(480);
        playAgainButton.setLayoutY(630);
        playAgainButton.setVisible(false);
        mainMenuButton = new Button("MAIN MENU");
        mainMenuButton.setOnAction(e -> {
            try {
                onClickMainMenu(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        mainMenuButton.setPrefSize(200, 80);
        mainMenuButton.setLayoutX(690);
        mainMenuButton.setLayoutY(630);
        mainMenuButton.setVisible(false);
        optionsButton = new Button("OPTIONS");
        optionsButton.setOnAction(e -> {
            try {
                openOptions(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        optionsButton.setLayoutX(10);
        optionsButton.setLayoutY(10);
        GameButton butOne = new GameButton(0,480, 150, Color.BLUE);
        GameButton butTwo = new GameButton(1,615, 150, Color.BLUE);
        GameButton butThree = new GameButton(2,750, 150, Color.BLUE);
        GameButton butFour = new GameButton(3,480, 285, Color.BLUE);
        GameButton butFive = new GameButton(4,615, 285, Color.BLUE);
        GameButton butSix = new GameButton(5,750, 285, Color.BLUE);
        GameButton butSeven = new GameButton(6,480, 420, Color.BLUE);
        GameButton butEight = new GameButton(7,615, 420, Color.BLUE);
        GameButton butNine = new GameButton(8,750, 420, Color.BLUE);
        gameButtons = new GameButton[]{butOne, butTwo, butThree, butFour, butFive, butSix, butSeven, butEight, butNine};
        colors = new Color[]{Color.BLUE, Color.WHITE, Color.GREEN, Color.RED};
        for(int i = 0; i < 9; i++) {
            root.getChildren().add(gameButtons[i]);
        }
        root.getChildren().addAll(roundDisplay, message, playAgainButton, mainMenuButton, optionsButton);
        playGame();
        return root;
    }

    public void playGame() throws Exception {
        if (!running) {
            gameOver();
        } else if (!playerTurn) {
            roundDisplay.setText("Round " + currentRound);
            new DisplaySequence().execute();
        } else if (playerTurn) {
            if (playerIndex == currentRound) {
                playerTurn = false;
                playerIndex = 0;
                currentRound++;
                message.setText("Correct!");
                new RoundComplete().execute();
            }
        }
    }

    public void addToSequence() {
        Random rand = new Random();
        int newInt = rand.nextInt(9);
        simonSequence.add(newInt);
    }

    public void setButtonColors(Color buttonColor) {
        for(int i = 0; i < 9; i++) {
            gameButtons[i].button.setFill(buttonColor);
        }
    }

    public void gameOver() {
        playerTurn = false;
        setButtonColors(colors[3]);
        playAgainButton.setVisible(true);
        mainMenuButton.setVisible(true);
        message.setText("Game Over");
        roundDisplay.setText("Score: " + currentRound);
    }

    private void openOptions(ActionEvent event) throws IOException {
        int result = GameOptions.display();
        switch (result) {
            case 0:
                System.out.println("Returned");
                break;
            case 1:
                Parent gameParent = FXMLLoader.load(getClass().getResource("/screens/GameScreen.fxml"));
                Scene gameScene = new Scene(gameParent);

                // getting stage information
                Stage gameWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                gameWindow.setScene(gameScene);
                gameWindow.show();
                break;
            case 2:
                Parent root = FXMLLoader.load(getClass().getResource("/screens/FXMLMainscreen.fxml"));
                Stage homeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene home = new Scene(root);
                homeWindow.setScene(home);
                homeWindow.show();
                break;
            default:
                System.out.println("Unknown");
        }
    }

    public void onClickPlayAgain(ActionEvent event) {
        SimonSaysMain simonSays = new SimonSaysMain();

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            simonSays.start(window);
        } catch (Exception e) {
            e.printStackTrace();
        };
        window.show();
    }

    public void onClickMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/screens/FXMLMainscreen.fxml"));
        Stage homeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene home = new Scene(root);
        homeWindow.setScene(home);
        homeWindow.show();
    }

    public class GameButton extends StackPane {
        int buttonId;
        Rectangle button;

        GameButton(int id, int x, int y, Color buttonColor) {
            buttonId = id;
            setTranslateX(x);
            setTranslateY(y);
            setPrefSize(125, 125);
            button = new Rectangle(125, 125);
            button.setArcHeight(20);
            button.setArcWidth(20);
            button.setFill(buttonColor);
            button.setStroke(Color.BLACK);
            button.setStrokeWidth(3);
            button.setOnMouseClicked(e -> {
                if(playerTurn) {
                    new ButtonClick().execute(buttonId);
                } else {

                }
            });

            getChildren().add(button);
        }
    }

    public class RoundComplete extends AsyncTask<Integer, Integer, Double> {

        @Override
        public void onPreExecute() {

        }

        @Override
        public Double doInBackground(Integer... integers) throws InterruptedException {
            Thread.sleep(200);
            publishProgress(2, 2);
            Thread.sleep(1000);
            publishProgress(0, 0);
            Thread.sleep(500);
            return null;
        }

        @Override
        public void progressCallback(Integer...params) {
            for (int i = 0; i < 9; i++) {
                gameButtons[i].button.setFill(colors[params[1]]);
            }
        }

        public void onPostExecute(Double result) throws Exception {
            message.setText("Simon's turn");
            playGame();
        }

    }

    public class DisplaySequence extends AsyncTask<Integer, Integer, Double> {

        @Override
        public void onPreExecute() {

        }

        @Override
        public Double doInBackground(Integer... integers) throws InterruptedException {
            addToSequence();
            for (int i = 0; i < simonSequence.size(); i++) {
                Thread.sleep(500);
                publishProgress(i, 1);
                Thread.sleep(500);
                publishProgress(i, 0);
            }
            return null;
        }

        @Override
        public void progressCallback(Integer...params) {
            gameButtons[simonSequence.get(params[0])].button.setFill(colors[params[1]]);
        }

        public void onPostExecute(Double result) throws Exception {
            playerTurn = true;
            message.setText("Your turn");
            playGame();
        }


    }
    public class ButtonClick extends AsyncTask<Integer, Integer, Double> {

        @Override
        public void onPreExecute() {

        }

        @Override
        public Double doInBackground(Integer... integers) throws InterruptedException {
            if (gameButtons[simonSequence.get(playerIndex)].buttonId == integers[0]) {
                publishProgress(integers[0], 1);
                Thread.sleep(50);
                publishProgress(integers[0], 0);
                playerIndex++;
            } else {
                running = false;
            }
            return null;
        }

        @Override
        public void progressCallback(Integer...params) {
            gameButtons[params[0]].button.setFill(colors[params[1]]);
        }

        public void onPostExecute(Double result) throws Exception {
            playGame();
        }


    }

}
