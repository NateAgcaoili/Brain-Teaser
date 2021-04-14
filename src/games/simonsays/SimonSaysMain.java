package games.simonsays;

import javafx.application.Application;
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
    ArrayList<Integer> simonSequence = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
