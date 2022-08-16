package com.abc.empapp.exception.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExceptionTemplate {

	LocalDate todayDate;
	LocalTime todayTime;
	String url;
	String className;
	String customMsg;
	String solution;
}
