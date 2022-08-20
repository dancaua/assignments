package com.abc.empapp.service.flight;

import com.abc.empapp.domain.dto.FlightResponseDTO;
import com.abc.empapp.domain.entity.Flight;
import com.abc.empapp.domain.entity.Schedule;
import com.abc.empapp.repository.flight.FlightDAO;
import com.abc.empapp.repository.flight.FlightRepository;
import com.abc.empapp.repository.schedule.ScheduleDAO;
import com.abc.empapp.util.FlightDTOConvertor;
import com.abc.empapp.util.FlightType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FlightServiceImplUnitTest {

    @Mock
    private FlightDAO flightDAO;

    @Mock
    private ScheduleDAO scheduleDAO;

    @Mock
    private FlightDTOConvertor flightDTOConvertor;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(description = "Test the findById method flow")
    void testGetFlightById() {
        // given
        final Long targetId = 1L;
        Optional<Flight> searchResult = Optional.of(new Flight());

        // when
        when(flightDAO.findById(targetId)).thenReturn(searchResult);
        Flight f = flightService.getFlightById(1L);

        // then
        assertNotNull(f);
    }

    @Test(description = "Test the existsById method flow")
    void testExistsById() {
        // given
        final Long targetId = 1L;
        final boolean shouldEntityExist = true;

        // when
        when(flightDAO.existsById(targetId)).thenReturn(shouldEntityExist);
        final boolean output = flightService.existsById(targetId);

        // then
        assertEquals(output, shouldEntityExist);
    }

    @Test(description = "Test the add flight method flow")
    void testAddFlight() {
        // given
        final Long expectedFlightId = 1L;
        final String expectedFlightName = "Test Flight Name";
        Flight flight = Flight.builder()
                .flightId(expectedFlightId)
                .flightName(expectedFlightName)
                .flightSchedule(new Schedule())
                .flightType(FlightType.DOMESTIC.getType())
                .build();

        FlightResponseDTO flightResponseDTO = new FlightResponseDTO();
        BeanUtils.copyProperties(flight, flightResponseDTO);


        // when
        when(flightDAO.save(flight)).thenReturn(flight);
        when(scheduleDAO.save(any(Schedule.class))).thenReturn(new Schedule());
        when(flightDTOConvertor.getFlightReponseDTO(flight)).thenReturn(new FlightResponseDTO());


        // then
        assertNotNull(flightResponseDTO);
        assertThat(flightResponseDTO.getFlightId())
                .isNotNull()
                .isEqualTo(expectedFlightId);

        assertThat(flightResponseDTO.getFlightName())
                .isNotBlank()
                .isEqualTo(expectedFlightName);
    }
}
