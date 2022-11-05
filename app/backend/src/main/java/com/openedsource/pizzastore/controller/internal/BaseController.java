package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.BaseEntity;
import com.openedsource.pizzastore.database.repository.BaseRepository;
import com.openedsource.pizzastore.dto.BaseDto;
import com.openedsource.pizzastore.service.BaseService;
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
public class BaseController {

    @Autowired
    private BaseRepository baseRepository;
    @Autowired
    private BaseService baseService;

    @PostMapping("/base/add")
    public ResponseEntity<Object> addBase(@RequestBody(required = false) BaseDto baseDto) {

        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(baseDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            BaseEntity baseEntity = new BaseEntity();
            BeanUtils.copyProperties(baseDto, baseEntity);

            try {
                baseService.insertBase(baseEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/base/update")
    public ResponseEntity<Object> updateBase(@RequestBody(required = false) BaseDto baseDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(baseDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            BaseEntity baseEntity = new BaseEntity();
            BeanUtils.copyProperties(baseDto, baseEntity);

            baseService.updateBase(baseEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/base/delete/{id}")
    public ResponseEntity<?> deleteBase(@PathVariable("id") Integer id) {
        baseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(BaseDto baseDto) {

        if (ValidateUtils.isNullOrEmpty(baseDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[BaseEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(baseDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(baseDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}

