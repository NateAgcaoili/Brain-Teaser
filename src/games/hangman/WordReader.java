package games.hangman;

import java.io.*;
import java.util.ArrayList;

public class WordReader {
    private static String fileName = null;
    private String word;
    private ArrayList<String> words = new ArrayList<String>();

    public WordReader() {
        this.fileName = "/dictionary/full_dictionary.txt";
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bf.readLine()) != null)
                words.add(line.split(":")[0]);
        }
        catch (Exception e) {
            System.out.println("Couldn't find/read file: " + fileName);
            System.out.println("Error message: " + e.getMessage());
        }
    }
    // Reading from words in personal dictionary (already discovered)
    public WordReader(String file) {
        this.fileName = file;
        try (InputStream in = getClass().getResourceAsStream(file);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bf.readLine()) != null)
                words.add(line.split(":")[0]);
        }
        catch (Exception e) {
            System.out.println("Couldn't find/read file: " + file);
            System.out.println("Error message: " + e.getMessage());
        }
    }

    public String getRandomWord() {
        if (words.isEmpty()) return "NO-DATA";
        this.word = words.get((int)(Math.random()*words.size()));
        return this.word;
    }
    public void addToDict() {
        BufferedWriter wr = null;
        String file = "/dictionary/dictionary.txt";
        try {
            wr = new BufferedWriter(new FileWriter(file));
            wr.write(this.word+"\n");
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(new File("/src/dictionary/dictionary.txt").getAbsolutePath());
        }

    }
}
