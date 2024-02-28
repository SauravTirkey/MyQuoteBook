import controllers.GameController;
import exceptions.DuplicateSymbolException;
import exceptions.PlayersCountDimensionMismatchException;
import modles.Game;
import modles.GameState;

import java.util.ArrayList;

import static modles.GameState.IN_PROGRESS;

public class Client {
    public static void main(String[] args) throws DuplicateSymbolException, PlayersCountDimensionMismatchException {
        GameController  gameController=new GameController();
        Game game=gameController.startGame(3,new ArrayList<>(),new ArrayList<>());

        while(gameController.checkState(game).equals((GameState).IN_PROGRESS)){
            gameController.printBoard(game);
            gameController.makeMove();
        }

    }
}
