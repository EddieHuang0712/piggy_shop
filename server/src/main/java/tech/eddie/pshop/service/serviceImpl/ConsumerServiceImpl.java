package tech.eddie.pshop.service.serviceImpl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import tech.eddie.pshop.entity.dao.*;
import tech.eddie.pshop.entity.dao.mapper.*;
import tech.eddie.pshop.entity.view.Res2Cart;
import tech.eddie.pshop.utils.IdUtils;
import tech.eddie.pshop.utils.PasswordUtil;
import tech.eddie.pshop.vo.Req4Settle;
import tech.eddie.pshop.entity.view.Res2Log;
import tech.eddie.pshop.service.ConsumerService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerMapper consumerMapper;

    @Autowired
    CartItemMapper cartItemMapper;

    @Autowired
    LogMapper logMapper;

    @Autowired
    AddressMapper addressMapper;

    @Resource
    SalesMapper salesMapper;

    @Resource
    MailSender mailSender;

    @Override
    public String register(Consumer consumer) {
        String id = IdUtils.genConsumerId().toString();
        consumer.setConsumerId(id);
        consumer.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        consumer.setPassword(PasswordUtil.encryptPassword(consumer.getPassword()));
        consumer.setBalance("0");
        consumerMapper.insert(consumer);
        return id;
    }

    @Override
    public Consumer login(String phone, String password) {
        var consumer = consumerMapper.selectByPhone(phone);
        if (consumer == null) {
            return null;
        }
        if (PasswordUtil.verifyPassword(password, consumer.getPassword())) {
            return consumer;
        }
        return null;
    }

    @Override
    public List<Res2Cart> listCart(String consumerId) {
        return cartItemMapper.selectByConsumerId(consumerId);
    }

    @Override
    public void addCart(CartItem cartItem) {
        cartItemMapper.insert(cartItem);
    }

    @Override
    public void settle(Req4Settle req4Settle) {
        var cartItems = req4Settle.getOrders();
        var consumerId = req4Settle.getConsumerId();
        var consumer= consumerMapper.selectByConsumerId(consumerId);
        for (var cartItem : cartItems) {
            cartItemMapper.deleteByCartItemId(cartItem.getCartItemId());
            Log log = new Log(null, consumerId, cartItem.getProductId(), '1', new Timestamp(System.currentTimeMillis()));
            logMapper.insert(log);
            salesMapper.insert(new Sales(null,consumerId,cartItem.getProductId(),cartItem.getCount(),cartItem.getSum()/cartItem.getCount(),cartItem.getSum(),new Timestamp(System.currentTimeMillis())));
        }
        sendEmail(consumer.getEmail(),consumer.getUsername());
    }

    @Override
    public void insertLog(Log log) {
        logMapper.insert(log);
    }

    @Override
    public List<Res2Log> listLog(String consumerId, Character type) {
        if (type == '0') {
            return logMapper.selectBrowseHistory(consumerId);
        } else if (type == '1') {
            return logMapper.selectPurchaseHistory(consumerId, new Timestamp(0), new Timestamp(System.currentTimeMillis()));
        }
        return null;
    }

//    @Override
//    public void selectPurchaseHistory(String consumerId) {
//        Timestamp startTime = new Timestamp(0);
//        Timestamp endTime = new Timestamp(System.currentTimeMillis());
//        logMapper.selectPurchaseHistory(consumerId, startTime, endTime);
//    }

    @Override
    public void addAddress(Address address) {
        addressMapper.insert(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressMapper.updateByAddressId(address);
    }

    @Override
    public void deleteAddress(Integer addressId) {
        addressMapper.deleteByAddressId(addressId);
    }

    @Override
    public List<Address> listAddress(String consumerId) {
        return addressMapper.selectByConsumerId(consumerId);
    }

    public void sendEmail(String email, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("13415143041@163.com");
        message.setTo(email);
        message.setSubject("您在猪头商城的购物订单支付成功");
        String plainText = "尊敬的" + name + "，您的订单已支付成功\n"
                + "感谢您在猪头商城购物！您的订单已成功支付。\n"
                + "您可以随时登录猪头商城查看订单详情。\n"
                + "如有任何问题，请随时与我们联系。谢谢！";
        message.setText(plainText);
        mailSender.send(message);
    }
}
