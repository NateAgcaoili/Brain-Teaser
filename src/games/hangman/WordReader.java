package games.hangman;

import java.io.*;
import java.util.*;

public class WordReader {
    private boolean personal_dict;
    private static String fileName;
    private String word;

    private Map<String, String> dict;

    public WordReader() {
        this.fileName = "/dictionary/full_dictionary.txt";
        this.personal_dict = false;
        this.dict = new HashMap<String,String>();

        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] word_def = line.split(":");
                dict.put(word_def[0],word_def[1]);
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't find/read file: " + fileName);
            System.out.println("Error message: " + e.getMessage());
        }
    }
    // Reading from words in personal dictionary (already discovered)
    public WordReader(String file) {
        this.fileName = file;
        this.personal_dict = true;
        this.dict = new HashMap<String,String>();

        try (InputStream in = getClass().getResourceAsStream(file);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {

            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] word_def = line.split(":");
                dict.put(word_def[0],word_def[1]);
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't find/read file: " + file);
            System.out.println("Error message: " + e.getMessage());
        }
    }
    public Map<String, String> getWordsWithDefinition() {
        return dict;
    }

    public String getRandomWord() {

        if (dict.isEmpty())
            return "NO-DATA";
        Object[] keys = this.dict.keySet().toArray();
        Object key = keys[new Random().nextInt(keys.length)];
        this.word = key.toString();
        if (!this.personal_dict) {
            addToDict();
        }
        return this.word;
    }

    public void addToDict(String word) {
        String file = "src/dictionary/dictionary.txt";
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(file, true))){
            wr.append(word).append("\n");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(new File("src/dictionary/dictionary.txt").getAbsolutePath());
            System.out.println(new File(".").getAbsolutePath());
        }
    }

    public void addToDict() {
        String new_def = this.word + ":" + dict.get(this.word);
        addToDict(new_def);
    }
}
