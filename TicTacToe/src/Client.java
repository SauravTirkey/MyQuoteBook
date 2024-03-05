import controllers.GameController;
import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneBotException;
import exceptions.PlayersCountDimensionMismatchException;
import modles.*;
import strategies.ColWinningStrategy;
import strategies.DiagWinningStrategy;
import strategies.RowWinningStrategy;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int dimensions = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(1L, "Sabahul", new Symbol('X'), PlayerType.HUMAN)
        );
        players.add(
                new Bot(2L, "Vignesh", new Symbol('O'), BotDifficultyLevel.EASY)
        );
        List<WinningStrategy> winningStrategies =List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagWinningStrategy()
        );

        Game game=null;
        try{
        game=gameController.startGame(dimensions,players,winningStrategies);
        while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            gameController.printBoard(game);
            System.out.println("Do you want to undo? (y/n");
            String undoAnswer=scanner.next();
            if(undoAnswer.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }
         }catch(Exception ex){
            System.out.println("Something went wrong");
        }


        gameController.printBoard(game);
        System.out.println("Game is over");

        GameState gameState=gameController.checkState(game);

        if(gameState.equals(GameState.DRAW)){
            System.out.println("Game has drawn");

        }else {
            System.out.println(gameController.getWinner(game)+" is the winner");
        }
    }

}