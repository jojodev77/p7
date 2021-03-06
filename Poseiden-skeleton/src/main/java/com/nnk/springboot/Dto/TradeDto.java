package com.nnk.springboot.Dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TradeDto {
	long id;
	long TradeId;
	String account;
	String type;
	double buyQuantity;
	double sellQuantity;
	double buyPrice;
	double sellPrice;
	LocalDateTime tradeDate;
	String security;
	String status;
	String trader;
	String benchmark;
	String book;
	String creationName;
	LocalDateTime creationDate;
	String revisionName;
	LocalDateTime revisionDate;
	String dealName;
	String sourceListId; 
	String side;
}
