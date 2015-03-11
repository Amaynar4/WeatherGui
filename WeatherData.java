package com.mycompany.weatherguitest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class WeatherData 
{
    private class DataWeatherData
    {
        public double coordLongtude = 0;
        public double coordLatitude = 0;
        public String sysCountry = "";
        public double sysSunrise = 0;
        public double sysSunset = 0;
        public int weatherId = 0;
        public String weatherMain = "";
        public String weatherDescription = "";
        public String weatherIcon = "";
        public String base;
        public double mainTemprature = 0;
        public int mainHumidity = 0;
        public double mainPressure = 0;
        public double mainTempMin = 0;
        public double mainTempMax = 0;
        public double windSpeed = 0;
        public double windDeg = 0;
        public String nameCity = "";

        public DataWeatherData(){ }
    }
    
    private String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String DataJson;
    URLAccess access;
    private DataWeatherData dwd = new DataWeatherData();
    
    public WeatherData() 
    {       
        try {
            access  = new URLAccess(openWeatherURL);
        } catch (IOException ex) {
            Logger.getLogger(WeatherData.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataJson = access.getDataJson();
        fillDataWeatherData();
    }
    public WeatherData(String city) 
    {
        try {
            access  = new URLAccess(openWeatherURL,city);
        } catch (IOException ex) {
            Logger.getLogger(WeatherData.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataJson = access.getDataJson();
        fillDataWeatherData();
    } 
    public WeatherData(String city, String country) 
    {
        try {
            access  = new URLAccess(openWeatherURL,city, country);
        } catch (IOException ex) {
            Logger.getLogger(WeatherData.class.getName()).log(Level.SEVERE, null, ex);
        }
        DataJson = access.getDataJson();
        fillDataWeatherData();
    } 
    
    public void fillDataWeatherData()
    {
        JSONObject jo = new JSONObject(DataJson);       
        dwd.coordLongtude = jo.getJSONObject("coord").getDouble("lon");
        dwd.coordLatitude = jo.getJSONObject("coord").getDouble("lat");
        dwd.sysCountry = jo.getJSONObject("sys").getString("country");
        dwd.sysSunrise = jo.getJSONObject("sys").getDouble("sunrise");
        dwd.sysSunset = jo.getJSONObject("sys").getDouble("sunset");
        dwd.weatherId = jo.getJSONArray("weather").getJSONObject(0).getInt("id");
        dwd.weatherMain = jo.getJSONArray("weather").getJSONObject(0).getString("main");
        dwd.weatherDescription = jo.getJSONArray("weather").getJSONObject(0).getString("description");
        dwd.weatherIcon = jo.getJSONArray("weather").getJSONObject(0).getString("icon");
        dwd.base = jo.getString("base");
        dwd.mainTemprature = jo.getJSONObject("main").getDouble("temp");
        dwd.mainHumidity = jo.getJSONObject("main").getInt("humidity");
        dwd.mainPressure = jo.getJSONObject("main").getDouble("pressure");
        dwd.mainTempMin = jo.getJSONObject("main").getDouble("temp_min");
        dwd.mainTempMax = jo.getJSONObject("main").getDouble("temp_max");
        dwd.windSpeed = jo.getJSONObject("wind").getDouble("speed");
        dwd.windDeg = jo.getJSONObject("wind").getDouble("deg");
        dwd.nameCity = jo.getString("name");        
    }    
    
    public double getTemprature()
    {
        return dwd.mainTemprature;
    }
    
    public double getMinimumTemp()
    {
        return dwd.mainTempMin;
    }
    
    public double getMaximumTemp()
    {
        return dwd.mainTempMax;
    }
    
    public String getSkyCondition()
    {
        return dwd.weatherDescription;
    }
    
    public String getSkyIcon()
    {
        return dwd.weatherIcon;
    }
    
    public Double getSunrise()
    {
        return dwd.sysSunrise;
    }
    
    public Double getSunset()
    {
        return dwd.sysSunset;
    }
    
    public double getWindDirection()
    {
        return dwd.windDeg; //not sure
    }
    
    public double getWindSpeed()
    {
        return dwd.windSpeed;
    }
    
    public double getAirPressure()
    {
        return dwd.mainPressure;
    }
    
    public int getHumidity()
    {
        return dwd.mainHumidity;
    }
}