package games.sudoku.problemdomain;

import java.io.IOException;

public interface IStorage {
    void updateGameData(SudokuGame game) throws IOException, IOException;
    SudokuGame getGameData() throws IOException;
}
