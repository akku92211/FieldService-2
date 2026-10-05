package com.KeyStone.FieldService2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KeyStone.FieldService2.Entity.TimeLog;

public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {

}