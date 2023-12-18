package tech.eddie.pshop.service;

import tech.eddie.pshop.entity.view.Res2Sales;
import tech.eddie.pshop.entity.dao.Merchant;

import java.util.List;

public interface MerchantService {
    /**
     * 商家注册
     *
     * @param merchant 商家信息
     * @return 商家id
     */
    Integer register(Merchant merchant);

    Merchant login(String phone, String password);

    List<Res2Sales> getSalesReport(Integer merchantId, Long startTime, Long endTime);
}
