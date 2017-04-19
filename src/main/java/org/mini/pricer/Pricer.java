package org.mini.pricer;

import org.mini.pricer.volatility.VolatilityStrategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Pricer {
    private List<LocalDate> holidays = Arrays.asList(LocalDate.of(2017,5,1), LocalDate.of(2017,5,8), LocalDate.of(2017,6,15), LocalDate.of(2017,6,19));
    
    private VolatilityStrategy strategy;

    public Pricer(final VolatilityStrategy volatilityStrategy) {
        this.strategy = volatilityStrategy;
    }

    public Double priceAt(LocalDate startDate,final LocalDate targetedDate, final Double price,final Double volatility) {

        startDate = adjustStartDate(startDate);
        if(startDate.toEpochDay() >= targetedDate.toEpochDay()){
            return price;
        }else{
            Double calculatedPrice =  priceAt(nextDay(startDate), targetedDate, price, volatility);
            return calculatedPrice * (1 + strategy.randomise(volatility)/100);
        }
    }

    private LocalDate nextDay(LocalDate date) {
        return isWorkingDay(date.plusDays(1))  ? date.plusDays(1) : nextDay(date.plusDays(1));
    }

    private boolean isWorkingDay(final LocalDate date) {        
        return !date.getDayOfWeek().equals(DayOfWeek.SATURDAY) && 
            !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) && 
            !holidays.contains(date);
    }

    private LocalDate adjustStartDate(LocalDate date){
        while (!isWorkingDay(date)){
            date = date.plusDays(1);
        }
        return date;
    }

}
