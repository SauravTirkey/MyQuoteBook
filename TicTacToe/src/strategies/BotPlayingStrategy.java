package strategies;

import modles.Board;
import modles.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
