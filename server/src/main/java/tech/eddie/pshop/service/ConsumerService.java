package tech.eddie.pshop.service;

import tech.eddie.pshop.entity.dao.Address;
import tech.eddie.pshop.entity.view.Res2Cart;
import tech.eddie.pshop.entity.view.Res2Log;
import tech.eddie.pshop.vo.Req4Settle;
import tech.eddie.pshop.entity.dao.CartItem;
import tech.eddie.pshop.entity.dao.Consumer;
import tech.eddie.pshop.entity.dao.Log;

import java.util.List;

public interface ConsumerService {
    /**
     * 买家注册
     */
    String register(Consumer consumer);

    Consumer login(String phone, String password);

    List<Res2Cart> listCart(String consumerId);

    void addCart(CartItem cartItem);

    void settle(Req4Settle req4Settle);

    void insertLog(Log log);
    

    List<Res2Log> listLog(String consumerId, Character type);


    void addAddress(Address address);

    void updateAddress(Address address);

    void deleteAddress(Integer addressId);

    List<Address> listAddress(String consumerId);
}
