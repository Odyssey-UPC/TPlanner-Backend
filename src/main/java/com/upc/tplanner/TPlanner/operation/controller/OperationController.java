package com.upc.tplanner.TPlanner.operation.controller;

import com.upc.tplanner.TPlanner.operation.dto.OperationRequest;
import com.upc.tplanner.TPlanner.operation.dto.OperationResponse;
import com.upc.tplanner.TPlanner.operation.dto.mapper.OperationMapper;
import com.upc.tplanner.TPlanner.operation.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class OperationController {

    @Autowired
    OperationService operationService;

    @Transactional(readOnly = true)
    @GetMapping("/operations")
    public ResponseEntity<List<OperationResponse>> getAllOperations(){
        var operations = operationService.getAllOperations();
        return new ResponseEntity<>(OperationMapper.INSTANCE.operationsToOperationResponses(operations), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/operations/{id}")
    public ResponseEntity<OperationResponse> getOperationById(@PathVariable("id") Long id){
        var operation = operationService.getOperationById(id);
        return new ResponseEntity<>(OperationMapper.INSTANCE.operationToOperationResponse(operation), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/operations/filterByOperationType")
    public ResponseEntity<List<OperationResponse>> getOperationsByOperationType(@RequestParam("operationType") String operationType){
        var operations = operationService.getOperationsByOperationType(operationType);
        return new ResponseEntity<>(OperationMapper.INSTANCE.operationsToOperationResponses(operations), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/operations/filterByTouristId")
    public ResponseEntity<List<OperationResponse>> getOperationsByTouristId(@RequestParam("touristId") Long touristId){
        var operations = operationService.getOperationsByTouristId(touristId);
        return new ResponseEntity<>(OperationMapper.INSTANCE.operationsToOperationResponses(operations), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @GetMapping("/operations/filterByTouristIdAndOperationType")
    public ResponseEntity<List<OperationResponse>> getOperationsByTouristIdAndOperationType(@RequestParam("touristId") Long touristId, @RequestParam("operationType") String operationType){
        var operations = operationService.getOperationsByTouristIdAndOperationType(touristId, operationType);
        return new ResponseEntity<>(OperationMapper.INSTANCE.operationsToOperationResponses(operations), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/operations")
    public ResponseEntity<OperationResponse> createOperation(@RequestBody OperationRequest operationRequest){
        var operation = OperationMapper.INSTANCE.operationRequestToOperation(operationRequest);
        var createdOperation = operationService.createOperation(operationRequest.getTouristId(), operationRequest.getTravelItineraryId(), operation);
        return new ResponseEntity<>(OperationMapper.INSTANCE.operationToOperationResponse(createdOperation), HttpStatus.CREATED);
    }


}
