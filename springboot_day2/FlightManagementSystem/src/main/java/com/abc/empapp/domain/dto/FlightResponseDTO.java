package com.abc.empapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponseDTO {

	private Long flightId;
	private String flightName;
	private String flightType;

}
