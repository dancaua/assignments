package com.abc.empapp.service.schedule;

import com.abc.empapp.domain.dto.ScheduleDTO;

public interface ScheduleService {
    ScheduleDTO save(Long flightId, ScheduleDTO scheduleDTO);
}
