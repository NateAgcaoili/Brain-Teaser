package screens.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import store.avatar.Avatar;
import store.avatar.AvatarManager;

import java.io.IOException;

public class FXMLSettingsController {

    private AvatarManager avatarManager;

    @FXML
    private GridPane gridPane;

    public FXMLSettingsController(AvatarManager avatarManager) {
        this.avatarManager = avatarManager;
    }

    public void initialize() {
        int i = 0;
        int width = 3;

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        System.out.println(avatarManager.getSelected().name());

        for(Avatar avatar : Avatar.values()) {
            GridPane buttonPane = new GridPane();
            buttonPane.setAlignment(Pos.CENTER);
            buttonPane.setHgap(20);

            int x = i % width;
            int y = i / width;

            ImageView avatarImage = new ImageView();
            avatarImage.setImage(avatar.getImage());
            avatarImage.setFitHeight(100);
            avatarImage.setFitWidth(100);

            buttonPane.add(avatarImage, 0, 0);
            Button button = new Button();
            if(!avatarManager.getAvatars().contains(avatar)) {
                button.setText("Purchase In Store");
                button.setStyle("-fx-background-color: #fc7c7c");
            } else {
                button.setText("Select");
                button.setStyle("-fx-background-color: #7cfca7");
                button.setOnAction(e -> {
                    avatarManager.selectAvatar(avatar);

                    try {
                        backButtonPushed(e);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            }

            buttonPane.add(button, 1, 0);
            gridPane.add(buttonPane, x, y);
            i++;
        }
        gridPane.setAlignment(Pos.CENTER);
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXMLMainscreen.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("../FXMLMainscreen.fxml"));
        Scene mainScene = new Scene(loader.load());
        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.setResizable(false);
        window.show();
    }
}
