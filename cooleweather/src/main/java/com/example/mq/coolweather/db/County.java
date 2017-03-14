package com.example.mq.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/14.
 */

public class County extends DataSupport {
    private int id;
    private String county_name;
    private String weather_id;
    private int city_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
