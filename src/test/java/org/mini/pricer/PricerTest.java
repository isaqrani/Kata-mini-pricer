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

    @Test
    public void should_change_the_price_for_other_day(){
        LocalDate startDate = LocalDate.of(2017,3,14);
        LocalDate targetedDate = LocalDate.of(2017,3,17);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(99.99);
    }

    @Test
    public void should_skip_weekend(){
        LocalDate startDate = LocalDate.of(2017,3,17);
        LocalDate targetedDate = LocalDate.of(2017,3,22);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(99.99);
    }

    @Test
    public void should_skip_weekend_when_start_day_is_saturday(){
        LocalDate startDate = LocalDate.of(2017,3,18);
        LocalDate targetedDate = LocalDate.of(2017,3,22);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(99.99);
    }

    @Test
    public void should_skip_holidays(){
        LocalDate startDate = LocalDate.of(2017,5,1);
        LocalDate targetedDate = LocalDate.of(2017,5,5);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(99.99);
    }

    @Test
    public void should_skip_holidays_and_weekends(){
        LocalDate startDate = LocalDate.of(2017,5,5);
        LocalDate targetedDate = LocalDate.of(2017,5,11);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(99.99);
    }
    
    @Test
    public void should_skip_holidays_and_weekends_and_holidayes_again(){
        LocalDate startDate = LocalDate.of(2017,6,14);
        LocalDate targetedDate = LocalDate.of(2017,6,21);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(99.99);
    }


}
