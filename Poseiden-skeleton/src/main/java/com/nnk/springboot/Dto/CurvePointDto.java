package com.nnk.springboot.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CurvePointDto {
	long id;
	long CurveId;
	LocalDateTime asOfDate;
	double term;
	double value;
	LocalDateTime creationDate;
}
