package com.example.hotel.controller;

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/hotelinfo")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/allhotel", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> getAllUser() {
        return hotelService.getAllUserInfo();
    }

    @PostMapping(value = "/addhotel")
    public void addhotel(@RequestBody Hotel hotel){
        hotelService.hotelsave(hotel);
    }

    @CacheEvict(value = "hotels",key = "#id")
    @DeleteMapping(value = "/delete{hotelid}")
    public void deletehotel(@PathVariable("hotelid") String id ){
        hotelService.deletehotel(id);
    }

    @CachePut(value = "hotels",key = "#id")
    @PutMapping(value = "update{hotelid}")
    public Hotel updatehotelstatus(@PathVariable("hotelid") String id,@RequestParam(required = false) String status){
        return hotelService.updateuser(id,status);
    }

    @Cacheable(value = "hotels",key = "#id")
    @GetMapping(value = "/hotelid/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelById(@PathVariable("id") String id)
    {
        return hotelService.gethotelById(id);
    }

    @GetMapping(value = "/username/{countryid}/{cityid}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> getHotelByName(@PathVariable("countryid") String country,@PathVariable("cityid") String city)
    {
        return hotelService.getAllUserByNameInfo(country, city);
    }
}

//    @Caching( put = {
//            @CachePut(key = "#code"),
//            @CachePut(key = "'TMP_'.concat(#code)")