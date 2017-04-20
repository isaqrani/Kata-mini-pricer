package org.mini.pricer;

import org.junit.Before;
import org.junit.Test;

import org.mini.pricer.volatility.DeterministicVolatilityStrategy;
import org.mini.pricer.volatility.VolatilityStrategy;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PricerTest {

    private Pricer pricer;
    private Double price;
    private Double volatility;

    @Before
    public void initialise(){
        VolatilityStrategy strategy = new DeterministicVolatilityStrategy();
        pricer = new Pricer(strategy);
        price = 100d;
        volatility = 1d;
    }

    @Test
    public void should_not_change_the_price_for_the_same_day(){
        LocalDate startDate = LocalDate.of(2017,3,14);

        Double calculatedPrice = pricer.priceAt(startDate, startDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(price);
    }
}
