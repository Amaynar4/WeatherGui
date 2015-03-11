package com.mycompany.weatherguitest;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class LongTermWeather {

    protected class DataWeatherData {

        public String nameCity = "";
        public double coordLongtude = 0;
        public double coordLatitude = 0;
        public String sysCountry = "";

        public int weatherId = 0;
        public String weatherMain = "";
        public String weatherDescription = "";
        public String weatherIcon = "";
        public Double mainTemprature = 0.0;
        public int mainHumidity = 0;
        public double mainPressure = 0;
        public double mainTempMin = 0;
        public double mainTempMax = 0;
        public double windSpeed = 0;
        public double windDeg = 0;
        public String dateText = "";
        public long date = 0L;

        public DataWeatherData() {
        }
    }

    private String openWeatherURL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private String DataJson;
    URLAccess access;
    protected DataWeatherData[] dwd;

    public LongTermWeather() 
    {        
        try {
            access = new URLAccess(openWeatherURL);
            DataJson = access.getDataJson();
            
        } catch (IOException ex) {
            Logger.getLogger(LongTermWeather.class.getName()).log(Level.SEVERE, null, ex);
        } 
        fillDataWeatherData();
    }
    
    public LongTermWeather(String city)
    {
        try {
            access = new URLAccess(openWeatherURL, city);
            DataJson = access.getDataJson();
        } catch (IOException ex) {
            Logger.getLogger(LongTermWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        fillDataWeatherData();
    } 
    public LongTermWeather(String city, String country) 
    {
        try {
            access = new URLAccess(openWeatherURL, city, country);
            DataJson = access.getDataJson();
        } catch (IOException ex) {
            Logger.getLogger(LongTermWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        fillDataWeatherData();
    } 

   
    public void fillDataWeatherData() {
        JSONObject jo = new JSONObject(DataJson);
        JSONArray arr = jo.getJSONArray("list");
        dwd = new DataWeatherData[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            JSONObject tempobj = arr.getJSONObject(i);
            dwd[i] = new DataWeatherData();
            dwd[i].nameCity = jo.getJSONObject("city").getString("name");
            dwd[i].coordLongtude = jo.getJSONObject("city").getJSONObject("coord").getDouble("lon");
            dwd[i].coordLatitude = jo.getJSONObject("city").getJSONObject("coord").getDouble("lat");
            dwd[i].sysCountry = jo.getJSONObject("city").getString("country");

            dwd[i].date = tempobj.getLong("dt");
            dwd[i].dateText = tempobj.getString("dt_txt");
            dwd[i].weatherId = tempobj.getJSONArray("weather").getJSONObject(0).getInt("id");
            dwd[i].weatherMain = tempobj.getJSONArray("weather").getJSONObject(0).getString("main");
            dwd[i].weatherDescription = tempobj.getJSONArray("weather").getJSONObject(0).getString("description");
            dwd[i].weatherIcon = tempobj.getJSONArray("weather").getJSONObject(0).getString("icon");
            dwd[i].mainTemprature = tempobj.getJSONObject("main").getDouble("temp");
            dwd[i].mainHumidity = tempobj.getJSONObject("main").getInt("humidity");
            dwd[i].mainPressure = tempobj.getJSONObject("main").getDouble("pressure");
            dwd[i].mainTempMin = tempobj.getJSONObject("main").getDouble("temp_min");
            dwd[i].mainTempMax = tempobj.getJSONObject("main").getDouble("temp_max");
            dwd[i].windSpeed = tempobj.getJSONObject("wind").getDouble("speed");
            dwd[i].windDeg = tempobj.getJSONObject("wind").getDouble("deg");
        }
    }

    public int sizeOfData() {
        return dwd.length;
    }

    public Vector<Double> getTemprature(String time) {
        Vector<Double> v = new Vector<>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 v.add(dwd[i].mainTemprature);
            }
        }
        return v;
    }
    public Vector<Double> getTemprature() 
    {
        return getTemprature("12:00");
    }

    public Vector<Double> getMinimumTemp(String time)
    {
        Vector<Double> v = new Vector<>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 v.add(dwd[i].mainTempMin);
            }
        }
        return v;
    }
    
    public Vector<Double> getMinimumTemp() 
    {
        return getMinimumTemp("12:00");
    }
    
    public Vector<Double> getMaximumTemp(String time)
    {
        Vector<Double> v = new Vector<>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 v.add(dwd[i].mainTempMax);
            }
        }
        return v;
    }
    
    public Vector<Double> getMaximumTemp() 
    {
        return getMaximumTemp("12:00");
    }
    
    public Vector<String> getSkyCondition(String time) {
        Vector<String> s = new Vector<String>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 s.add(dwd[i].weatherDescription);
            }
        }
        return s;
    }
    
    public Vector<String> getSkyCondition() 
    {
        return getSkyCondition("12:00");
    }
    
    public Vector<String> getSkyIcon(String time)
    {
        Vector<String> s = new Vector<String>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 s.add(dwd[i].weatherIcon);
            }
        }
        return s;
    }
    
    public Vector<String> getSkyIcon() 
    {
        return getSkyCondition("12:00");
    }
        
    public Vector<Double> getWindDirection(String time)
    {
        Vector<Double> v = new Vector<>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 v.add(dwd[i].windDeg); //not sure
            }
        }
        return v;
    }
    
    public Vector<Double> getWindDirection() 
    {
        return getWindDirection("12:00");
    }
    
    public Vector<Double> getWindSpeed(String time)
    {
        Vector<Double> v = new Vector<>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 v.add(dwd[i].windSpeed); //not sure
            }
        }
        return v;
    }
    
    public Vector<Double> getWindSpeed() 
    {
        return getWindSpeed("12:00");
    }
    
    public Vector<Double> getAirPressure(String time)
    {
        Vector<Double> v = new Vector<>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 v.add(dwd[i].mainPressure); //not sure
            }
        }
        return v;
    }
    
    public Vector<Double> getAirPressure() 
    {
        return getAirPressure("12:00");
    }
    
    public Vector<Integer> getHumidity(String time)
    {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < dwd.length; i++) {
            if(time.compareTo(dwd[i].dateText.substring(11, 16)) == 0)
            {
                 v.add(dwd[i].mainHumidity); //not sure
            }
        }
        return v;
    }
    
    public Vector<Integer> getHumidity() 
    {
        return getHumidity("12:00");
    }
}