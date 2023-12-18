package tech.eddie.pshop.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.eddie.pshop.entity.dao.Address;
import tech.eddie.pshop.entity.dao.mapper.SalesMapper;
import tech.eddie.pshop.vo.BaseRes;
import tech.eddie.pshop.vo.Req4Settle;
import tech.eddie.pshop.entity.dao.CartItem;
import tech.eddie.pshop.entity.dao.Consumer;
import tech.eddie.pshop.entity.dao.Log;
import tech.eddie.pshop.service.ConsumerService;

import java.sql.Timestamp;
import java.util.HashMap;

@RestController
@RequestMapping("api/consumer")
public class ConsumerController {
    @Autowired
    ConsumerService consumerService;

    @Resource
    SalesMapper salesMapper;

    @PostMapping("/register")
    public BaseRes register(@RequestBody Consumer consumer) {
        BaseRes res = new BaseRes();
        res.setData(new HashMap<String, String>() {{
            put("consumerId", consumerService.register(consumer));
        }});
        res.setCode(0);
        res.setMsg("注册成功");
        return res;
    }

    @PostMapping("/login")
    public BaseRes login(@RequestBody HashMap<String, String> map) {
        BaseRes res = new BaseRes();
        var consumer = consumerService.login(map.get("phone"), map.get("password"));
        if (consumer == null) {
            res.setCode(1);
            res.setMsg("登录失败");
            return res;
        }
        res.setData(consumer);
        res.setCode(0);
        res.setMsg("登录成功");
        return res;
    }

    @GetMapping("/list_cart")
    public BaseRes listCart(@RequestParam String consumerId) {
        BaseRes res = new BaseRes();
        res.setData(consumerService.listCart(consumerId));
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }

    @PostMapping("/add_cart")
    public BaseRes addCart(@RequestBody CartItem cartItem) {
        BaseRes res = new BaseRes();
        consumerService.addCart(cartItem);
        res.setCode(0);
        res.setMsg("添加成功");
        return res;
    }

    @PostMapping("/settle")
    public BaseRes settle(@RequestBody Req4Settle req4Settle) {
        BaseRes res = new BaseRes();
        consumerService.settle(req4Settle);
        res.setCode(0);
        res.setMsg("结算成功");
        return res;
    }

    @PostMapping("/add_log")
    public BaseRes addLog(@RequestBody HashMap<String, String> map) {
        BaseRes res = new BaseRes();
        consumerService.insertLog(new Log(null, map.get("uid"), map.get("pid"), map.get("type").charAt(0), new Timestamp(System.currentTimeMillis())));
        res.setCode(0);
        res.setMsg("添加成功");
        return res;
    }

    @GetMapping("/list_log")
    public BaseRes listLog(@RequestParam String consumerId, @RequestParam Character type) {
        BaseRes res = new BaseRes();
        res.setData(consumerService.listLog(consumerId, type));
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }

    @GetMapping("/list_order")
    public BaseRes listOrder(@RequestParam String consumerId) {
        BaseRes res = new BaseRes();
        res.setData(salesMapper.selectByConsumerId(consumerId));
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }

    @PostMapping("/recv_info/add")
    public BaseRes addRecvInfo(@RequestBody Address address) {
        BaseRes res = new BaseRes();
        consumerService.addAddress(address);
        res.setCode(0);
        res.setMsg("添加成功");
        return res;
    }

    @DeleteMapping("/recv_info/delete")
    public BaseRes deleteRecvInfo(@RequestParam Integer addressId) {
        BaseRes res = new BaseRes();
        consumerService.deleteAddress(addressId);
        res.setCode(0);
        res.setMsg("删除成功");
        return res;
    }

    @PutMapping("/recv_info/edit")
    public BaseRes updateRecvInfo(@RequestBody Address address) {
        BaseRes res = new BaseRes();
        consumerService.updateAddress(address);
        res.setCode(0);
        res.setMsg("更新成功");
        return res;
    }

    @GetMapping("/recv_info/list")
    public BaseRes listRecvInfo(@RequestParam String consumerId) {
        BaseRes res = new BaseRes();
        res.setData(consumerService.listAddress(consumerId));
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }
}
