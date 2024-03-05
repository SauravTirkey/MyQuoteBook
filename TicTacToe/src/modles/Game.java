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
    private List<Player>  players;
    private Board board;
    private List<WinningStrategy> winningStrategies;
    private  int nextMovePlayerIndex;
    private  Player winner;
    private  GameState gameState;
    private  List<Move> moves;

public Game(int dimension, List<Player> players,List<WinningStrategy> winningStrategies){
this.players=players;
this.winningStrategies=winningStrategies;
this.nextMovePlayerIndex=0;
this.gameState=GameState.IN_PROGRESS;
this.moves=new ArrayList<>();
this.board=new Board(dimension);
}

public static Builder getBuilder(){
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
        return moves;
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
        public Builder setWinningStrategy(List<WinningStrategy> winningStrategies){
         this.winningStrategies=winningStrategies;
         return  this;
        }
        public Builder addWinningStrategies(WinningStrategy winningStrategy){
         this.winningStrategies.add(winningStrategy);
         return this;
        }

public Game build() throws DuplicateSymbolException, PlayersCountDimensionMismatchException, MoreThanOneBotException {
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

private boolean validateMove(Move move){
    int row=move.getCell().getRow();
    int col=move.getCell().getCol();
    if(row>=board.getSize()){
        return  false;
    }
    if(col>=board.getSize()){
        return  false;
    }
    if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
        return true;
    }

    return false;

}

private boolean checkWinner(Board board,Move move){
for(WinningStrategy winningStrategy:winningStrategies){
    if(winningStrategy.checkWinner(board,move)){
        return true;
    }
}
return  false;
}
public  void makeMove() {
    Player currentMovePlayer = players.get(nextMovePlayerIndex);
    System.out.println("It is " + currentMovePlayer.getName() + "'s turn.Plesae make your move");

    Move move = currentMovePlayer.makeMove(board);//make move cann takek me toa bot of a oplayer
    System.out.println(currentMovePlayer.getName() + " has made a move at row:" + move.getCell().getRow() + " and col:" + move.getCell().getCol());
    //validation

    if (!validateMove(move)) {
        System.out.println("Invalid move please try again");
        return;
    }
    //after that update the board
    int row = move.getCell().getRow();
    int col = move.getCell().getCol();

    Cell cellToChange = board.getBoard().get(row).get(col);//changing the state
    cellToChange.setCellState(CellState.FILLED);
    cellToChange.setPlayer(currentMovePlayer);

    Move finalMove = new Move(cellToChange, currentMovePlayer);
    moves.add(finalMove);  // we are adding to undo later

    nextMovePlayerIndex += 1;//changing the index
    nextMovePlayerIndex %= players.size();


    if (checkWinner(board, finalMove)) {
        gameState = GameState.WINNER;
        winner = currentMovePlayer;
    } else if (moves.size() == board.getSize() * board.getSize()) {
        gameState = GameState.DRAW;
    }
}
    public void undo(){
        if(moves.size()==0){
            System.out.println("No move to undo");
            return;
        }
        Move lastMove=moves.get(moves.size()-1);
        moves.remove(lastMove);

        Cell cell=lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);

        for(WinningStrategy winningStrategy:winningStrategies){
            winningStrategy.handleUndo(board,lastMove);
        }
        nextMovePlayerIndex-=1;
            nextMovePlayerIndex=(nextMovePlayerIndex+players.size())%players.size();
    }
}


