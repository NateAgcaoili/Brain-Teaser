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
        currentRound = 1;
        playerIndex = 0;
        running = true;
        playerTurn = false;
        GameButton butOne = new GameButton(615, 285, Color.BLUE);
        Pane root = new Pane();
        root.getChildren().add(butOne);
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
