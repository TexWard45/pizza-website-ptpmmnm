package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.OrderDetailEntity;
import com.openedsource.pizzastore.database.repository.OrderDetailRepository;
import com.openedsource.pizzastore.dto.OrderDetailDto;
import com.openedsource.pizzastore.dto.UserDto;
import com.openedsource.pizzastore.service.OrderDetailService;
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
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/orderdetail/add")
    public ResponseEntity<Object> addOrderDetail(@RequestBody(required = false) OrderDetailDto orderDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(orderDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            BeanUtils.copyProperties(orderDetailDto, orderDetailEntity);

            try {
                orderDetailService.insertOrderDetail(orderDetailEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/orderdetail/update")
    public ResponseEntity<Object> updateOrderDetail(@RequestBody(required = false) OrderDetailDto orderDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(orderDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            BeanUtils.copyProperties(orderDetailDto, orderDetailEntity);

            orderDetailService.updateOrderDetail(orderDetailEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;

    }

    @DeleteMapping("/orderdetail/delete/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable("id") Integer id) {
        orderDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(OrderDetailDto orderDetailDto) {

        if (ValidateUtils.isNullOrEmpty(orderDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[UserEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getOrderid()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDetailDto.getOrderid()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "OrderId" : ",OrderId"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getPizzadetailid()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDetailDto.getPizzadetailid()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "PizzaDetailId" : ",PizzaDetailId"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getPrice())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Price" : ",Price"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getQuantity()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDetailDto.getQuantity()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Quantity" : ",Quantity"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
