package games.sudoku.buildlogic;

import games.sudoku.computationlogic.GameLogic;
import games.sudoku.persistence.LocalStorageImpl;
import games.sudoku.problemdomain.IStorage;
import games.sudoku.problemdomain.SudokuGame;
import games.sudoku.userinterface.IUserInterfaceContract;
import games.sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try{
            initialState = storage.getGameData();
        } catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
