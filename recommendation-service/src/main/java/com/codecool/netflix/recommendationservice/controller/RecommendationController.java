package com.codecool.netflix.recommendationservice.controller;

import com.codecool.netflix.recommendationservice.dao.RecommendationDao;
import com.codecool.netflix.recommendationservice.model.Recommendation;
import com.codecool.netflix.recommendationservice.model.RecommendationList;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RecommendationController {

    private RecommendationDao recommendationDao;

    @GetMapping("/videos/{videoId}/recommendations")
    public RecommendationList getRecommendations(@PathVariable Long videoId) {
        return recommendationDao.getRecommendations(videoId);
    }

    @PostMapping("/videos/{videoId}/recommendations")
    public Recommendation addRecommendation(@RequestBody Recommendation recommendation) {
        recommendationDao.addNewRecommendation(recommendation);
        return recommendation;
    }

    @PutMapping("/recommendations/{recommendationId}")
    public void updateRecommendation(@PathVariable Long recommendationId,
                                     @RequestBody Recommendation editedRecommendation) {
        recommendationDao.updateRecommendation(recommendationId, editedRecommendation);
    }

}
