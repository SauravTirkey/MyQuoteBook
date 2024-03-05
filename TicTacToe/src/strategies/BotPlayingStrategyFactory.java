package strategies;

import modles.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficulty(BotDifficultyLevel botDifficultyLevel){
         return new EasyBotPlayingStrategy();
    }
}
