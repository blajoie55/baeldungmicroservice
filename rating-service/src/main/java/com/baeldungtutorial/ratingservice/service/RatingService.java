package com.baeldungtutorial.ratingservice.service;

import com.baeldungtutorial.ratingservice.model.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RatingService {
    public List<Rating> findAllRatings()
    {
        return null;
    }

    public List<Rating> findRatingsByBookId(Long bookId)
    {
        return null;
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
}
