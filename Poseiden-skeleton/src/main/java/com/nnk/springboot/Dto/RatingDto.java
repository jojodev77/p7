package com.nnk.springboot.Dto;

import lombok.Data;

@Data
public class RatingDto {
	long id;
	long orderNumber;
	String sandPRating;
	String moodysRating;
	String fitchRating;
}
