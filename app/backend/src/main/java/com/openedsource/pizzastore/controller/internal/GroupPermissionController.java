package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.constants.Constants;
import com.openedsource.pizzastore.database.entity.GroupPermissionEntity;
import com.openedsource.pizzastore.database.repository.GroupPermissionRepository;
import com.openedsource.pizzastore.dto.GroupPermissionDto;
import com.openedsource.pizzastore.dto.UserDto;
import com.openedsource.pizzastore.service.GroupPermissionService;
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
@RequestMapping("/internal/grouppermission")
@CrossOrigin(origins = "*")
public class GroupPermissionController {
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;
    @Autowired
    private GroupPermissionService groupPermissionService;

    @PostMapping
    public ResponseEntity<Object> addGroupPermission(@RequestBody(required = false) GroupPermissionDto groupPermissionDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(groupPermissionDto);

        if (ValidateUtils.isNullOrEmpty(message)) {
            GroupPermissionEntity groupPermissionEntity = new GroupPermissionEntity();
            BeanUtils.copyProperties(groupPermissionDto, groupPermissionEntity);

            try {
                groupPermissionService.insertGroupPermission(groupPermissionEntity);
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
    public ResponseEntity<Object> updateGroupPermission(@RequestBody GroupPermissionDto groupPermissionDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(groupPermissionDto);

        if (ValidateUtils.isNullOrEmpty(message)) {
            GroupPermissionEntity groupPermissionEntity = new GroupPermissionEntity();
            BeanUtils.copyProperties(groupPermissionDto, groupPermissionEntity);
            if (groupPermissionRepository.findById(groupPermissionEntity.getId()).isPresent()) {
                groupPermissionService.updateGroupPermission(groupPermissionEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Update Successfully");
            } else {
                groupPermissionService.updateGroupPermission(groupPermissionEntity);
                response = ResponseUtils.buildMessageReponse(HttpStatus.CREATED, "Successfully Added");
            }

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroupPermission(@PathVariable("id") Integer id) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        if (groupPermissionRepository.findById(id).isPresent()) {
            groupPermissionRepository.deleteById(id);
            response = ResponseUtils.buildMessageReponse(HttpStatus.OK, "Successfully Deleted");
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.NOT_FOUND, "GroupPermission was not found");
        }
        return response;

    }

    private String inputcheck(GroupPermissionDto groupPermissionDto) {

        if (ValidateUtils.isNullOrEmpty(groupPermissionDto)) {
            return Constants.MessageString.PARAMETER_ERROR.getMessage() + "[GroupPermissionEntity]";
        }

        StringBuilder errorField = new StringBuilder();
        if (ValidateUtils.isNullOrEmpty(groupPermissionDto.getId())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Id" : ",Id"));
        }
        if (ValidateUtils.isNullOrEmpty(groupPermissionDto.getGroup_id())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "GroupId" : ",GroupId"));
        }
        if (ValidateUtils.isNullOrEmpty(groupPermissionDto.getPermission())) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Permission" : ",Permission"));
        }
        if (ValidateUtils.isNullOrEmpty(groupPermissionDto.getValue()) && ValidateUtils.isFullWidthDigit(String.valueOf(groupPermissionDto.getValue()))) {
            errorField.append((ValidateUtils.isNullOrEmpty(errorField) ? "Value" : ",Value"));
            errorField.append(ValidateUtils.isFullWidthDigit(errorField.toString()) ? "Value" : ",Value");
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}

