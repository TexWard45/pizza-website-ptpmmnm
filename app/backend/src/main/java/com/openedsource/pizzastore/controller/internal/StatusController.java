package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.StatusEntity;
import com.openedsource.pizzastore.database.repository.StatusRepository;
import com.openedsource.pizzastore.dto.StatusDto;
import com.openedsource.pizzastore.service.StatusService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/internal/status")
@CrossOrigin(origins = "*")
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private StatusService statusService;

    @PostMapping
    public ResponseEntity<Object> addStatus(@RequestBody(required = false) StatusDto statusDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(statusDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            StatusEntity orderDetailEntity = new StatusEntity();
            BeanUtils.copyProperties(statusDto, orderDetailEntity);

            try {
                statusService.insertStatus(orderDetailEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");

            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<Object> updateStatus(@RequestBody(required = false) StatusDto statusDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(statusDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            StatusEntity statusEntity = new StatusEntity();
            BeanUtils.copyProperties(statusDto, statusEntity);
            if (statusRepository.findById(statusEntity.getId()).isPresent()) {
                statusService.updateStatus(statusEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                statusService.updateStatus(statusEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();

        if (statusRepository.findById(id).isPresent()) {
            statusRepository.deleteById(id);
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "Category was not found");
        }
        return response;

    }

    private String inputcheck(StatusDto statusDto) {

        if (ValidateUtils.isNullOrEmpty(statusDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[StatusEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(statusDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(statusDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
