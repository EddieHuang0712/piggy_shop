import httpInstance from '@/apis/http'
/**
 * 商家注册
 * @param shopName
 * @param phone
 * @param password
 */
export const merchantRegisterApi = ({ name, phone, password }) => {
    return httpInstance({
        url: '/merchant/register',
        method: 'post',
        data: {
            name,
            phone,
            password
        }
    })
}

/**
 * 商家登录
 * @param phone
 * @param password
 */
export const merchantLoginApi = ({ phone, password }) => {
    return httpInstance({
        url: '/merchant/login',
        method: 'post',
        data: {
            phone,
            password
        }
    })
}

/**
 * 获取销售统计报表
 * @param merchantId
 * @param startTime
 * @param endTime
 */
export const getSalesReportApi = ({ merchantId, startTime, endTime }) => {
    return httpInstance({
        url: '/merchant/list_statistic',
        params: {
            merchantId,
            startTime,
            endTime
        }
    })
}
