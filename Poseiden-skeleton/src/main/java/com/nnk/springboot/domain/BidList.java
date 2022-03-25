package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
