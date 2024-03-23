package strategies;

import modles.SpotAssignmentStrategyType;

public class SpotAssignmentStrategyFactory {

    public static SpotAssignmentStrategy getSpotForType(
            SpotAssignmentStrategyType spotAssignmentStrategyType){
        return new RandomSpotAssignmentStrategy();
    }
}
