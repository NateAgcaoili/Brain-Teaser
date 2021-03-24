package games.hanoi;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.Image;
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
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContent() {
        Image background = new Image("assets/images/backgrounds/game_bg.png");
        ImageView bgView = new ImageView(background);
        bgView.setFitHeight(APP_H);
        bgView.setFitWidth(APP_W);
        Group backgroundImage = new Group(bgView);
        Pane root = new Pane();
        root.getChildren().add(backgroundImage);
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
        return root;
    }

    private class Tower extends StackPane {
        Tower(int x, int y) {

            setTranslateX(x);
            setTranslateY(y);
            setPrefSize(400, 400);

            Rectangle bg = new Rectangle(25, 25);
            bg.setStroke(Color.RED);
            bg.setStrokeWidth(0);
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

            getChildren().add(bg);
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

    public static void main(String[] args) {
        launch(args);
    }
}
