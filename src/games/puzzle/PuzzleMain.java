package games.puzzle;

import games.GameOptions;
import games.directions;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import screens.FXMLGameScreenController;
import store.MoneyManager;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manoj
 */

public class PuzzleMain extends Application {

    private Timeline timeline;
    private static final int APP_W = 1280;
    private static final int APP_H = 767; //leave this, for some reason when using 720 the window gets smaller
    private static final int score_per_piece = 15;
    public static SimpleIntegerProperty score = new SimpleIntegerProperty();
    public static SimpleIntegerProperty highScore = new SimpleIntegerProperty();


    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        highScore.set(read_highScore_from_file());
        // load puzzle image
        Image image = get_new_puzzle();
        int numOfColumns = (int) (image.getWidth() / Piece.SIZE);
        int numOfRows = (int) (image.getHeight() / Piece.SIZE);
        // create desk
        final Desk desk = new Desk(numOfColumns, numOfRows);
        // create puzzle pieces
        final List<Piece> pieces  = new ArrayList<>();
        for (int col = 0; col < numOfColumns; col++) {
            for (int row = 0; row < numOfRows; row++) {
                int x = col * Piece.SIZE;
                int y = row * Piece.SIZE;
                final Piece piece = new Piece(image, x, y, row>0, col>0,
                        row<numOfRows -1, col < numOfColumns -1,
                        desk.getWidth(), desk.getHeight());
                pieces.add(piece);
            }
        }
        desk.getChildren().addAll(pieces);

        // create button box
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setStyle("-fx-font-size: 2em;");
        shuffleButton.setOnAction(actionEvent -> {
            if (timeline != null) timeline.stop();
            timeline = new Timeline();
            for (final Piece piece : pieces) {
                piece.setActive();
                double shuffleX = Math.random() *
                        (desk.getWidth() - Piece.SIZE + 48f ) -
                        24f - piece.getCorrectX();
                double shuffleY = Math.random() *
                        (desk.getHeight() - Piece.SIZE + 30f ) -
                        15f - piece.getCorrectY();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1),
                                new KeyValue(piece.translateXProperty(), shuffleX),
                                new KeyValue(piece.translateYProperty(), shuffleY)));
            }
            timeline.playFromStart();
        });
        Button solveButton = new Button("Solve");
        solveButton.setStyle("-fx-font-size: 2em;");
        solveButton.setOnAction(actionEvent -> {
            if (timeline != null) timeline.stop();
            timeline = new Timeline();
            for (final Piece piece : pieces) {
                piece.setInactive();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1),
                                new KeyValue(piece.translateXProperty(), 0),
                                new KeyValue(piece.translateYProperty(), 0)));
            }
            timeline.playFromStart();
            stopGame();
            score.set(0);
        });
        Button optionsButton = new Button("Options");
        optionsButton.setStyle("-fx-font-size: 2em;");
        optionsButton.setOnAction(e -> {
            try {
                openOptions(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

//        Button howToPlayButton = new Button("HOW TO PLAY");
//        howToPlayButton.setStyle("-fx-font-size: 2em;");
//        howToPlayButton.setOnAction(e -> {
//            try{
//                openHowToPlay(e);
//            }catch (IOException ioException){
//                ioException.printStackTrace();
//            }
//        });

        Text textScore = new Text();
        textScore.textProperty().bind(score.asString().concat(" Points"));
        textScore.setStyle("-fx-font-size: 2em;");

        HBox buttonBox = new HBox(8);
        buttonBox.getChildren().addAll(shuffleButton, solveButton, optionsButton, textScore);
        // create vbox for desk and buttons
        VBox vb = new VBox(10);
        vb.getChildren().addAll(desk,buttonBox);
        root.getChildren().addAll(vb);
        primaryStage.setWidth(1500);
        primaryStage.setHeight(1000);
    }

    /**
     * Node that represents the playing area/ desktop where the puzzle pices sit
     */
    private static class Desk extends Pane {
        Desk(int numOfColumns, int numOfRows) {
            setStyle("-fx-background-color: #cccccc; " +
                    "-fx-border-color: #464646; " +
                    "-fx-effect: innershadow( two-pass-box , rgba(0,0,0,0.8) , 15, 0.0 , 0 , 4 );");
            double DESK_WIDTH = Piece.SIZE * numOfColumns;
            double DESK_HEIGHT = Piece.SIZE * numOfRows;
            setPrefSize(DESK_WIDTH,DESK_HEIGHT);
            setMaxSize(DESK_WIDTH, DESK_HEIGHT);
            autosize();
            // create path for lines
            Path grid = new Path();
            grid.setStroke(Color.rgb(70, 70, 70));
            getChildren().add(grid);
            // create vertical lines
            for (int col = 0; col < numOfColumns - 1; col++) {
                grid.getElements().addAll(
                        new MoveTo(Piece.SIZE + Piece.SIZE * col, 5),
                        new LineTo(Piece.SIZE + Piece.SIZE * col, Piece.SIZE * numOfRows - 5)
                );
            }
            // create horizontal lines
            for (int row = 0; row < numOfRows - 1; row++) {
                grid.getElements().addAll(
                        new MoveTo(5, Piece.SIZE + Piece.SIZE * row),
                        new LineTo(Piece.SIZE * numOfColumns - 5, Piece.SIZE + Piece.SIZE * row)
                );
            }
        }
        @Override protected void layoutChildren() {}
    }


    /**
     * Node that represents a puzzle piece
     */
    public static class Piece extends Parent {
        public static final int SIZE = 100;
        private final double correctX;
        private final double correctY;
        private final boolean hasTopTab;
        private final boolean hasLeftTab;
        private final boolean hasBottomTab;
        private final boolean hasRightTab;
        private double startDragX;
        private double startDragY;
        private Point2D dragAnchor;

        public Piece(Image image, final double correctX, final double correctY,
                     boolean topTab, boolean leftTab, boolean bottomTab, boolean rightTab,
                     final double deskWidth, final double deskHeight) {
            this.correctX = correctX;
            this.correctY = correctY;
            this.hasTopTab = topTab;
            this.hasLeftTab = leftTab;
            this.hasBottomTab = bottomTab;
            this.hasRightTab = rightTab;
            // configure clip
            Shape pieceClip = createPiece();
            pieceClip.setFill(Color.WHITE);
            pieceClip.setStroke(null);
            // add a stroke
            Shape pieceStroke = createPiece();
            pieceStroke.setFill(null);
            pieceStroke.setStroke(Color.BLACK);
            // create image view
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setClip(pieceClip);
            setFocusTraversable(true);
            getChildren().addAll(imageView, pieceStroke);
            // turn on caching so the jigsaw piece is fasr to draw when dragging
            setCache(true);
            // start in inactive state
            setInactive();
            // add listeners to support dragging
            setOnMousePressed(me -> {
                toFront();
                startDragX = getTranslateX();
                startDragY = getTranslateY();
                dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
            });
            setOnMouseReleased(me -> {
                if (getTranslateX() < (10) && getTranslateX() > (- 10) &&
                        getTranslateY() < (10) && getTranslateY() > (- 10)) {
                    score.set(score.get() + score_per_piece);
                    setTranslateX(0);
                    setTranslateY(0);
                    setInactive();



                }
            });
            setOnMouseDragged(me -> {
                double newTranslateX = startDragX
                        + me.getSceneX() - dragAnchor.getX();
                double newTranslateY = startDragY
                        + me.getSceneY() - dragAnchor.getY();
                double minTranslateX = - 45f - correctX;

                // Max translate default values are x:50f y:70f
                // Change to drag around on the screen.
                double maxTranslateX = (deskWidth - Piece.SIZE + 250f ) - correctX;
                double minTranslateY = - 30f - correctY;
                double maxTranslateY = (deskHeight - Piece.SIZE +250f ) - correctY;
                if ((newTranslateX> minTranslateX ) &&
                        (newTranslateX< maxTranslateX) &&
                        (newTranslateY> minTranslateY) &&
                        (newTranslateY< maxTranslateY)) {
                    setTranslateX(newTranslateX);
                    setTranslateY(newTranslateY);
                }
            });
        }

        private Shape createPiece() {
            Shape shape = createPieceRectangle();
            if (hasRightTab) {
                shape = Shape.union(shape,
                        createPieceTab(69.5f, 0f, 10f, 17.5f, 50f, -12.5f, 11.5f,
                                25f, 56.25f, -14f, 56.25f, 14f));
            }
            if (hasBottomTab) {
                shape = Shape.union(shape,
                        createPieceTab(0f, 69.5f, 17.5f, 10f, -12.5f, 50f, 25f,
                                11f, -14f, 56.25f, 14f, 56.25f));
            }
            if (hasLeftTab) {
                shape = Shape.subtract(shape,
                        createPieceTab(-31f, 0f, 10f, 17.5f, -50f, -12.5f, 11f,
                                25f, -43.75f, -14f, -43.75f, 14f));
            }
            if (hasTopTab) {
                shape = Shape.subtract(shape,
                        createPieceTab(0f, -31f, 17.5f, 10f, -12.5f, -50f, 25f,
                                12.5f, -14f, -43.75f, 14f, -43.75f));
            }
            shape.setTranslateX(correctX);
            shape.setTranslateY(correctY);
            shape.setLayoutX(50f);
            shape.setLayoutY(50f);
            return shape;
        }

        private Rectangle createPieceRectangle() {
            Rectangle rec = new Rectangle();
            rec.setX(-50);
            rec.setY(-50);
            rec.setWidth(SIZE);
            rec.setHeight(SIZE);
            return rec;
        }

        private Shape createPieceTab(double eclipseCenterX, double eclipseCenterY, double eclipseRadiusX, double eclipseRadiusY,
                                     double rectangleX, double rectangleY, double rectangleWidth, double rectangleHeight,
                                     double circle1CenterX, double circle1CenterY,
                                     double circle2CenterX, double circle2CenterY) {
            Ellipse e = new Ellipse(eclipseCenterX, eclipseCenterY, eclipseRadiusX, eclipseRadiusY);
            Rectangle r = new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
            Shape tab = Shape.union(e, r);
            Circle c1 = new Circle(circle1CenterX, circle1CenterY, 6.25);
            tab = Shape.subtract(tab, c1);
            Circle c2 = new Circle(circle2CenterX, circle2CenterY, 6.25);
            tab = Shape.subtract(tab, c2);
            return tab;
        }

        public void setActive() {
            setDisable(false);
            setEffect(new DropShadow());
            toFront();
        }

        public void setInactive() {
            setEffect(null);
            setDisable(true);
            toBack();
        }

        public double getCorrectX() { return correctX; }

        public double getCorrectY() { return correctY; }
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
                gameWindow.setWidth(APP_W);
                gameWindow.setHeight(APP_H);
                gameWindow.setScene(gameScene);
                gameWindow.show();
                break;
            case 2:
                Parent root = FXMLLoader.load(getClass().getResource("/screens/FXMLMainscreen.fxml"));
                Stage homeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                homeWindow.setWidth(APP_W);
                homeWindow.setHeight(APP_H);
                Scene home = new Scene(root);
                homeWindow.setScene(home);
                homeWindow.show();
                break;
            default:
                System.out.println("Unknown");
        }
    }
//    private void openHowToPlay(ActionEvent event) throws IOException{
//        directions.display();
//        Parent root = FXMLLoader.load(getClass().getResource("/screens/FXMLPuzzleDirections.fxml"));
//        Stage gameWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
//        Scene directionsScene = new Scene(root);
//        gameWindow.setScene(directionsScene);
//        gameWindow.show();
//    }
    /*private boolean checkHighScore(SimpleIntegerProperty score){
        return score.lessThan(highScore).get();
    }*/
    private void stopGame(){
        if(score.intValue() >= highScore.intValue()){
            JOptionPane.showMessageDialog(null, "You beat your high score of " + highScore.intValue() +
                    " with a score of " + score.intValue(), "High Score!", JOptionPane.PLAIN_MESSAGE);
            highScore.set(score.get());
            write_highscore_to_file(highScore);

            int amt = 1000;
            MoneyManager moneyManager = new MoneyManager();
            moneyManager.add(amt);
            JOptionPane.showMessageDialog(null, "You earned: " + amt, "Money", JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "You didn't beat your high score " + highScore.intValue() + " with a score of " + score.intValue(), "High Score!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void write_highscore_to_file(SimpleIntegerProperty highScore) {
        FXMLGameScreenController controller = new FXMLGameScreenController();
        int score = highScore.intValue();
        String[] info = {"jigsaw", String.valueOf(score)};
        controller.write_highscores(info);

    }
    public int read_highScore_from_file() {
        int n = 2;//equates to line 3 in the text file highscores.txt so the function will read the 3rd line, use this function for every game after hangman, and don't change the highscores.txt format.
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



    /*  Hard code each puzzle in and add it to the puzzle list.
            using File.list() returns null for the directory
         */
    public static Image get_new_puzzle() {
        Image waterfall = new Image("assets/images/puzzleimgs/puzzle1.jpg");
        Image house = new Image("assets/images/puzzleimgs/puzzle2.jpg");
        Image fox = new Image("assets/images/puzzleimgs/puzzle3.jpg");

        List<Image> puzzles = new ArrayList<>();

        puzzles.add(waterfall);
        puzzles.add(house);
        puzzles.add(fox);

        int index = (int)(Math.random() * puzzles.size());
        return  puzzles.get(index);

    }

    @Override public void start(Stage primaryStage) {
        init(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
