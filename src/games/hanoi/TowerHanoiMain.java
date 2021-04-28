package games.hanoi;

import games.GameOptions;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import screens.FXMLGameScreenController;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;


public class TowerHanoiMain extends Application {
    private static final int APP_W = 1280;
    private static final int APP_H = 720;
    private static final int NUM_CIRCLES = 5;
    Long startTime = System.currentTimeMillis();
    int finishedTime;
    SimpleIntegerProperty score = new SimpleIntegerProperty();



    private Optional<Circle> selectedCircle = Optional.empty();
    Color colorList[] = {Color.RED, Color.GREEN, Color.BLUE, Color.DARKCYAN, Color.ORANGE};

    public TowerHanoiMain() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        int highScore = read_highScore_from_file();
        Scene scene = new Scene(createContent(), APP_W, APP_H);
        scene.setOnKeyPressed(e -> {
            char pressed = e.getText().toUpperCase().charAt(0);
            if (pressed == 'G') {
                finishedTime = (int)((System.currentTimeMillis() - startTime)/1000);
                JOptionPane.showMessageDialog(null, "You won!\nTime taken : "
                        + (int)(finishedTime/60) + " minutes " + (finishedTime - ((finishedTime/60)*60)
                        + " Seconds"), "Winner!", JOptionPane.PLAIN_MESSAGE);
                if(finishedTime > 0 && (finishedTime/60) < 1){
                    score.set(1000);
                }else if((finishedTime/60) > 1 && (finishedTime/60) < 3){
                    score.set(750);
                }
                else if((finishedTime/60) > 3 && (finishedTime/60) < 5){
                    score.set(500);
                }
                else {
                    score.set(200);
                }
                if(score.intValue() >= highScore){
                    write_highscore_to_file(score);
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public Parent createContent() {
        HBox options = new HBox();
        Button optionsButton = new Button("OPTIONS");
        optionsButton.setOnAction(e -> {
            try {
                openOptions(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        options.getChildren().add(optionsButton);
        options.setPadding(new Insets(10, 10 ,10, 10));
        Pane root = new Pane();
        root.getChildren().addAll( options); //backgroundimage

        root.setPrefSize(400*3, 400);

            Tower tower1 = new Tower(0*400, 150);
            Tower tower2 = new Tower(1*400, 150);
            Tower tower3 = new Tower(2*400, 150);



            if (0 == 0) {
                for (int j = NUM_CIRCLES; j > 0; j--) {
                    Circle circle = new Circle(30 + j*15, null);
                    circle.setStroke(colorList[j % 5]);
                    circle.setStrokeWidth(circle.getRadius() / 30.0);

                    tower1.addCircle(circle);

                }
            }

            root.getChildren().add(tower1);
            root.getChildren().add(tower2);
            root.getChildren().add(tower3);



        root.setStyle(
                "-fx-background-image: url(" +
                        "'/assets/images/backgrounds/towerofhanoi_bg.png'" +
                        "); " +
                        "-fx-background-size: stretch;" +
                        "-fx-background-color:  #ffd6dd;"
        );
        return root;
    }
    private void write_highscore_to_file(SimpleIntegerProperty highScore) {
        FXMLGameScreenController controller = new FXMLGameScreenController();
        int score = highScore.intValue();
        String[] info = {"towers", String.valueOf(score)};
        controller.write_highscores(info);

    }

    public int read_highScore_from_file() {
        int n = 1;//equates to line 3 in the text file highscores.txt so the function will read the 3rd line, use this function for every game after hangman, and don't change the highscores.txt format.
        int savedHighScore = 0;
        try {
            String s = Files.readAllLines(Paths.get("src/scoreboard/highscores.txt")).get(n);
            int ind = s.indexOf("-");
            if (ind != -1) {
                String value = s.substring(ind + 1);
                savedHighScore += Integer.parseInt(value);
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        return savedHighScore;
    }


    public class Tower extends StackPane {
        Tower(int x, int y) {

            setTranslateX(x);
            setTranslateY(y);
            setPrefSize(400, 400);

            Rectangle bg = new Rectangle(250, 250);
            bg.setFill(Color.TRANSPARENT);
            Rectangle black = new Rectangle(25, 25);
            bg.setOnMouseClicked(e -> {
                if (selectedCircle.isPresent()) {
                    addCircle(selectedCircle.get());
                    selectedCircle.get().setStrokeWidth(selectedCircle.get().getRadius() / 30.0);
                    selectedCircle = Optional.empty();
                } else
                    selectedCircle = Optional.ofNullable(getTopMost());
                    selectedCircle.get().setStrokeWidth(selectedCircle.get().getRadius() / 15.0);

            } );
            getChildren().addAll(black, bg);
        }



        private Circle getTopMost() {
            return getChildren().stream()
                    .filter(n -> n instanceof Circle)
                    .map(n -> (Circle) n)
                    .min(Comparator.comparingDouble(Circle::getRadius))
                    .orElse(null);
        }


        void addCircle(Circle circle) {
            Circle topMost = getTopMost();

            if (topMost == null) {
                getChildren().add(circle);
            } else {
                if (circle.getRadius() < topMost.getRadius()) {
                    getChildren().add(circle);
                }
            }
        }
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


    public static void main(String[] args) {
        launch(args);
    }
}