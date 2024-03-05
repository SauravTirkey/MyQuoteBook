package strategies;

import modles.Board;
import modles.Move;
import modles.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{

    HashMap<Integer,Map<Symbol,Integer>> countMap=new HashMap<Integer, Map<Symbol, Integer>>();
    @Override
    public boolean checkWinner(Board board, Move move){
        int col=move.getCell().getCol();
        Symbol symbol=move.getCell().getPlayer().getSymbol();

        if(!countMap.containsKey(col)){
            countMap.put(col,new HashMap<>());
        }

        Map<Symbol,Integer> colMap=countMap.get(col);

        colMap.put(symbol,colMap.getOrDefault(symbol,0)+1);

        if(colMap.get(symbol)==board.getSize()){
            return true;
        }
    return false;

    }
@Override
public void handleUndo(Board board,Move move){
        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getSymbol();

        Map<Symbol,Integer> colMap=countMap.get(col);
        colMap.put(symbol,colMap.get(symbol)-1);
 }

}
