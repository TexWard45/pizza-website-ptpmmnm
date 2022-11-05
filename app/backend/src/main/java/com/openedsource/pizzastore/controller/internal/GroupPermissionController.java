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
@RequestMapping("internal")
@CrossOrigin(origins = "*")
public class GroupPermissionController {
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;
    @Autowired
    private GroupPermissionService groupPermissionService;

    @PostMapping("/grouppermission/add")
    public ResponseEntity<Object> addGroupPermission(@RequestBody(required = false) GroupPermissionDto groupPermissionDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.CREATED).build();
        String message = inputcheck(groupPermissionDto);

        if (ValidateUtils.isNullOrEmpty(message)) {
            GroupPermissionEntity groupPermissionEntity = new GroupPermissionEntity();
            BeanUtils.copyProperties(groupPermissionDto, groupPermissionEntity);

            try {
                groupPermissionService.insertGroupPermission(groupPermissionEntity);
            } catch (DuplicateKeyException e) {
                response = ResponseUtils.buildMessageReponse(HttpStatus.CONFLICT, Constants.MessageString.CONFLICT_ERROR.getMessage());
            }
        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @PutMapping("/grouppermission/update")
    public ResponseEntity<Object> updateGroupPermission(@RequestBody GroupPermissionDto groupPermissionDto) {
        ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).build();
        String message = inputcheck(groupPermissionDto);
        
        if (ValidateUtils.isNullOrEmpty(message)) {
            GroupPermissionEntity groupPermissionEntity = new GroupPermissionEntity();
            BeanUtils.copyProperties(groupPermissionDto, groupPermissionEntity);

            groupPermissionService.updateGroupPermission(groupPermissionEntity);

        } else {
            response = ResponseUtils.buildMessageReponse(HttpStatus.BAD_REQUEST, message);
        }
        return response;
    }

    @DeleteMapping("/grouppermission/delete/{id}")
    public ResponseEntity<?> deleteGroupPermission(@PathVariable("id") Integer id) {
        groupPermissionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
        }

        String message = "";
        if (!ValidateUtils.isNullOrEmpty((errorField.toString()))) {
            message = Constants.MessageString.PARAMETER_ERROR.getMessage() + "[" + errorField.toString() + "]";
        }
        return message;
    }
}

