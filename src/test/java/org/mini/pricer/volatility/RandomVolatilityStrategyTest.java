package org.mini.pricer.volatility;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RandomVolatilityStrategy.class)
public class RandomVolatilityStrategyTest {

    private RandomVolatilityStrategy randomVolatilityStrategy;
    private Double volatility;

    @Before
    public void setUp() {
        randomVolatilityStrategy = new RandomVolatilityStrategy();
        volatility = 1d;
    }

    @Test
    public void should_generate_a_random_volatility_equal_to_minus_one() {
        mockRandom(0.3);
        Double actualRandomVolatility = randomVolatilityStrategy.randomise(volatility);
        assertThat(actualRandomVolatility).isEqualTo(-1);
    }

    @Test
    public void should_generate_a_random_volatility_equal_to_zero() {
        mockRandom(0.5);
        Double actualRandomVolatility = randomVolatilityStrategy.randomise(volatility);
        assertThat(actualRandomVolatility).isEqualTo(0);
    }

    @Test
    public void should_generate_a_random_volatility_equal_to_one() {
        mockRandom(0.7);
        Double actualRandomVolatility = randomVolatilityStrategy.randomise(volatility);
        assertThat(actualRandomVolatility).isEqualTo(1);
    }

    private void mockRandom(final Double forcedValue) {
        PowerMock.mockStatic(Math.class);
        EasyMock.expect(Math.random()).andReturn(forcedValue).once();
        PowerMock.replay(Math.class);
    }
}
