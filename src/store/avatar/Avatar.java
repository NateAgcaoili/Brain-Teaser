package store.avatar;

import javafx.scene.image.Image;

public enum Avatar {

    STAGE_0("file:src/assets/images/avatars/avatar_0.png", 100),
    STAGE_1("file:src/assets/images/avatars/avatar_1.png", 250),
    STAGE_2("file:src/assets/images/avatars/avatar_2.png", 500),
    STAGE_3("file:src/assets/images/avatars/avatar_3.png", 1000),
    STAGE_4("file:src/assets/images/avatars/avatar_4.png", 2000),
    STAGE_5("file:src/assets/images/avatars/avatar_5.png", 4000),
    STAGE_6("file:src/assets/images/avatars/avatar_6.png", 8000),
    STAGE_7("file:src/assets/images/avatars/avatar_7.png", 16000),
    STAGE_8("file:src/assets/images/avatars/avatar_8.png", 32000);

    private Image image;
    private int cost;

    private Avatar(String file, int cost) {
        this.image = new Image(file);
        this.cost = cost;
    }

    public Image getImage() {
        return image;
    }

    public int getCost() {
        return cost;
    }
}
