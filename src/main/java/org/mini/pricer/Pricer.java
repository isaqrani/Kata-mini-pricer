package org.mini.pricer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Pricer {
    private List<LocalDate> holidays = Arrays.asList(LocalDate.of(2017,5,1), LocalDate.of(2017,5,8));
    private VolatilityStrategy strategy;

    public Pricer(final VolatilityStrategy volatilityStrategy) {
        this.strategy = volatilityStrategy;
    }

    public Double priceAt(final LocalDate startDate,final LocalDate targetedDate, Double price, Double volatility) {

        if(startDate.toEpochDay() == targetedDate.toEpochDay()){
            return price;
        }else{
            Double calculatedPrice =  priceAt(nextDay(startDate), targetedDate, price, volatility);
            return calculatedPrice * (1 + strategy.randomise(volatility)/100);
        }
    }

    private LocalDate nextDay(LocalDate date) {
        return skipWeekends(skipHolidays(date));
    }

    private LocalDate skipWeekends(LocalDate date) {
        if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
            return date.plusDays(3);
        }else if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
            return date.plusDays(2);
        }else{
            return date.plusDays(1);
        }
    }

    private LocalDate skipHolidays(LocalDate date){
        if(holidays.contains(date)){
            return date.plusDays(1);
        }
        return date;
    }
}
