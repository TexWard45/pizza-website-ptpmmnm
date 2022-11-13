package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.PizzaDetailEntity;
import com.openedsource.pizzastore.database.repository.PizzaDetailRepository;
import com.openedsource.pizzastore.dto.PizzaDetailDto;
import com.openedsource.pizzastore.service.PizzaDetailService;
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
@RequestMapping("/internal/pizzadetail")
@CrossOrigin(origins = "*")
public class PizzaDetailController {
    @Autowired
    private PizzaDetailRepository pizzaDetailRepository;
    @Autowired
    private PizzaDetailService pizzaDetailService;

    @PostMapping
    public ResponseEntity<Object> addPizzaDetail(@RequestBody(required = false) PizzaDetailDto pizzaDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(pizzaDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            PizzaDetailEntity pizzaDetailEntity = new PizzaDetailEntity();
            BeanUtils.copyProperties(pizzaDetailDto, pizzaDetailEntity);

            try {
                pizzaDetailService.insertPizzaDetail(pizzaDetailEntity);
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
    public ResponseEntity<Object> updatePizzaDetail(@RequestBody(required = false) PizzaDetailDto pizzaDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(pizzaDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            PizzaDetailEntity pizzaDetailEntity = new PizzaDetailEntity();
            BeanUtils.copyProperties(pizzaDetailDto, pizzaDetailEntity);
            if (pizzaDetailRepository.findById(pizzaDetailEntity.getId()).isPresent()) {
                pizzaDetailService.updatePizzaDetail(pizzaDetailEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                pizzaDetailService.updatePizzaDetail(pizzaDetailEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");

            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePizzaDetail(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (pizzaDetailRepository.findById(id).isPresent()) {
            pizzaDetailRepository.deleteById(id);
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "PizzaDetail was not found");
        }
        return response;

    }

    private String inputcheck(PizzaDetailDto pizzaDetailDto) {

        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[PizzaDetailEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getSize_id()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDetailDto.getSize_id()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "SizeId" : ",SizeId"));
            errorField.append((ValidateUtils.isFullWidthDigit(errorField.toString()) ? "SizeId" : ",SizeId"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getPizza_id()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDetailDto.getPizza_id()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "PizzaId" : ",PizzaId"));
            errorField.append((ValidateUtils.isFullWidthDigit(errorField.toString()) ? "PizzaId" : ",PizzaId"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getBase_id()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDetailDto.getBase_id()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "BaseId" : ",BaseId"));
            errorField.append((ValidateUtils.isFullWidthDigit(errorField.toString()) ? "BaseId" : ",BaseId"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getPrice())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Price" : ",Price"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getQuantity()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDetailDto.getQuantity()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Quantity" : ",Quantity"));
            errorField.append((ValidateUtils.isFullWidthDigit(errorField.toString()) ? "Quantity" : ",Quantity"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
