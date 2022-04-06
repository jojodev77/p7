package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;


@Entity
@Data
@Table(name = "trade")
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
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
