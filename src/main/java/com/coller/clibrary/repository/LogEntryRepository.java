package com.coller.clibrary.repository;

import com.coller.clibrary.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long>{
}
