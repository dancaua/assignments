package com.abc.empapp.domain.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long flightId;
	private String flightName;
	private String flightType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "schedule", referencedColumnName = "id")
	private Schedule flightSchedule;

}















