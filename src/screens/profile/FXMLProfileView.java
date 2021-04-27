package screens.profile;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import store.avatar.Avatar;

public class FXMLProfileView {

    private Avatar selected;
    private int amt;

    @FXML
    private ImageView avatarImage;

    @FXML
    private Text money;

    public FXMLProfileView(Avatar avatar, int amt) {
        this.selected = avatar;
        this.amt = amt;
    }

    @FXML
    public void initialize() {
        avatarImage.setImage(selected.getImage());
        money.setText("Money: " + amt);
    }
}
