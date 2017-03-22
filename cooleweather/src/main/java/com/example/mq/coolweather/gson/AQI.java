package com.example.mq.coolweather.gson;

/**
 * Created by Administrator on 2017/3/20.
 */

public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
