package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;


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
