package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.GroupPermission;
import com.openedsource.pizzastore.database.repository.GroupPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class GroupPermissionController {
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;

    @GetMapping("/grouppermission")
    public ResponseEntity<List<GroupPermission>> getGroupPermissionList(){
        List<GroupPermission> groupPermissionList = groupPermissionRepository.findAll();
        return ResponseEntity.ok().body(groupPermissionList);
    }
    @GetMapping("/grouppermission/{id}")
    public ResponseEntity<Object> getGroupPermission(@PathVariable(name = "id") Integer id){
        Optional<GroupPermission> user = groupPermissionRepository.findById(id);
        return ResponseEntity.ok().body(user.get());
    }
    @PostMapping("/base/add")
    public ResponseEntity<GroupPermission> addGroupPermission(@Valid @RequestBody GroupPermission groupPermission) {
        GroupPermission newGroupPermission =  groupPermissionRepository.save(groupPermission);
        return new ResponseEntity<>(newGroupPermission, HttpStatus.CREATED);
    }

    @PutMapping("/base/update")
    public ResponseEntity<GroupPermission> updateGroupPermission(@RequestBody GroupPermission groupPermission){
        GroupPermission updateGroupPermission = groupPermissionRepository.save(groupPermission);
        return new ResponseEntity<>(updateGroupPermission, HttpStatus.OK);
    }
    @DeleteMapping("/base/delete/{id}")
    public ResponseEntity<?> deleteGroupPermission(@PathVariable("id")Integer id) {
        groupPermissionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
