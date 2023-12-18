package tech.eddie.pshop.service.serviceImpl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.eddie.pshop.entity.view.Res2Sales;
import tech.eddie.pshop.utils.PasswordUtil;
import tech.eddie.pshop.entity.dao.Merchant;
import tech.eddie.pshop.entity.dao.mapper.LogMapper;
import tech.eddie.pshop.entity.dao.mapper.MerchantMapper;
import tech.eddie.pshop.entity.dao.mapper.SalesMapper;
import tech.eddie.pshop.service.MerchantService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    LogMapper logMapper;

    @Resource
    SalesMapper salesMapper;

    @Override
    public Integer register(Merchant merchant) {
        merchant.setPassword(PasswordUtil.encryptPassword(merchant.getPassword()));
        try {
            merchantMapper.insert(merchant);
        } catch (Exception e){
            return -1;
        }

        return merchantMapper.selectByPhone(merchant.getPhone()).getMerchantId();
    }

    @Override
    public Merchant login(String phone, String password) {
        Merchant merchant = merchantMapper.selectByPhone(phone);
        if (merchant == null) {
            return null;
        }
        if (PasswordUtil.verifyPassword(password, merchant.getPassword())) {
            return merchant;
        }
        return null;
    }

    @Override
    public List<Res2Sales> getSalesReport(Integer merchantId, Long startTime, Long endTime) {
        Timestamp startTimeStamp = new Timestamp(startTime);
        Timestamp endTimeStamp = new Timestamp(endTime);
        return salesMapper.selectByMerchantId(merchantId);
    }

}
