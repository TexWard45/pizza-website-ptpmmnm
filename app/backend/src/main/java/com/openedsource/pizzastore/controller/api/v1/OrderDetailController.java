package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.OrderDetailEntity;
import com.openedsource.pizzastore.database.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orderdetail")
@CrossOrigin(origins = "*")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public ResponseEntity<List<OrderDetailEntity>> getOrderDetailList() {
        List<OrderDetailEntity> orderDetailList = orderDetailRepository.findAll();
        return ResponseEntity.ok().body(orderDetailList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderDetail(@PathVariable(name = "id") Integer id) {
        Optional<OrderDetailEntity> orderDetail = orderDetailRepository.findById(id);
        return ResponseEntity.ok().body(orderDetail.get());
    }
}
