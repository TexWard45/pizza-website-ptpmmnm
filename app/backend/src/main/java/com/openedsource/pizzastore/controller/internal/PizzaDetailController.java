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
@RequestMapping("internal")
@CrossOrigin(origins = "*")
public class PizzaDetailController {
    @Autowired
    private PizzaDetailRepository pizzaDetailRepository;
    @Autowired
    private PizzaDetailService pizzaDetailService;

    @PostMapping("/pizzadetail/add")
    public ResponseEntity<Object> addPizzaDetail(@RequestBody(required = false) PizzaDetailDto pizzaDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(pizzaDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            PizzaDetailEntity pizzaDetailEntity = new PizzaDetailEntity();
            BeanUtils.copyProperties(pizzaDetailDto, pizzaDetailEntity);

            try {
                pizzaDetailService.insertPizzaDetail(pizzaDetailEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/pizzadetail/update")
    public ResponseEntity<Object> updatePizzaDetail(@RequestBody(required = false) PizzaDetailDto pizzaDetailDto){
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(pizzaDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            PizzaDetailEntity pizzaDetailEntity = new PizzaDetailEntity();
            BeanUtils.copyProperties(pizzaDetailDto, pizzaDetailEntity);

            pizzaDetailService.updatePizzaDetail(pizzaDetailEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }
    @DeleteMapping("/pizzadetail/delete/{id}")
    public ResponseEntity<?> deletePizzaDetail(@PathVariable("id")Integer id) {
        pizzaDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private String inputcheck(PizzaDetailDto pizzaDetailDto) {

        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[UserEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getSizeid()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDetailDto.getSizeid()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "SizeId" : ",SizeId"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getPizzaid()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDetailDto.getPizzaid()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "PizzaId" : ",PizzaId"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getPrice())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Price" : ",Price"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDetailDto.getQuantity()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDetailDto.getQuantity()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Quantity" : ",Quantity"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
