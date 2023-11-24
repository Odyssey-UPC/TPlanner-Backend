package com.upc.tplanner.TPlanner.operation.service.impl;

import com.upc.tplanner.TPlanner.operation.model.Operation;
import com.upc.tplanner.TPlanner.operation.repository.OperationRepository;
import com.upc.tplanner.TPlanner.operation.service.OperationService;
import com.upc.tplanner.TPlanner.travelItinerary.model.TravelItinerary;
import com.upc.tplanner.TPlanner.travelItinerary.repository.TravelItineraryRepository;
import com.upc.tplanner.TPlanner.user.repository.TouristRepository;
import com.upc.tplanner.TPlanner.utils.exception.ResourceNotFoundException;
import com.upc.tplanner.TPlanner.utils.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    TouristRepository touristRepository;

    @Autowired
    TravelItineraryRepository travelItineraryRepository;

    @Override
    public Operation createOperation(Long touristId, Long travelItineraryId, Operation operation) {
        existsTouristById(touristId);
        existTravelItineraryById(travelItineraryId);
        validateOperationType(operation.getOperationType());

        operation.setOperationDate(LocalDateTime.now());
        operation.setTourist(touristRepository.findById(touristId).get());
        operation.setTravelItinerary(travelItineraryRepository.findById(travelItineraryId).get());

        return operationRepository.save(operation);
    }

    @Override
    public Operation getOperationById(Long operationId) {
        existsOperationById(operationId);
        return operationRepository.findById(operationId).get();
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public List<Operation> getOperationsByOperationType(String operationType) {
        validateOperationType(operationType);
        return operationRepository.findByOperationType(operationType);
    }

    @Override
    public List<Operation> getOperationsByTouristId(Long touristId) {
        existsTouristById(touristId);
        return operationRepository.findByTourist_Id(touristId);
    }

    @Override
    public List<Operation> getOperationsByTouristIdAndOperationType(Long touristId, String operationType) {
        existsTouristById(touristId);
        validateOperationType(operationType);
        return operationRepository.findByTourist_IdAndOperationType(touristId, operationType);
    }

    private void existsTouristById(Long touristId) {
        if (!touristRepository.existsById(touristId)){
            throw new ResourceNotFoundException("tourist not found with id: " + touristId);
        }
    }

    private void validateOperationType(String operationType) {
        if (operationType == null || operationType.isEmpty()){
            throw new ValidationException("operation type is required");
        }
        if (!operationType.equals("CREATED") && !operationType.equals("BOUGHT")){
            throw new ValidationException("operation type must be CREATED or BOUGHT");
        }
    }

    private void existsOperationById(Long operationId) {
        if (!operationRepository.existsById(operationId)){
            throw new ResourceNotFoundException("operation not found with id: " + operationId);
        }
    }

    private void existTravelItineraryById(Long travelItineraryId) {
        if (!travelItineraryRepository.existsById(travelItineraryId)){
            throw new ResourceNotFoundException("travel itinerary not found with id: " + travelItineraryId);
        }
    }

}
