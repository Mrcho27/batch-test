package com.example.batchtest.service;

import com.example.batchtest.dto.AladinApiDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@Transactional
public class AladinApiService {
    @Value("ttbqhdekdaos1029001")
    private String apiKey;

    public AladinApiDto findBestseller(){
        String baseUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx";
        String queryType = "Bestseller";
        String searchTarget = "Book";
        String output = "js";
        String version = "20131101";
        int start = 1;
        int maxResults = 50;

        String url = baseUrl + "?ttbkey=" + apiKey +
                "&QueryType=" + queryType +
                "&SearchTarget=" + searchTarget +
                "&output=" + output +
                "&version=" + version +
                "&Start=" + start +
                "&MaxResults=" + maxResults;
        log.info("url : {}", url);


        WebClient webClient = WebClient.builder().build();
        AladinApiDto result = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(AladinApiDto.class)
                .block();

        System.out.println("result = " + result);
        return result;
    }
}
