package com.mycompany.weatherguitest; 
import java.util.Vector;

public class ShortTermWeather extends LongTermWeather
{
    public ShortTermWeather()
    {
        super();
    }
    
    public ShortTermWeather(String city)
    {
        super(city);
    }
    
    public ShortTermWeather(String city, String country)
    {
        super(city, country);
    }
    
    public Vector<Double> getTemprature() {
        Vector<Double> v = new Vector<>();
        for (int i = 0; i < 8; i++) {
            
                 v.add(dwd[i].mainTemprature);
        }
        return v;
    }
     
    public Vector<String> getSkyCondition() {
        Vector<String> s = new Vector<String>();
        for (int i = 0; i < 8; i++) {
            
                 s.add(dwd[i].weatherDescription);
        }
        return s;
    }
    
    public Vector<String> getSkyIcon()
    {
        Vector<String> s = new Vector<String>();
        for (int i = 0; i < 8; i++) {
            
                 s.add(dwd[i].weatherIcon);
        }
        return s;
    }
    
    
}