package store.avatar;

import java.io.*;
import java.util.LinkedList;

public class AvatarManager {

    private String fileName;
    private final LinkedList<Avatar> avatars;
    public AvatarManager(LinkedList<Avatar> avatarList) {
        this.avatars = avatarList;
    }

    public AvatarManager() {
        this.fileName = "/data/avatars.txt";
        this.avatars = new LinkedList<>();
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bf.readLine()) != null) {
                avatars.add(Avatar.valueOf(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Avatar> getAvatars() {
        return avatars;
    }

    public Avatar getSelected() {
        return avatars.getFirst();
    }

    public void selectAvatar(Avatar avatar) {
        avatars.remove(avatar);
        avatars.addFirst(avatar);
    }

    public void saveToFile() {
        String path = "src/data/avatars.txt";
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path, true))){

            // Clear existing file
            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();

            // Loop through list and save to file
            // [0 |#] -> [1 |#] -> [2 |#] -> [3 |#]
            for(Avatar avatar : avatars) {
                wr.append(avatar.name()).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(new File("src/store/avatar/avatars.txt").getAbsolutePath());
            System.out.println(new File(".").getAbsolutePath());
        }
    }

}
