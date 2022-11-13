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
@RequestMapping("/internal/warehousereceiptdetail")
@CrossOrigin(origins = "*")
public class WarehouseReceiptDetailController {
    @Autowired
    private WarehouseReceiptDetailRepository warehouseReceiptDetailRepository;
    @Autowired
    private WarehouseReceiptDetailService warehouseReceiptDetailService;

    @PostMapping
    public ResponseEntity<Object> addWarehouseReceiptDetail(@RequestBody(required = false) WarehouseReceiptDetailDto warehouseReceiptDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(warehouseReceiptDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            WarehouseReceiptDetailEntity orderDetailEntity = new WarehouseReceiptDetailEntity();
            BeanUtils.copyProperties(warehouseReceiptDetailDto, orderDetailEntity);

            try {
                warehouseReceiptDetailService.insertWarehouseReceiptDetail(orderDetailEntity);
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
    public ResponseEntity<Object> updateWarehouseReceiptDetail(@RequestBody(required = false) WarehouseReceiptDetailDto warehouseReceiptDetailDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(warehouseReceiptDetailDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            WarehouseReceiptDetailEntity warehouseReceiptDetailEntity = new WarehouseReceiptDetailEntity();
            BeanUtils.copyProperties(warehouseReceiptDetailDto, warehouseReceiptDetailEntity);
            if (warehouseReceiptDetailRepository.findById(warehouseReceiptDetailEntity.getId()).isPresent()) {
                warehouseReceiptDetailService.updateWarehouseReceiptDetail(warehouseReceiptDetailEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                warehouseReceiptDetailService.updateWarehouseReceiptDetail(warehouseReceiptDetailEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouseReceiptDetail(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (warehouseReceiptDetailRepository.findById(id).isPresent()) {
            warehouseReceiptDetailRepository.deleteById(id);
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "WareHouseReceiptDetail was not found");
        }
        return response;

    }

    private String inputcheck(WarehouseReceiptDetailDto warehouseReceiptDetailDto) {

        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[WarehouseReceiptDetailEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getWarehouse_receipt_id())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "WarehouseReceiptId" : ",WarehouseReceiptId"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getPizza_detail_id())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "PizzaDetailId" : ",PizzaDetailId"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getPrice())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Price" : ",Price"));
        }
        if (ValidateUtils.isNullOrEmpty(warehouseReceiptDetailDto.getQuantity()) && ValidateUtils.isFullWidthDigit(String.valueOf(warehouseReceiptDetailDto.getQuantity()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Quantity" : ",Quantity"));
        }
        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
