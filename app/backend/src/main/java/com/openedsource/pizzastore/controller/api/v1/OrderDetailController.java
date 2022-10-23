package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.OrderDetail;
import com.openedsource.pizzastore.database.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class OrderDetailController {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/orderdetail")
    public ResponseEntity<List<OrderDetail>> getOrderDetailList(){
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
        return ResponseEntity.ok().body(orderDetailList);
    }
    @GetMapping("/orderdetail/{id}")
    public ResponseEntity<Object> getOrderDetail(@PathVariable(name = "id") Integer id){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        return ResponseEntity.ok().body(orderDetail.get());
    }
    @PostMapping("/orderdetail/add")
    public ResponseEntity<OrderDetail> addOrderDetail(@Valid @RequestBody OrderDetail orderdetail) {
        OrderDetail newOrderDetail =  orderDetailRepository.save(orderdetail);
        return new ResponseEntity<>(newOrderDetail, HttpStatus.CREATED);
    }

    @PutMapping("/orderdetail/update")
    public ResponseEntity<OrderDetail> updateOrderDetail(@RequestBody OrderDetail orderdetail){
        OrderDetail updateOrderDetail = orderDetailRepository.save(orderdetail);
        return new ResponseEntity<>(updateOrderDetail, HttpStatus.OK);
    }
    @DeleteMapping("/orderdetail/delete/{id}")
    public ResponseEntity<?> deleteOrderDetail(@PathVariable("id")Integer id) {
        orderDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
