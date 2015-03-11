package com.mycompany.weatherguitest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class URLAccess
{
    private String cityByIP = "http://ip-api.com/json";
    private String DefaultCity = null;
    private String DefaultCountry = null;
    private String DataJson = null;
    
    public URLAccess(String openWeatherURL) throws MalformedURLException, IOException {

        URL url = new URL(openWeatherURL + getCurrentCity() + "," + getCurrentCountry());
        URLConnection uc = url.openConnection();
        InputStreamReader is = new InputStreamReader(uc.getInputStream(), "UTF-8");
        BufferedReader input = new BufferedReader(is);
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = input.readLine()) != null) {
            sb.append(line);
        }
        this.DataJson = sb.toString();
    }
    
    public URLAccess(String openWeatherURL, String city) throws MalformedURLException, IOException
    {
        URL url = new URL(openWeatherURL + city + "," + getCurrentCountry());
        URLConnection uc = url.openConnection();
        InputStreamReader is = new InputStreamReader(uc.getInputStream(), "UTF-8");
        BufferedReader input = new BufferedReader(is);
        StringBuilder sb = new StringBuilder();
        String line;
        
        while((line = input.readLine()) != null)
        {
            sb.append(line);
        }
        this.DataJson = sb.toString();
    } 
    public URLAccess(String openWeatherURL, String city, String country) throws MalformedURLException, IOException
    {
        URL url = new URL(openWeatherURL + city + "," + country);
        URLConnection uc = url.openConnection();
        InputStreamReader is = new InputStreamReader(uc.getInputStream(), "UTF-8");
        BufferedReader input = new BufferedReader(is);
        StringBuilder sb = new StringBuilder();
        String line;
        
        while((line = input.readLine()) != null)
        {
            sb.append(line);
        }
        this.DataJson = sb.toString();
    } 
    
   
    private void currentLocation() throws MalformedURLException, IOException 
    {
        URL url = new URL(cityByIP);
        URLConnection uc = url.openConnection();
        InputStreamReader is = new InputStreamReader(uc.getInputStream(), "UTF-8");
        BufferedReader input = new BufferedReader(is);
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = input.readLine()) != null) {
            sb.append(line);
        }
        line = sb.toString();
        JSONObject jo = new JSONObject(line);
        DefaultCity = jo.getString("city");
        DefaultCountry = jo.getString("country");
    }  

    public String getCurrentCity() {
        try {
            currentLocation();
        } catch (MalformedURLException ex) {
            System.out.println("URL Issue");
        } catch (IOException ex) {
            System.err.println("Connection issue");
        }
        return DefaultCity;
    }

    public String getCurrentCountry() {
        try {
            currentLocation();
        } catch (MalformedURLException ex) {
            System.out.println("URL Issue");
        } catch (IOException ex) {
            System.err.println("Connection issue");
        }
        return DefaultCountry;
    }
    
    public String getDataJson()
    {
        return this.DataJson;
    }
}