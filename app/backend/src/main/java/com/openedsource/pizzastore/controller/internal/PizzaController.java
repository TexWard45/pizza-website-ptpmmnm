package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.PizzaEntity;
import com.openedsource.pizzastore.database.repository.PizzaRepository;
import com.openedsource.pizzastore.dto.PizzaDto;
import com.openedsource.pizzastore.service.PizzaService;
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
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaService pizzaService;

    @PostMapping("/pizza/add")
    public ResponseEntity<Object> addPizza(@RequestBody(required = false) PizzaDto pizzaDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(pizzaDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            PizzaEntity pizzaEntity = new PizzaEntity();
            BeanUtils.copyProperties(pizzaDto, pizzaEntity);

            try {
                pizzaService.insertPizza(pizzaEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/pizza/update")
    public ResponseEntity<Object> updatePizza(@RequestBody(required = false) PizzaDto pizzaDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(pizzaDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            PizzaEntity pizzaEntity = new PizzaEntity();
            BeanUtils.copyProperties(pizzaDto, pizzaEntity);

            pizzaService.updatePizza(pizzaEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;

    }

    @DeleteMapping("/pizza/delete/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable("id") Integer id) {
        pizzaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(PizzaDto pizzaDto) {

        if (ValidateUtils.isNullOrEmpty(pizzaDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[PizzaEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(pizzaDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDto.getCategoryid()) && ValidateUtils.isFullWidthDigit(String.valueOf(pizzaDto.getCategoryid()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "CategoryId" : ",CategoryId"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDto.getDescription())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Description" : ",Description"));
        }
        if (ValidateUtils.isNullOrEmpty(pizzaDto.getImage())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Image" : ",Image"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
