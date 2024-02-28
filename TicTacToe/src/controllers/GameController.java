package controllers;

import exceptions.DuplicateSymbolException;
import modles.Game;
import modles.GameState;
import modles.Player;
import strategies.WinningStrategy;

import java.util.List;

public class GameController {
  public  Game startGame(int dimensions,
                   List<Player> players,
                   List<WinningStrategy>winningStrategies) throws DuplicateSymbolException {

        return  Game.getBuilder()
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
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
}
