package controllers;

import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneBotException;
import exceptions.PlayersCountDimensionMismatchException;
import modles.Game;
import modles.GameState;
import modles.Player;
import strategies.WinningStrategy;

import java.util.List;

public class GameController {
  public  Game startGame(int dimensions,
                   List<Player> players,
                   List<WinningStrategy> winningStrategies) throws DuplicateSymbolException, MoreThanOneBotException, PlayersCountDimensionMismatchException {
    return Game.getBuilder()
            .setPlayers(players)
            .setWinningStrategy(winningStrategies)
            .setDimension(dimensions)
            .build();
    }
    public void printBoard(Game game){
     game.printBoard();
    }

    public GameState checkState(Game game){
    return  game.getGameState();

    }
    public void makeMove(Game game){
      game.makeMove();

    }

    public void undo(Game game){
      game.undo();
    }
    public  String getWinner(Game game){
      return game.getWinner().getName();
    }
}
