package com.nnk.springboot.Dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BidListDto {
	long id;
	long BidListId;
	String account;
	String type;
	double bidQuantity;
	double askQuantity;
	double bid;
	double ask;
	String benchmark;
	LocalDateTime bidListDate;
	String commentary;
	String security;
	String status;
	String trader;
	String book;
	String creationName;
	LocalDateTime creationDate;
	String revisionName;
	LocalDateTime revisionDate;
	String dealName;
	String dealType;
	String sourceListId;
	String side;
}
