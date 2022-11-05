package com.openedsource.pizzastore.service;

import com.openedsource.pizzastore.database.entity.GroupPermissionEntity;
import com.openedsource.pizzastore.database.repository.GroupPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupPermissionService {
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;

    public void insertGroupPermission(GroupPermissionEntity groupPermissionEntity) {

        Optional<GroupPermissionEntity> groupPermission = groupPermissionRepository.findById(groupPermissionEntity.getId());
        if (groupPermission.isPresent()) {
            throw new DuplicateKeyException(null);
        }
        groupPermissionRepository.save(groupPermissionEntity);
    }

    public void updateGroupPermission(GroupPermissionEntity groupPermissionEntity) {
        groupPermissionRepository.save(groupPermissionEntity);
    }
}
