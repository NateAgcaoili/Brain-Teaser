package games.sudoku.userinterface.logic;

import games.sudoku.computationlogic.GameLogic;
import games.sudoku.constants.GameState;
import games.sudoku.constants.Messages;
import games.sudoku.problemdomain.IStorage;
import games.sudoku.problemdomain.SudokuGame;
import games.sudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;

    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view){
        this.storage = storage;
        this.view = view;
    }


    @Override
    public void onSudokuInput(int x, int y, int input) throws IOException {
        SudokuGame gameData = storage.getGameData();
        int[][] newGridState = gameData.getCopyOfGridState();
        newGridState[x][y] = input;

        gameData = new SudokuGame(
                GameLogic.checkForCompletion(newGridState),
                newGridState
        );
        storage.updateGameData(gameData);

        view.updateSquare(x, y, input);

        if (gameData.getGameState() == GameState.COMPLETE){
            view.showDialog(Messages.GAME_COMPLETE);
        }

    }

    @Override
    public void onDialogClick() throws IOException {
        storage.updateGameData(
                GameLogic.getNewGame()
        );

        view.updateBoard(storage.getGameData());
    }


}
