package strategies;

import modles.Board;
import modles.Move;
import modles.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements  WinningStrategy{

    private HashMap<Integer, Map<Symbol,Integer>> countMap=new HashMap<Integer, Map<Symbol, Integer>>();
    @Override
    public boolean checkWinner(Board board, Move move){
        int row=move.getCell().getRow();
        Symbol symbol=move.getCell().getPlayer().getSymbol();

        if(!countMap.containsKey(row)){
            countMap.put(row,new HashMap<>());
        }

        Map<Symbol,Integer> rowMap=countMap.get(row);

        rowMap.put(symbol,rowMap.getOrDefault(symbol,0)+1);

        if(rowMap.get(symbol)==board.getSize()){
            return true;
        }

        return  false;
    }
    @Override
    public void handleUndo(Board board,Move move){
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();
        Map<Symbol,Integer> rowMap=countMap.get(row);
        rowMap.put(symbol,rowMap.get(symbol)-1);
    }


}
