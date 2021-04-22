package games.sudoku.userinterface;

import games.sudoku.problemdomain.SudokuGame;

import java.io.IOException;

public interface IUserInterfaceContract {
    interface EventListener{
        void onSudokuInput(int x, int y, int input) throws IOException;
        void onDialogClick() throws IOException;
    }

    interface View {
        void setListener(IUserInterfaceContract.EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog(String message) throws IOException;
        void showError(String message);
    }
}
