package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.SizeEntity;
import com.openedsource.pizzastore.database.repository.SizeRepository;
import com.openedsource.pizzastore.dto.SizeDto;
import com.openedsource.pizzastore.service.SizeService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("internal")
@CrossOrigin(origins = "*")
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private SizeService sizeService;

    @PostMapping("/size/add")
    public ResponseEntity<Object> addSize(@RequestBody SizeDto sizeDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(sizeDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            SizeEntity sizeEntity = new SizeEntity();
            BeanUtils.copyProperties(sizeDto, sizeEntity);

            try {
                sizeService.insertSize(sizeEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/size/update")
    public ResponseEntity<Object> updateSize(@RequestBody SizeDto sizeDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(sizeDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            SizeEntity sizeEntity = new SizeEntity();
            BeanUtils.copyProperties(sizeDto, sizeEntity);

            sizeService.updateSize(sizeEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/size/delete/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable("id") Integer id) {
        sizeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(SizeDto sizeDto) {

        if (ValidateUtils.isNullOrEmpty(sizeDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[UserEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(sizeDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(sizeDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }
        if (ValidateUtils.isNullOrEmpty(sizeDto.getPriority()) && ValidateUtils.isFullWidthDigit(String.valueOf(sizeDto.getPriority()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Priority" : ",Priority"));
//            errorField.append((ValidateUtils.isFullWidthDigit(String.valueOf(errorField))? "Priority":",Priority"));
        }
        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
