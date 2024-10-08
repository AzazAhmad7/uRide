package com.project.uRide.strategies;

import com.project.uRide.strategies.impl.DefaultRideFareCalculationStrategy;
import com.project.uRide.strategies.impl.HighestRatedDriverMatchingStrategy;
import com.project.uRide.strategies.impl.NearestDriverMatchingStrategy;
import com.project.uRide.strategies.impl.SurgePricingRideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final HighestRatedDriverMatchingStrategy highestRatedDriverMatchingStrategy;
    private final NearestDriverMatchingStrategy nearestDriverMatchingStrategy;
    private final DefaultRideFareCalculationStrategy defaultRideFareCalculationStrategy;
    private final SurgePricingRideFareCalculationStrategy surgePricingRideFareCalculationStrategy;

    public DriverMatchingStrategy getDriverMatchingStrategy(double riderRating) {
        if(riderRating >=4.8){
            return highestRatedDriverMatchingStrategy;
        }else{
            return nearestDriverMatchingStrategy;
        }
    }

    public RideFareCalculationStrategy getRideFareCalculationStrategy() {
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(22,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeEndTime) && currentTime.isBefore(surgeStartTime);
        if(isSurgeTime){
            return surgePricingRideFareCalculationStrategy;
        }else{
            return defaultRideFareCalculationStrategy;
        }
    }

}
