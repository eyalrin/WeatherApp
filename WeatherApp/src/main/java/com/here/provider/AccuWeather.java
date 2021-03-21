package com.here.provider;

import com.here.datamodel.CityInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AccuWeather extends AbstractProvider {
    private static String APIKey = "A4MEZYmsBMdJ7fjmCeOweylXLqLq7koe";
    private static String URL = "http://apidev.accuweather.com/locations/v1/cities/search.json?q=%s&apikey=%s&language=en-us";

    @Override
    public String getApiUrl(String cityName) {
        return String.format(URL, cityName, APIKey);
    }

    @Override
    public CityInfo[] getCityInfoFromResponse(String json) {
        throw new NotImplementedException();
    }
}
