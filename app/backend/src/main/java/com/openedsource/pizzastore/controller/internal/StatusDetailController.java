package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.StatusDetailEntity;
import com.openedsource.pizzastore.database.repository.StatusDetailRepository;
import com.openedsource.pizzastore.dto.StatusDetailDto;
import com.openedsource.pizzastore.service.StatusDetailService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
@CrossOrigin(origins = "*")
public class StatusDetailController {
    @Autowired
    private StatusDetailRepository statusDetailRepository;
    @Autowired
    private StatusDetailService statusDetailService;

    @PostMapping("/statusdetail/add")
    public ResponseEntity<Object> addStatusDetail(@RequestBody(required = false) StatusDetailDto statusDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(statusDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            StatusDetailEntity statusDetailEntity = new StatusDetailEntity();
            BeanUtils.copyProperties(statusDetailDto, statusDetailEntity);

            try {
                statusDetailService.insertStatusDetail(statusDetailEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/statusdetail/update")
    public ResponseEntity<Object> updateStatusDetail(@RequestBody(required = false) StatusDetailDto statusDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(statusDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            StatusDetailEntity statusDetailEntity = new StatusDetailEntity();
            BeanUtils.copyProperties(statusDetailDto, statusDetailEntity);

            statusDetailService.updateStatusDetail(statusDetailEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/statusdetail/delete/{id}")
    public ResponseEntity<?> deleteStatusDetail(@PathVariable("id") Integer id) {
        statusDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(StatusDetailDto statusDetailDto) {

        if (ValidateUtils.isNullOrEmpty(statusDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[StatusDetailEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(statusDetailDto.getOrderid())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "OrderId" : ",OrderId"));
        }
        if (ValidateUtils.isNullOrEmpty(statusDetailDto.getStatusid())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "StatusId" : ",StatusId"));
        }
        if (ValidateUtils.isNullOrEmpty(statusDetailDto.getTime_created())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Time Created" : ",Time Created"));
        }
        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
