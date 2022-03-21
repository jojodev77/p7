package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;


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
