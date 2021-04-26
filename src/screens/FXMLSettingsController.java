package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import store.avatar.Avatar;
import store.avatar.AvatarManager;

import java.io.IOException;

public class FXMLSettingsController {

    private AvatarManager avatarManager;

    @FXML
    private GridPane gridPane;

    public void initialize() {
        int i = 0;
        int width = 3, height = 3;

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for(Avatar avatar : Avatar.values()) {

            GridPane buttonPane = new GridPane();
            buttonPane.setAlignment(Pos.CENTER);
            buttonPane.setHgap(20);

            int x = i % width;
            int y = i / width;
            Button button = new Button();
            button.setText("Select");
            button.setStyle("-fx-background-color: #7cfca7");


            button.setOnAction(e -> {
                System.out.println("Selecting " + avatar.name());

                avatarManager.selectAvatar(avatar);
                avatarManager.saveToFile();

//                try {
//                    backButtonPushed(e);
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
            });

            ImageView avatarImage = new ImageView();
            avatarImage.setImage(avatar.getImage());
            avatarImage.setFitHeight(100);
            avatarImage.setFitWidth(100);

            buttonPane.add(avatarImage, 0, 0);
            if(avatarManager.getAvatars().contains(avatar)) {
                buttonPane.add(button, 1, 0);
            }

            gridPane.add(buttonPane, x, y);
            i++;
        }
        gridPane.setAlignment(Pos.CENTER);
    }

    public FXMLSettingsController() {
        avatarManager = new AvatarManager();
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent aboutParent = FXMLLoader.load(getClass().getResource("FXMLMainscreen.fxml"));
        Scene mainScene = new Scene(aboutParent);
        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.setResizable(false);
        window.show();
    }
}
