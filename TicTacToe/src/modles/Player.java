package modles;

import java.util.Scanner;

public class Player {
    private  Long id;
    private  String name;
    private Symbol symbol;//can also be a char so we need builder
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long id,String name,Symbol symbol,PlayerType type){
        this.id=id;
        this.playerType=type;
        this.name=name;
        this.symbol=symbol;
        scanner=new Scanner(System.in);
    }

    public Long  getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public  Move makeMove(Board board){
    System.out.println("Please tell the row number where you want to move");
    int row=scanner.nextInt();

        System.out.println("Please tell the col number where you want to move");
        int col=scanner.nextInt();

        return new Move(new Cell(row,col),this);
    }
}
