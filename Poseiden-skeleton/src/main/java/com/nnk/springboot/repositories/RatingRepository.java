package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	Optional<Rating> findBySandPRating(String fitchRating);

}
