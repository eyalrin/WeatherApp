package com.here.application;

import com.here.provider.AbstractProvider;
import com.here.provider.OpenWeatherMap;

import java.util.Scanner;

public class WeatherApplication {

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nEnter a city name (english only, no spaces) or type <Exit> to exit: ");

            Scanner scanner = new Scanner(System.in);
            String cityName = scanner.nextLine();

            if (cityName.equalsIgnoreCase("Exit"))
                break;

            AbstractProvider weatherProvider = new OpenWeatherMap();
            weatherProvider.getWeather(cityName);
        }
    }
}