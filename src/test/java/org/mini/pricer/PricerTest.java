package org.mini.pricer;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PricerTest {

    private Pricer pricer;

    @Before
    public void initialise(){
        pricer = new Pricer();
    }

    @Test
    public void should_not_change_the_price_for_the_same_day(){
        Double price = 100d;
        Double volatility = 1d;
        LocalDate startDate = LocalDate.of(2017,3,14);
        LocalDate targetedDate = startDate;

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(price);
    }

    @Test
    public void should_change_the_price_for_other_day(){
        Double price = 100d;
        Double volatility = 1d;
        LocalDate startDate = LocalDate.of(2017,3,14);
        LocalDate targetedDate = LocalDate.of(2017,3,17);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(103.0301);
    }

    @Test
    public void should_skip_weekend(){
        Double price = 100d;
        Double volatility = 1d;
        LocalDate startDate = LocalDate.of(2017,3,17);
        LocalDate targetedDate = LocalDate.of(2017,3,22);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(103.0301);
    }

    @Test
    public void should_skip_weekend_when_start_day_is_saturday(){
        Double price = 100d;
        Double volatility = 1d;
        LocalDate startDate = LocalDate.of(2017,3,18);
        LocalDate targetedDate = LocalDate.of(2017,3,22);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(103.0301);
    }

    @Test
    public void should_skip_holidays(){
        Double price = 100d;
        Double volatility = 1d;
        LocalDate startDate = LocalDate.of(2017,5,1);
        LocalDate targetedDate = LocalDate.of(2017,5,5);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(103.0301);
    }

    @Test
    public void should_skip_holidays_and_weekends(){
        Double price = 100d;
        Double volatility = 1d;
        LocalDate startDate = LocalDate.of(2017,5,5);
        LocalDate targetedDate = LocalDate.of(2017,5,11);

        Double calculatedPrice = pricer.priceAt(startDate, targetedDate, price, volatility);

        assertThat(calculatedPrice).isEqualTo(103.0301);
    }


}
