package com.nnk.springboot.domain;



import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "curvepoint")
public class CurvePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	long CurveId;
	LocalDateTime asOfDate;
	double term;
	double value;
	LocalDateTime creationDate;
}
