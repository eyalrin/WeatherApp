package com.here.tests;

import com.here.provider.AbstractProvider;
import com.here.provider.OpenWeatherMap;
import org.junit.Test;

public class functionality {

    // These are not really tests (since we don't assert the result) but more of a examples/demonstrations

    @Test
    public void testOpenWeatherMapGood() {
        String[] cities = new String[] {"London", "Jerusalem", "Athens", "Madrid", "TelAviv"};

        for (String city : cities) {
            AbstractProvider weatherProvider = new OpenWeatherMap();
            weatherProvider.getWeather(city);
        }
    }

    @Test
    public void testOpenWeatherMapCityNotExists() {
        String[] cities = new String[] {"ewklfjmoew"};

        for (String city : cities) {
            AbstractProvider weatherProvider = new OpenWeatherMap();
            weatherProvider.getWeather(city);
        }
    }

    @Test
    public void testOpenWeatherMapBadChar() {
        String[] cities = new String[] {"גכגלךכךל", "Rio De Janeiro"};

        for (String city : cities) {
            AbstractProvider weatherProvider = new OpenWeatherMap();
            weatherProvider.getWeather(city);
        }
    }
}
