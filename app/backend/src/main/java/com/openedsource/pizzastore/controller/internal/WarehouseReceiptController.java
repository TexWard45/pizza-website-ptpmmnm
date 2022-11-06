package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.WarehouseReceiptEntity;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptRepository;
import com.openedsource.pizzastore.dto.WarehouseReceiptDto;
import com.openedsource.pizzastore.service.WarehouseReceiptService;
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
@RequestMapping("/internal")
@CrossOrigin(origins = "*")
public class WarehouseReceiptController {
    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;
    @Autowired
    private WarehouseReceiptService warehouseReceiptService;

    @PostMapping("/warehousereceipt/add")
    public ResponseEntity<Object> addWarehouseReceipt(@RequestBody(required = false) WarehouseReceiptDto warehouseReceiptDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(warehouseReceiptDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            WarehouseReceiptEntity orderDetailEntity = new WarehouseReceiptEntity();
            BeanUtils.copyProperties(warehouseReceiptDto, orderDetailEntity);

            try {
                warehouseReceiptService.insertWarehouseReceipt(orderDetailEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/warehousereceipt/update")
    public ResponseEntity<Object> updateWarehouseReceipt(@RequestBody(required = false) WarehouseReceiptDto warehouseReceiptDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(warehouseReceiptDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            WarehouseReceiptEntity warehouseReceiptEntity = new WarehouseReceiptEntity();
            BeanUtils.copyProperties(warehouseReceiptDto, warehouseReceiptEntity);

            warehouseReceiptService.updateWarehouseReceipt(warehouseReceiptEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/warehousereceipt/delete/{id}")
    public ResponseEntity<?> deleteWarehouseReceipt(@PathVariable("id") Integer id) {
        warehouseReceiptRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String inputcheck(WarehouseReceiptDto warehouseReceiptDto) {

        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[WarehouseReceiptEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDto.getSupplierid()) && ValidateUtils.isFullWidthDigit(String.valueOf(warehouseReceiptDto.getSupplierid()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "SupplierId" : ",SupplierId"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDto.getHandler())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Handler" : ",Handler"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDto.getTime_created())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Time Created" : ",Time Created"));
        }
        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
