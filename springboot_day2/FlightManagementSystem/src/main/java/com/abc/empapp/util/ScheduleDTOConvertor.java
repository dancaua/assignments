package com.abc.empapp.util;

import com.abc.empapp.domain.dto.ScheduleDTO;
import com.abc.empapp.domain.entity.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDTOConvertor {

    public ScheduleDTO doToDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setDepartureTime(schedule.getDepartureTime());
        dto.setLandingTime(schedule.getLandingTime());
        dto.setStop(schedule.getStop());
        return dto;
    }

    public Schedule dtoToDO(ScheduleDTO schedule) {
        Schedule dto = new Schedule();
        dto.setDepartureTime(schedule.getDepartureTime());
        dto.setLandingTime(schedule.getLandingTime());
        dto.setStop(schedule.getStop());
        return dto;
    }
}
