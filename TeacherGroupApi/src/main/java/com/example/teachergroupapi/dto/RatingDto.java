package com.example.teachergroupapi.dto;

import com.example.teachergroupapi.model.Rating;
import com.example.teachergroupapi.model.TeacherGroup;

public record RatingDto (
    Long id,
    int ratingValue,
    TeacherGroup group
){

    public static RatingDto mapRatingToDto(Rating rating) {
        RatingDto dto = new RatingDto(rating.getId(), rating.getValue(),rating.getGroup());
        return dto;
    }

    public static Rating mapDtoToRating(RatingDto dto, TeacherGroup group) {
        Rating rating = new Rating(dto.ratingValue,dto.group);
        return rating;
    }
}
