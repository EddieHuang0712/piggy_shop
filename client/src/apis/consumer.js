import httpInstance from "@/apis/http";
/**
 * 顾客注册
 * @param username
 * @param phone
 * @param password
 * @param email
 */
export const consumerRegisterApi = ({ username, phone, password, email }) => {
    return httpInstance({
        url: '/consumer/register',
        method: 'post',
        data: {
            username,
            phone,
            password,
            email
        }
    })
}

/**
 * 顾客登录
 * @param phone
 * @param password
 */
export const consumerLoginApi = ({ phone, password }) => {
    return httpInstance({
        url: '/consumer/login',
        method: 'post',
        data: {
            phone,
            password
        }
    })
}

/**
 * 添加购物车
 * @param productId
 * @param consumerId
 * @param count
 * @param sum
 */
export const addCartApi = ({ productId, consumerId, count, sum }) => {
    return httpInstance({
        url: '/consumer/add_cart',
        method: 'post',
        data: {
            productId,
            consumerId,
            count,
            sum
        }
    })
}

/**
 * 获取购物车列表
 * @param consumerId
 */
export const getCartListApi = ({ consumerId }) => {
    return httpInstance({
        url: '/consumer/list_cart',
        params: {
            consumerId
        }
    })
}

/**
 * 商品结算
 * @param consumerId
 * @param addressId
 * @param {orders:[{productId,consumerId,cartItemId,count,sum}]}
 * @param sum
 */
export const settlementApi = ({ consumerId, addressId, orders, sum }) => {
    return httpInstance({
        url: '/consumer/settle',
        method: 'post',
        data: {
            consumerId,
            addressId,
            orders,
            sum
        }
    })
}

// 不确定
/**
 * 获取订单列表
 * @param consumerId
 * @param type
 */
export const getOrderListApi = ({ consumerId, type }) => {
    return httpInstance({
        url: '/consumer/list_log',
        params: {
            consumerId,
            type
        }
    })
}

/**
 * 添加收货信息
 * @param consumerId
 * @param name
 * @param email
 * @param address
 */
export const addAddressApi = ({ consumerId, name, email, address }) => {
    return httpInstance({
        url: '/consumer/recv_info/add',
        method: 'post',
        data: {
            consumerId,
            name,
            email,
            address
        }
    })
}

/**
 * 获取收货信息列表
 * @param consumerId
 */
export const getAddressListApi = ({ consumerId }) => {
    return httpInstance({
        url: '/consumer/recv_info/list',
        params: {
            consumerId
        }
    })
}

/**
 * 修改收货信息
 * @param uid
 * @param addressId
 * @param name
 * @param email
 * @param address
 */
export const updateAddressApi = ({ uid, addressId, name, email, address }) => {
    return httpInstance({
        url: '/consumer/recv_info/edit',
        method: 'put',
        data: {
            uid,
            addressId,
            name,
            email,
            address
        }
    })
}

/**
 * 删除收货信息
 * @param addressId
 */
export const deleteAddressApi = ({ addressId }) => {
    return httpInstance({
        url: '/consumer/recv_info/delete',
        method: 'delete',
        params: {
            addressId
        }
    })
}

/**
 * 记录用户日志
 * @param uid
 * @param pid
 * @param type（0：浏览记录，1：购买记录）
 */
export const addLogApi = ({ uid, pid, type }) => {
    return httpInstance({
        url: '/consumer/add_log',
        method: 'post',
        data: {
            uid,
            pid,
            type
        }
    })
}

/**
 * 获取用户日志列表
 * @param consumerId
 * @param type
 */
export const getLogListApi = ({ consumerId, type }) => {
    return httpInstance({
        url: '/consumer/list_log',
        params: {
            consumerId,
            type
        }
    })
}

/**
 * 获取购物历史
 * @param consumerId
 */
export const getHistoryListApi = ({ consumerId }) => {
    return httpInstance({
        url: '/consumer/list_order',
        params: {
            consumerId
        }
    })
}
