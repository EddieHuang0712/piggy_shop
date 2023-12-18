package tech.eddie.pshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.eddie.pshop.service.MerchantService;
import tech.eddie.pshop.vo.BaseRes;
import tech.eddie.pshop.entity.dao.Merchant;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("api/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    /**
     * 商家注册
     */
    @PostMapping("/register")
    public BaseRes register(@RequestBody Merchant merchant) {
        BaseRes res = new BaseRes();
        Integer merchantId = merchantService.register(merchant);
        if(merchantId==-1){
            res.setCode(-1);
            res.setMsg("注册失败，号码已被注册");
            return res;
        }
        res.setData(new HashMap<String, Integer>() {{
            put("merchantId", merchantId);
        }});
        res.setCode(0);
        res.setMsg("注册成功");
        return res;
    }

    /**
     * 商家登录
     */
    @PostMapping("/login")
    public BaseRes login(@RequestBody Merchant merchant) {
        BaseRes res = new BaseRes();
        Merchant merchant1 = merchantService.login(merchant.getPhone(), merchant.getPassword());
        if (Objects.isNull(merchant1)) {
            res.setCode(1);
            res.setMsg("登录失败");
            return res;
        } else {
            res.setData(merchant1);
        }
        res.setCode(0);
        res.setMsg("登录成功");
        return res;
    }

    /**
     * 获取销售统计报表
     */
    @GetMapping("/list_statistic")
    public BaseRes getSalesReport(@RequestParam("merchantId") Integer merchantId,
                                  @RequestParam("startTime") Long startTime,
                                  @RequestParam("endTime") Long endTime) {
        BaseRes res = new BaseRes();
        res.setData(new HashMap<String, Object>() {{
            put("salesReport", merchantService.getSalesReport(merchantId, startTime, endTime));
        }});
        res.setCode(0);
        res.setMsg("查询成功");
        return res;
    }
}
