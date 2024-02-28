package modles;

import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneBotException;
import exceptions.PlayersCountDimensionMismatchException;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Board board;
    private List<WinningStrategy> winningStrategies;
    private  int nextMovePlayerIndex;
    private Player winner;
    private  GameState gameState;
    private  List<Move> move;

public Game(int dimension, List<Player> players,List<WinningStrategy> winningStrategies){
this.players=players;
this.winningStrategies=winningStrategies;
this.nextMovePlayerIndex=0;
this.gameState=GameState.IN_PROGRESS;
this.move=new ArrayList<>();
}

public Builder getBuilder(){
    return new Builder();
}

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public List<Move> getMove() {
        return move;
    }

    public static class Builder{
        private List<Player> players;
        private  List<WinningStrategy> winningStrategies;
        private int dimensions;

     public Builder setDimension(int dimension){
         this.dimensions=dimension;
         return  this;
     }
     public Builder setPlayers(List<Player> players){
         this.players=players;
         return  this;
     }
public  Builder addPlayer(Player player){
         this.players.add(player);
         return  this;
}
public Builder SetWinningStrategies(List<WinningStrategy> winningStrategies){
         this.winningStrategies=winningStrategies;
         return  this;
}
public Builder addWinningStrategies(WinningStrategy winningStrategy){
         this.winningStrategies.add(winningStrategy);
         return this;
}

public Game build(){
validate();
return new Game(dimensions,players,winningStrategies);
}
private void validate() throws MoreThanOneBotException,PlayersCountDimensionMismatchException,DuplicateSymbolException{
validateBotCounts();
validateDimensionsAndPlayerCount();
validateUniqueSymbolsForPlayers();
}
private void validateBotCounts() throws MoreThanOneBotException{
         int botCount=0;
         for(Player player:players){
             if(player.getPlayerType().equals(PlayerType.BOT)){
                 botCount+=1;
             }
         }
         if(botCount>1){
             throw new MoreThanOneBotException();
         }
}

private void validateDimensionsAndPlayerCount() throws PlayersCountDimensionMismatchException {
         if(players.size()!=dimensions-1){
             throw new PlayersCountDimensionMismatchException();
         }
}
private void validateUniqueSymbolsForPlayers() throws DuplicateSymbolException {
    Map<Character,Integer> symbolCounts =new HashMap<>();
    for(Player player:players){
        if(!symbolCounts.containsKey(player.getSymbol().getaChar())){
            symbolCounts.put(player.getSymbol().getaChar(), 0);
        }
        symbolCounts.put(
                player.getSymbol().getaChar(),
                symbolCounts.get(player.getSymbol().getaChar())+1
        );
        if(symbolCounts.get(player.getSymbol().getaChar())>1){
            throw new DuplicateSymbolException();
        }

    }
}
    }
public void printBoard(){
board.printBoard();
}
public  void makeMove(){
      
}

}
