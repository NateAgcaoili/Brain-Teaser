import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException, InterruptedException, URISyntaxException {
        //AudioClip startSound = new AudioClip("file:src/assets/audio/brain_teaser_start.wav");
        Parent root = FXMLLoader.load(getClass().getResource("screens/FXMLMainscreen.fxml"));
        Image logo = new Image("/assets/images/icons/loadingscreen_logo.png");
        ImageView logoView = new ImageView();
        logoView.setImage(logo);
        logoView.setX(360);
        logoView.setY(0);
        logoView.setFitHeight(580);
        logoView.setFitWidth(580);
        Group images = new Group(logoView);
        Scene loadingScene = new Scene(images, 1280, 720);
        Scene scene = new Scene((root));
        primaryStage.getIcons().add(new Image("/assets/images/icons/window_icon.png"));
        primaryStage.setScene(loadingScene);
        primaryStage.setTitle("Brain Teaser");
        primaryStage.setResizable(false);
        primaryStage.show();
        //startSound.play();
        PauseTransition loadingDelay = new PauseTransition(Duration.seconds(2));
        loadingDelay.setOnFinished(event -> primaryStage.setScene(scene));
        loadingDelay.play();
    }
}