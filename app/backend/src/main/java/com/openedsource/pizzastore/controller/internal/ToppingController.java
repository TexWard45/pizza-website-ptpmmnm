package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.ToppingEntity;
import com.openedsource.pizzastore.database.repository.ToppingRepository;
import com.openedsource.pizzastore.dto.ToppingDto;
import com.openedsource.pizzastore.service.ToppingService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/topping")
@CrossOrigin(origins = "*")
public class ToppingController {
    @Autowired
    private ToppingRepository toppingRepository;
    @Autowired
    private ToppingService toppingService;

    @PostMapping
    public ResponseEntity<Object> addTopping(@RequestBody(required = false) ToppingDto toppingDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(toppingDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            ToppingEntity toppingEntity = new ToppingEntity();
            BeanUtils.copyProperties(toppingDto, toppingEntity);

            try {
                toppingService.insertTopping(toppingEntity);
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
    public ResponseEntity<Object> updateTopping(@RequestBody(required = false) ToppingDto toppingDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(toppingDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            ToppingEntity toppingEntity = new ToppingEntity();
            BeanUtils.copyProperties(toppingDto, toppingEntity);
            if (toppingRepository.findById(toppingEntity.getId()).isPresent()) {
                toppingService.updateTopping(toppingEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                toppingService.updateTopping(toppingEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopping(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (toppingRepository.findById(id).isPresent()) {
            toppingRepository.deleteById(id);
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "Topping was not found");
        }
        return response;
    }

    private String inputcheck(ToppingDto toppingDto) {

        if (ValidateUtils.isNullOrEmpty(toppingDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[ToppingEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(toppingDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(toppingDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
