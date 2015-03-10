package com.mycompany.weatherguitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class MarsWeather
{
    private class DataWeatherData
    {
      public String terrestrial_date = "";
      public int  sol = 0;
      public Double ls = 0.0;
      public Double min_temp = 0.0;
      public Double min_temp_fahrenheit = 0.0;
      public Double max_temp = 0.0;
      public Double max_temp_fahrenheit = 0.0;
      public Double pressure = 0.0;
      public String pressure_string = "";
      public Double abs_humidity = 0.0;
      public Double wind_speed = 0.0;
      public int wind_direction = 0;
      public String atmo_opacity = "";
      public String season = "";
      public String sunrise = "";
      public String sunset = "";

        public DataWeatherData(){ }
    }
    
    private String openWeatherURL = "http://marsweather.ingenology.com/v1/latest/?format=json";
    private String DataJson;
    private DataWeatherData dwd = new DataWeatherData();
    
    public MarsWeather()
    {
        
        try {
            URL url = new URL(openWeatherURL);
            URLConnection uc = url.openConnection();
            InputStreamReader is = new InputStreamReader(uc.getInputStream(), "UTF-8");
            BufferedReader input = new BufferedReader(is);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = input.readLine()) != null) {
                sb.append(line);
        }
        this.DataJson = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(MarsWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        fillDataWeatherData();
    }
    
    public void fillDataWeatherData()
    {
        JSONObject jo = new JSONObject(DataJson);       
        dwd.terrestrial_date = jo.getJSONObject("report").getString("terrestrial_date");
        dwd.min_temp = jo.getJSONObject("report").getDouble("min_temp");
        dwd.min_temp_fahrenheit = jo.getJSONObject("report").getDouble("min_temp_fahrenheit");
        dwd.max_temp = jo.getJSONObject("report").getDouble("max_temp");
        dwd.max_temp_fahrenheit = jo.getJSONObject("report").getDouble("max_temp_fahrenheit");
        dwd.pressure = jo.getJSONObject("report").getDouble("pressure");
        dwd.pressure_string = jo.getJSONObject("report").getString("pressure_string");
        dwd.atmo_opacity = jo.getJSONObject("report").getString("atmo_opacity");
        dwd.season = jo.getJSONObject("report").getString("season");
        dwd.sunrise = jo.getJSONObject("report").getString("sunrise");
        dwd.sunset = jo.getJSONObject("report").getString("sunset");
    }
    
    public double getMaxTemp()
    {
        return dwd.max_temp;
    }
    
    public double getMinTemp()
    {
        return dwd.min_temp;
    }
    
    public double getMaxTempFahrenheit()
    {
        return dwd.max_temp_fahrenheit;
    }
    
    public double getMinTempFahrenheit()
    {
        return dwd.min_temp_fahrenheit;
    }
    
    public String getSkyCondition()
    {
        return dwd.atmo_opacity;
    }
    
    public String getSunrise()
    {
        return dwd.sunrise;
    }
    
    public String getSunset()
    {
        return dwd.sunset;
    }
    
    public double getWindDirection()
    {
        return dwd.wind_direction; //not sure
    }
    
    public double getWindSpeed()
    {
        return dwd.wind_speed;
    }
    
    public double getPressure()
    {
        return dwd.pressure;
    }
    
    public String getPressureString()
    {
        return dwd.pressure_string;
    }
    
    public  Double getHumidity()
    {
        return dwd.abs_humidity;
    }
    
    public String getSeason()
    {
        return dwd.season;
    }
    
    public String getDate()
    {
        return dwd.terrestrial_date;
    }
}