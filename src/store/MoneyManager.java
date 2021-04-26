package store;

import java.io.*;

public class MoneyManager {

    private String fileName;
    private int money;

    public MoneyManager() {
        this.fileName = "/data/money.txt";

        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader bf = new BufferedReader(new InputStreamReader(in))) {
            int amt = Integer.parseInt(bf.readLine());
            this.money = amt;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int get() {
        return money;
    }

    public void add(int amt) {
        this.money = (money + amt);
    }

    public void rem(int amt) {
        this.money = (money - amt);
    }

    public void saveToFile() {
        String path = "src/data/money.txt";
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path, true))){

            PrintWriter writer = new PrintWriter(path);
            writer.print("");
            writer.close();

            wr.append(money + "").append("\n");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(new File("/data/money.txt").getAbsolutePath());
            System.out.println(new File(".").getAbsolutePath());
        }
    }

}
