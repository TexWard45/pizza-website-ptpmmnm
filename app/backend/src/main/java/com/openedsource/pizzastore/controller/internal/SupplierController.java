package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.SupplierEntity;
import com.openedsource.pizzastore.database.repository.SupplierRepository;
import com.openedsource.pizzastore.dto.SupplierDto;
import com.openedsource.pizzastore.service.SupplierService;
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
@RequestMapping("/internal/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<Object> addSupplier(@RequestBody(required = false) SupplierDto supplierDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(supplierDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            SupplierEntity supplierEntity = new SupplierEntity();
            BeanUtils.copyProperties(supplierDto, supplierEntity);

            try {
                supplierService.insertSupplier(supplierEntity);
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
    public ResponseEntity<Object> updateSupplier(@RequestBody(required = false) SupplierDto supplierDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(supplierDto);
        if (ValidateUtils.isNullOrEmpty(message)) {
            SupplierEntity supplierEntity = new SupplierEntity();
            BeanUtils.copyProperties(supplierDto, supplierEntity);
            if (supplierRepository.findById(supplierEntity.getId()).isPresent()) {
                supplierService.updateSupplier(supplierEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                supplierService.updateSupplier(supplierEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (supplierRepository.findById(id).isPresent()){
        supplierRepository.deleteById(id);response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "Supplier was not found");
        }
        return response;
    }

    private String inputcheck(SupplierDto supplierDto) {

        if (ValidateUtils.isNullOrEmpty(supplierDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[SupplierEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(supplierDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(supplierDto.getDisplay())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Display" : ",Display"));
        }
        if (ValidateUtils.isNullOrEmpty(supplierDto.getAddress())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Address" : ",Address"));
        }
        if (ValidateUtils.isNullOrEmpty(supplierDto.getPhone()) && ValidateUtils.isFullWidthDigit(supplierDto.getPhone())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Phone" : ",Phone"));
            errorField.append((ValidateUtils.isFullWidthDigit(errorField.toString()) ? "Phone" : ",Phone"));
        }
        if (ValidateUtils.isNullOrEmpty(supplierDto.getFax())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Fax" : ",Fax"));
        }
        if (ValidateUtils.isTel(supplierDto.getEmail())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Email" : ",Email"));
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}
