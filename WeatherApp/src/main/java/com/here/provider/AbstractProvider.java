package com.here.provider;

import com.here.datamodel.CityInfo;
import com.here.error.CityNotFoundException;
import com.here.error.IllegalCharException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractProvider {
    private DefaultHttpClient httpClient;

    public AbstractProvider() {
        httpClient = new DefaultHttpClient();
    }

    private DefaultHttpClient getHttpClient() {
        return httpClient;
    }

    private boolean validateCityNameChars(String cityName) {
        String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cityName);
        return matcher.matches();
    }

    public abstract String getApiUrl(String cityName);

    public String getResponseFromApi(String cityName) {
        String output = "";
        try {
            if (!validateCityNameChars(cityName)) {
                throw new IllegalCharException();
            }

            HttpGet getRequest = new HttpGet(getApiUrl(cityName));

            getRequest.addHeader("accept", "application/json");

            HttpResponse response = null;

            response = getHttpClient().execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new CityNotFoundException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));


            output = br.readLine();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                output.concat(temp);
            }

            httpClient.getConnectionManager().shutdown();
        } catch (IOException e) {
            System.out.println("An error occurred reading the data from the provider");
        } catch (CityNotFoundException e) {
            System.out.println("Unknown city name");
        } catch (IllegalCharException e) {
            System.out.println("Invalid chars were entered - Please use only english letters with no spaces");
        }

        return output;
    }

    public abstract CityInfo[] getCityInfoFromResponse(String json);

    public void print(CityInfo[] cityInfos) {
        for (CityInfo cityInfo : cityInfos) {
            System.out.println(cityInfo.toString());
        }
    }

    public void getWeather(String cityName) {
        String responseFromApi = getResponseFromApi(cityName);
        if (!"".equals(responseFromApi)) {
            CityInfo[] citiesInfoFromResponse = getCityInfoFromResponse(responseFromApi);
            if (citiesInfoFromResponse != null && citiesInfoFromResponse.length > 0) {
                print(citiesInfoFromResponse);
            }
        }
    }
}
