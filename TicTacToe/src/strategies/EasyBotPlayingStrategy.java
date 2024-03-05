package strategies;

import modles.Board;
import modles.Cell;
import modles.CellState;
import modles.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board){
        for(List<Cell> row:board.getBoard()){
            for(Cell cell:row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell,null);
                }
            }
        }
        return null;
    }
}
