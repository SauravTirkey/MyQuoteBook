package modles;

import strategies.BotPlayingStrategy;

public class Bot extends Player{
   private BotDifficultyLevel botDifficultyLevel;
   private BotPlayingStrategy botPlayingStrategy;

   public Bot(Long id,String name,Symbol symbol,BotDifficultyLevel botDifficultyLevel){
         super(id,name,symbol,PlayerType.BOT);
         this.botDifficultyLevel=botDifficultyLevel;
       //TODO // this.botPlayingStrategy=  add a factrory;
   }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
}