package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.WarehouseReceiptDetailEntity;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptDetailRepository;
import com.openedsource.pizzastore.dto.WarehouseReceiptDetailDto;
import com.openedsource.pizzastore.service.WarehouseReceiptDetailService;
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
public class WarehouseReceiptDetailController {
    @Autowired
    private WarehouseReceiptDetailRepository warehouseReceiptDetailRepository;
    @Autowired
    private WarehouseReceiptDetailService warehouseReceiptDetailService;

    @PostMapping("/warehousereceiptdetail/add")
    public ResponseEntity<Object> addWarehouseReceiptDetail(@RequestBody(required = false) WarehouseReceiptDetailDto warehouseReceiptDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(warehouseReceiptDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            WarehouseReceiptDetailEntity orderDetailEntity = new WarehouseReceiptDetailEntity();
            BeanUtils.copyProperties(warehouseReceiptDetailDto, orderDetailEntity);

            try {
                warehouseReceiptDetailService.insertWarehouseReceiptDetail(orderDetailEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/warehousereceiptdetail/update")
    public ResponseEntity<Object> updateWarehouseReceiptDetail(@RequestBody(required = false) WarehouseReceiptDetailDto warehouseReceiptDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(warehouseReceiptDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            WarehouseReceiptDetailEntity orderDetailEntity = new WarehouseReceiptDetailEntity();
            BeanUtils.copyProperties(warehouseReceiptDetailDto, orderDetailEntity);

            warehouseReceiptDetailService.updateWarehouseReceiptDetail(orderDetailEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/warehousereceiptdetail/delete/{id}")
    public ResponseEntity<?> deleteWarehouseReceiptDetail(@PathVariable("id") Integer id) {
        warehouseReceiptDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(WarehouseReceiptDetailDto warehouseReceiptDetailDto) {

        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[WarehouseReceiptDetailEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getWarehousereceiptid())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "WarehouseReceiptId" : ",WarehouseReceiptId"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getPizzadetailid())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "PizzaDetailId" : ",PizzaDetailId"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getPrice())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Price" : ",Price"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getAmount()) && ValidateUtils.isFullWidthDigit(String.valueOf(warehouseReceiptDetailDto.getAmount()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Amount" : ",Amount"));
        }
        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
