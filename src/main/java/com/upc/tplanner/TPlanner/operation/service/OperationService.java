package com.upc.tplanner.TPlanner.operation.service;

import com.upc.tplanner.TPlanner.operation.model.Operation;

import java.util.List;

public interface OperationService {
    Operation createOperation(Long touristId, Long travelItineraryId, Operation operation);
    Operation getOperationById(Long operationId);
    List<Operation> getAllOperations();
    List<Operation> getOperationsByOperationType(String operationType);
    List<Operation> getOperationsByTouristId(Long touristId);
    List<Operation> getOperationsByTouristIdAndOperationType(Long touristId, String operationType);
}
