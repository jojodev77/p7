package com.nnk.springboot.Dto;

import lombok.Data;

@Data
public class RuleNameDto {
	long id;
	String name;
	String description;
	String json;
	String template;
	String sqlStr;
	String sqlPart;
}
