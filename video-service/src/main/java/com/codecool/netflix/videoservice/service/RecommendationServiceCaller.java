package com.codecool.netflix.videoservice.service;

import com.codecool.netflix.videoservice.model.Recommendation;
import com.codecool.netflix.videoservice.model.RecommendationList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RecommendationServiceCaller {

    private RestTemplate restTemplate;

    public RecommendationServiceCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${recommendation-service.url}")
    private String baseUrl;

    public List<Recommendation> getRecommendations(Long videoId) {
        log.info("recommendation service caller: get recommendations for video (id: " + videoId + ")");

        String url = baseUrl + "/videos/" + videoId + "/recommendations";
        RecommendationList body = restTemplate.getForEntity(url, RecommendationList.class).getBody();
        return body != null ? body.getRecommendations() : null;
    }

    public Recommendation addRecommendation(Recommendation recommendation) {
        log.info("recommendation service caller: add recommendation: " + recommendation);

        HttpEntity<Recommendation> request = new HttpEntity<>(recommendation);
        String url = baseUrl + "/videos/" + recommendation.getVideoId() + "/recommendations";
        return restTemplate.postForObject(url, request, Recommendation.class);
    }

    public void updateRecommendation(Long recommendationId, Recommendation editedRecommendation) {
        log.info("recommendation service caller: update recommendation (id: " + recommendationId + "): "
                + editedRecommendation);

        String url = baseUrl + "/recommendations/" + recommendationId;
        HttpEntity<Recommendation> requestUpdate = new HttpEntity<>(editedRecommendation);
        restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Recommendation.class);
    }

}
