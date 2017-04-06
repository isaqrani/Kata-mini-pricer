package org.mini.pricer;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomVolatilityStrategyTest {
    private RandomVolatilityStrategy randomVolatilityStrategy;
    private Double price;
    private Double volatility;

    @Before
    public void initialise(){
        RandomVolatilityStrategy randomVolatilityStrategy = new RandomVolatilityStrategy();
        volatility = 1d;
    }

    @Test
    public void should_generate_a_random(){
        Double actualRandomVolatility = randomVolatilityStrategy.randomise(volatility)
        List<Double> expectedVolatilities = Arrays.asList(-1d, 0d, 1d);
        assertThat(actualRandomVolatility).isIn(expectedVolatilities);
    }
 }
