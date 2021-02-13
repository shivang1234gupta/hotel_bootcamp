package com.example.hotel.repository;

import com.example.hotel.model.Hotel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelCrud extends ElasticsearchRepository<Hotel,String> {
}
