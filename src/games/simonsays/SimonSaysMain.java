package games.simonsays;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SimonSaysMain extends Application {
    private static final int APP_W = 1280;
    private static final int APP_H = 720;
    private static final int NUM_CIRCLES = 5;
    int currentRound;
    int playerIndex;
    int blue;
    int red;
    int green;
    boolean running;
    boolean playerTurn;
    GameButton[] gameButtons;
    ArrayList<Integer> simonSequence = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent(), APP_W, APP_H);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createContent() {
        Pane root = new Pane();
        currentRound = 1;
        playerIndex = 0;
        running = true;
        playerTurn = false;
        GameButton butOne = new GameButton(480, 150, Color.BLUE);
        GameButton butTwo = new GameButton(615, 150, Color.BLUE);
        GameButton butThree = new GameButton(750, 150, Color.BLUE);
        GameButton butFour = new GameButton(480, 285, Color.BLUE);
        GameButton butFive = new GameButton(615, 285, Color.BLUE);
        GameButton butSix = new GameButton(750, 285, Color.BLUE);
        GameButton butSeven = new GameButton(480, 420, Color.BLUE);
        GameButton butEight = new GameButton(615, 420, Color.BLUE);
        GameButton butNine = new GameButton(750, 420, Color.BLUE);
        gameButtons = new GameButton[]{butOne, butTwo, butThree, butFour, butFive, butSix, butSeven, butEight, butNine};
        for(int i = 0; i < 9; i++) {
            root.getChildren().add(gameButtons[i]);
        }
        return root;
    }

    public class GameButton extends StackPane {
        boolean isEnabled;
        Color buttonColor;
        Rectangle button;

        GameButton(int x, int y, Color buttonColor) {
            setTranslateX(x);
            setTranslateY(y);
            setPrefSize(125, 125);
            button = new Rectangle(125, 125);
            button.setArcHeight(20);
            button.setArcWidth(20);
            button.setFill(buttonColor);
            button.setOnMouseClicked(e -> {
                if(playerTurn) {
                    button.setFill(Color.RED);
                } else {

                }
            });

            getChildren().add(button);
        }
    }

}
