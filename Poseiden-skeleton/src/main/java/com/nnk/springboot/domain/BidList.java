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
@Table(name = "bidlist")
public class BidList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	long BidListId;
	@NotBlank(message = "account is not blank")
	String account;
	@NotBlank(message = "type is not blank")
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
