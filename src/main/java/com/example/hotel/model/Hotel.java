package com.example.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "hotelinfo")
public class Hotel implements Serializable {
    private String country_id,city_id,hotel_type,hotel_status;
    @Id
    private String hotel_id;

    public Hotel() {
    }

    public Hotel(String country_id, String city_id, String hotel_type, String hotel_status, String hotel_id) {
        this.country_id = country_id;
        this.city_id = city_id;
        this.hotel_type = hotel_type;
        this.hotel_status = hotel_status;
        this.hotel_id = hotel_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getHotel_type() {
        return hotel_type;
    }

    public void setHotel_type(String hotel_type) {
        this.hotel_type = hotel_type;
    }

    public String getHotel_status() {
        return hotel_status;
    }

    public void setHotel_status(String hotel_status) {
        this.hotel_status = hotel_status;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }
}
