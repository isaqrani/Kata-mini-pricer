package org.mini.pricer;

public class DeterministicVolatilityStrategy implements VolatilityStrategy{

    private double[] values = { -1, 0, 1 };
    private int current = 0;

    @Override
    public Double randomise(Double volatility) {
        return values[current==3 ? 0 : current++];
    }
}
