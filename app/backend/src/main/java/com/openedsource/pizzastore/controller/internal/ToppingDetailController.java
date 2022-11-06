package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.ToppingDetailEntity;
import com.openedsource.pizzastore.database.repository.ToppingDetailRepository;
import com.openedsource.pizzastore.dto.ToppingDetailDto;
import com.openedsource.pizzastore.service.ToppingDetailService;
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
public class ToppingDetailController {
    @Autowired
    private ToppingDetailRepository toppingDetailRepository;
    @Autowired
    private ToppingDetailService toppingDetailService;

    @PostMapping("/toppingdetail/add")
    public ResponseEntity<Object> addToppingDetail(@RequestBody(required = false) ToppingDetailDto toppingDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(toppingDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            ToppingDetailEntity toppingDetailEntity = new ToppingDetailEntity();
            BeanUtils.copyProperties(toppingDetailDto, toppingDetailEntity);

            try {
                toppingDetailService.insertToppingDetail(toppingDetailEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/toppingdetail/update")
    public ResponseEntity<Object> updateToppingDetail(@RequestBody(required = false) ToppingDetailDto toppingDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(toppingDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            ToppingDetailEntity toppingDetailEntity = new ToppingDetailEntity();
            BeanUtils.copyProperties(toppingDetailDto, toppingDetailEntity);

            toppingDetailService.updateToppingDetail(toppingDetailEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/toppingdetail/delete/{id}")
    public ResponseEntity<?> deleteToppingDetail(@PathVariable("id") Integer id) {
        toppingDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(ToppingDetailDto toppingDetailDto) {

        if (ValidateUtils.isNullOrEmpty(toppingDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[ToppingDetailEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(toppingDetailDto.getPizzaid()) && ValidateUtils.isFullWidthDigit(String.valueOf(toppingDetailDto.getPizzaid()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "PizzaId" : ",PizzaId"));
        }
        if (ValidateUtils.isNullOrEmpty(toppingDetailDto.getToppingid()) && ValidateUtils.isFullWidthDigit(String.valueOf(toppingDetailDto.getToppingid())))
        {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "ToppingId" : ",ToppingId"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}

