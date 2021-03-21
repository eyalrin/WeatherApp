package com.here.provider;

import com.here.datamodel.CityInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OpenWeatherMap extends AbstractProvider {
    private static String APIKey = "49ba34bad15f953b74c107e42bfa85f8";
    private static String URL = "http://api.OpenWeatherMap.org/data/2.5/weather?q=%s&units=metric&type=accurate&APPID=%s";

    @Override
    public String getApiUrl(String cityName) {
        return String.format(URL, cityName, APIKey);
    }

    @Override
    public CityInfo[] getCityInfoFromResponse(String json) {
        // Notes
        // 1. For simplicity (and only for the exercise) I query a JSONObject instead of serializing it to an object using GSON/Jackson
        // 2. This provider doesn't support multiple cities (with the same name of course) so we always return array of size 1

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        CityInfo cityInfo = null;
        try {
            jsonObject = (JSONObject) parser.parse(json);
            cityInfo = new CityInfo(jsonObject.get("name").toString(), ((JSONObject)jsonObject.get("sys")).get("country").toString(), ((JSONObject)jsonObject.get("main")).get("temp").toString());
        } catch (ParseException e) {
            System.out.println("Response from provider is missing needed data");
        }
        return new CityInfo[] {cityInfo};
    }
}
