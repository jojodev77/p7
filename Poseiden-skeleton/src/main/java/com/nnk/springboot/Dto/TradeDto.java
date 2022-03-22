package com.nnk.springboot.Dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TradeDto {
	long TradeId;
	@NotBlank(message = "account is not blank")
	String account;
	@NotBlank(message = "type is not blank")
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
