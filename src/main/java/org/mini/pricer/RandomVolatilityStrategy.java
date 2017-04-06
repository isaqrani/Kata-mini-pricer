package org.mini.pricer;

public class RandomVolatilityStrategy implements VolatilityStrategy {

    @Override
    public Double randomise(final Double volatility) {
        double random = Math.random();
        double trend = 0;
        if(random < 0.33) {
            trend  = -1d;
        }
        if(random > 0.66) {
            trend = 1d;
        }
        return volatility * trend;
    }

}
