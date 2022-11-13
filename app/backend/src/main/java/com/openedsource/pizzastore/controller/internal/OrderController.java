package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.OrderEntity;
import com.openedsource.pizzastore.database.repository.OrderRepository;
import com.openedsource.pizzastore.dto.OrderDto;
import com.openedsource.pizzastore.dto.UserDto;
import com.openedsource.pizzastore.service.OrderService;
import com.openedsource.pizzastore.util.ResponseUtils;
import com.openedsource.pizzastore.util.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/internal/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> addOrder(@RequestBody(required = false) OrderDto orderDto) {

        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(orderDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderDto, orderEntity);

            try {
                orderService.insertOrder(orderEntity);
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
    public ResponseEntity<Object> updateOrder(@RequestBody(required = false) OrderDto orderDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(orderDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            OrderEntity orderEntity = new OrderEntity();
            BeanUtils.copyProperties(orderDto, orderEntity);
            if (orderRepository.findById(orderEntity.getId()).isPresent()) {
                orderService.updateOrder(orderEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                orderService.updateOrder(orderEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "Order was not found");
        }
        return response;

    }

    private String inputcheck(OrderDto orderDto) {

        if (ValidateUtils.isNullOrEmpty(orderDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[OrderEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(orderDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getCustomer())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Customer" : ",Customer"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getHandler())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Handler" : ",Handler"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getTotal_price())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "TotalPrice" : ",TotalPrice"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getQuantity()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDto.getQuantity()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Quantity" : ",Quantity"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getFullname())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Fullname" : ",Fullname"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getAddress())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Address" : ",Address"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getPhone()) && ValidateUtils.isFullWidthDigit(orderDto.getPhone())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Phone" : ",Phone"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getEmail())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Email" : ",Email"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getPayment_type()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDto.getPayment_type()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Payment Type" : ",Payment Type"));
        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getOrder_type()) && ValidateUtils.isFullWidthDigit(String.valueOf(orderDto.getOrder_type()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Order Type" : ",Order Type"));
        }
//        if (ValidateUtils.isNullOrEmpty(orderDto.getOrdertime())) {
//            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Order Time" : ",Order Time"));
//        }
        if (ValidateUtils.isNullOrEmpty(orderDto.getNote())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Note" : ",Note"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}


//    @PutMapping("/order/update/{id}")
//    public ResponseEntity<OrderEntity> updateGroup(@RequestBody OrderEntity order, @PathVariable("id")Integer id){
//        Optional<OrderEntity> orderOptional = orderRepository.findById(id);
//        if(orderOptional.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//        order.setId(id);
//        OrderEntity updateGroup = orderRepository.save(order);
//        return new ResponseEntity<>(updateGroup,HttpStatus.NO_CONTENT);
//    }
