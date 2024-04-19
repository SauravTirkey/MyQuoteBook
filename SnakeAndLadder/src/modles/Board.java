package modles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
//    private int size;
//    private List<List<Cell>> board;
//
//    public Board(int dimension,int numberOfSnakes,int numberOFLadders){
//        this.size=dimension;
//
//        this.board=new ArrayList<>();
//
//        for(int i=0;i<dimension;i++){
//            board.add(new ArrayList<>());
//            for(int j=0;j<dimension;j++){
//            board.get(i).add(new Cell);
//            }
//        }
//    }

    Cell[][] cells;
    Board(int boardSize,int numberOfSnakes,int numberOfLadders){
        initializeCells(boardSize);
        addSnakesLadders(cells,numberOfSnakes,numberOfLadders)
    }
    private void initializeCells(int boardSize){
        cells=new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                Cell cellObj=new Cell();
                cells[i][j]=cellObj;
            }
        }
    }
    private void addSnakesLadders(Cell[][] cells,int numberOfSnakes,int numberOfLadders){
        while(numberOfSnakes>0){
            int snakeHead= ThreadLocalRandom.current().nextInt(1,cells.length+cells.length-1);
            int snakeTail=ThreadLocalRandom.current().nextInt(1,cells.length+cells.length-1);
        if(snakeTail>=snakeHead){
            continue;
        }
        Jump snakeObj=new Jump();
        snakeObj.setStart(snakeHead);//snakeObj.start=snakeHead;
        snakeObj.setEnd(snakeTail);

        Cell cell =getCell(snakeHead);
        cell.jump=snakeObj;
        numberOfSnakes--;
        }
        while(numberOfLadders>0){
            int ladderStart= ThreadLocalRandom.current().nextInt(1,cells.length+cells.length-1);
            int ladderEnd=ThreadLocalRandom.current().nextInt(1,cells.length+cells.length-1);
            if(ladderStart>=ladderEnd){
                continue;
            }
            Jump ladderObj=new Jump();
            ladderObj.setStart(ladderStart);//snakeObj.start=snakeHead;
            ladderObj.setEnd(ladderEnd);

            Cell cell =getCell(snakeHead);
            cell.jump=snakeObj;
            numberOfLadders--;
        }
    }

}
