package com.codecool.netflix.videoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoWithRecommendations {
    private Video video;
    private List<Recommendation> recommendations;
}
