package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.OrderDetailEntity;
import com.openedsource.pizzastore.database.repository.OrderDetailRepository;
import com.openedsource.pizzastore.dto.OrderDetailDto;
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
@RequestMapping("/internal/orderdetail")
@CrossOrigin(origins = "*")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<Object> addOrderDetail(@RequestBody(required = false) OrderDetailDto orderDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(orderDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            BeanUtils.copyProperties(orderDetailDto, orderDetailEntity);

            try {
                orderDetailService.insertOrderDetail(orderDetailEntity);
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
    public ResponseEntity<Object> updateOrderDetail(@RequestBody(required = false) OrderDetailDto orderDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(orderDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            BeanUtils.copyProperties(orderDetailDto, orderDetailEntity);
            if (orderDetailRepository.findById(orderDetailEntity.getId()).isPresent()) {
                orderDetailService.updateOrderDetail(orderDetailEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                orderDetailService.updateOrderDetail(orderDetailEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (orderDetailRepository.findById(id).isPresent()) {
            orderDetailRepository.deleteById(id);
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "OrderDetail was not found");
        }
        return response;

    }

    private String inputcheck(OrderDetailDto orderDetailDto) {

        if (ValidateUtils.isNullOrEmpty(orderDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[OrderDetailEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getOrder_id()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDetailDto.getOrder_id()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "OrderId" : ",OrderId"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDetailDto.getPizza_detail_id()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDetailDto.getPizza_detail_id()))) {
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
