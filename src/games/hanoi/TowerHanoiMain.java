package games.hanoi;

import games.GameOptions;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;


public class TowerHanoiMain extends Application {
    private static final int APP_W = 1280;
    private static final int APP_H = 720;
    private static final int NUM_CIRCLES = 5;

    private Optional<Circle> selectedCircle = Optional.empty();
    Color colorList[] = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE};

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent(), APP_W, APP_H);
        scene.setOnKeyPressed(e -> {
            char pressed = e.getText().toUpperCase().charAt(0);
            if (pressed == 'G') {
                JOptionPane.showMessageDialog(null, "You won!", "Winner!", JOptionPane.PLAIN_MESSAGE);
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
        //Image background = new Image("assets/images/backgrounds/game_bg.png");
        //ImageView bgView = new ImageView(background);
        //bgView.setFitHeight(APP_H);
        //bgView.setFitWidth(APP_W);
        //Group backgroundImage = new Group(bgView);
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
        //options.setAlignment(Pos.BOTTOM_CENTER);
        options.setPadding(new Insets(10, 10 ,10, 10));
        Pane root = new Pane();
        root.getChildren().addAll( options); //backgroundimage

        root.setPrefSize(400*3, 400);
        for (int i = 0; i < 3; i++) {
            Tower tower = new Tower(i*400, 150);

            if (i == 0) {
                for (int j = NUM_CIRCLES; j > 0; j--) {
                    Circle circle = new Circle(30 + j*15, null);
                    circle.setStroke(colorList[j % 5]);
                    circle.setStrokeWidth(circle.getRadius() / 30.0);

                    tower.addCircle(circle);
                }
            }

            root.getChildren().add(tower);
        }
        root.setStyle(
                "-fx-background-image: url(" +
                        "'/assets/images/backgrounds/towerofhanoi_bg.png'" +
                        "); " +
                        "-fx-background-size: stretch;" +
                        "-fx-background-color:  #ffd6dd;"
        );
//        root.setStyle("-fx-background-color:  #7cc9fc;" );
//        root.setStyle("-fx-background-image: url('/assets/images/backgrounds/game_bg.png');");
        return root;
    }

    private class Tower extends StackPane {
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
                } else {
                    selectedCircle = Optional.ofNullable(getTopMost());
                    selectedCircle.get().setStrokeWidth(selectedCircle.get().getRadius() / 15.0);
                }
            });

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
