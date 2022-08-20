package com.abc.empapp.domain.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	@Id
	@Column(name = "flight_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long flightId;
	private String flightName;
	private String flightType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "schedule", referencedColumnName = "id")
	private Schedule flightSchedule;

}















