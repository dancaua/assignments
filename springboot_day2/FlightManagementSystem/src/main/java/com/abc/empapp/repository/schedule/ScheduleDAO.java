package com.abc.empapp.repository.schedule;

import com.abc.empapp.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDAO extends JpaRepository<Schedule, Long> {
}
