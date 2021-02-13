package com.example.hotel.repository;

import com.example.hotel.model.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HotelRepoImpl implements HotelRep{

    @Autowired
    private ObjectMapper objectMapper;
    RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
            new HttpHost("localhost", 9200, "http")));
    @Override
    public List<Hotel> findAllHotelDetailsFromElastic() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("hotelinfo");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        List<Hotel> list = new ArrayList<>();
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Hotel.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Hotel> findAllHotelByCountryAndCityDetailsFromElastic(String countryid,String cityid) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("hotelinfo");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("country_id.keyword",countryid)).
                                                            must(QueryBuilders.matchQuery("city_id.keyword",cityid)));
        searchRequest.source(searchSourceBuilder);
        List<Hotel> list = new ArrayList<>();
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Hotel.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
