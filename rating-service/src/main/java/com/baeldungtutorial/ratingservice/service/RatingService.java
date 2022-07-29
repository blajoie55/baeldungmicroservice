package com.baeldungtutorial.ratingservice.service;

import com.baeldungtutorial.ratingservice.model.Rating;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    public List<Rating> findAllRatings()
    {
        return Lists.newArrayList(buildRating(1), buildRating(2), buildRating(3));
    }

    public List<Rating> findRatingsByBookId(Long bookId)
    {
        return Lists.newArrayList(buildRating(1), buildRating(2));
    }

    public Rating createRating(Rating rating) {
        return rating;
    }

    public void deleteRating(Long ratingId)
    {
    }

    public Rating updateRating(Rating rating, Long ratingId)
    {
        return rating;
    }

    public Rating updateRating(Map<String, String> updates, Long ratingId)
    {
        return null;
    }

    private Rating buildRating()
    {
        return buildRating(1);
    }

    private Rating buildRating(int ratingId)
    {
        Rating rating = new Rating();
        rating.setId(ratingId);
        rating.setBookId(2L);
        rating.setStars(5);
        return rating;
    }
}
