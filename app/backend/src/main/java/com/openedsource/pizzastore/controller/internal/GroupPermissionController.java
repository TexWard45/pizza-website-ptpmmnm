package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.database.entity.GroupPermission;
import com.openedsource.pizzastore.database.repository.GroupPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class GroupPermissionController {
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;

    @PostMapping("/grouppermission/add")
    public ResponseEntity<GroupPermission> addGroupPermission(@RequestBody GroupPermission groupPermission) {
        GroupPermission newGroupPermission =  groupPermissionRepository.save(groupPermission);
        return new ResponseEntity<>(newGroupPermission, HttpStatus.CREATED);
    }

    @PutMapping("/grouppermission/update")
    public ResponseEntity<GroupPermission> updateGroupPermission(@RequestBody GroupPermission groupPermission){
        GroupPermission updateGroupPermission = groupPermissionRepository.save(groupPermission);
        return new ResponseEntity<>(updateGroupPermission, HttpStatus.OK);
    }

    @PutMapping("/grouppermission/update/{id}")
    public ResponseEntity<GroupPermission> updateGroup(@RequestBody GroupPermission group,@PathVariable("id")Integer id){
        Optional<GroupPermission> groupPermissionOptional = groupPermissionRepository.findById(id);
        if(groupPermissionOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        group.setId(id);
        GroupPermission updateGroupPermission = groupPermissionRepository.save(group);
        return new ResponseEntity<>(updateGroupPermission,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/grouppermission/delete/{id}")
    public ResponseEntity<?> deleteGroupPermission(@PathVariable("id")Integer id) {
        groupPermissionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

