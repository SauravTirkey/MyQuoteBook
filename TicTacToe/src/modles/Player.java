package modles;

public class Player {
    private  Long id;
    private  String name;
    private Symbol symbol;//can also be a char so we need builder
    private PlayerType playerType;

    public Player(Long id,String name,Symbol symbol,PlayerType type){
        this.id=id;
        this.playerType=type;
        this.name=name;
        this.symbol=symbol;
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
}
