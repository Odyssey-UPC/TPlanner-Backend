package com.upc.tplanner.TPlanner.operation.repository;

import com.upc.tplanner.TPlanner.operation.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    boolean existsById(Long id);
    List<Operation> findByTourist_IdAndOperationType(Long touristId, String operationType);
    List<Operation> findByTourist_Id(Long touristId);
    List<Operation> findByOperationType(String operationType);
}
