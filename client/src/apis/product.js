import httpInstance from "@/apis/http";
/**
 * 获取首页推荐商品
 * @param pageNum
 * @param pageSize
 */
export const getHomeProductsApi = ({ pageNum, pageSize }) => {
    return httpInstance({
        url: '/product/recommend',
        params: {
            pageNum,
            pageSize
        }
    })
}

/**
 * 添加商品
 * @param merchantId
 * @param name
 * @param price
 * @param stock
 * @param description
 * @param coverUrl
 */
export const addProductApi = ({ merchantId, name, price, stock, description, coverUrl }) => {
    return httpInstance({
        url: '/product/add',
        method: 'post',
        data: {
            merchantId,
            name,
            price,
            stock,
            description,
            coverUrl
        }
    })
}

/**
 * 获取商品详情
 * @param productId
 */
export const getProductDetailApi = ({ productId }) => {
    return httpInstance({
        url: `/product/detail/${productId}`,
    })
}

/**
 * 修改商品
 * @param merchantId
 * @param productId
 * @param name
 * @param price
 * @param stock
 * @param description
 * @param coverUrl
 */

export const updateProductApi = ({ merchantId, productId, name, price, stock, description, coverUrl }) => {
    return httpInstance({
        url: '/product/update',
        method: 'put',
        data: {
            merchantId,
            productId,
            name,
            price,
            stock,
            description,
            coverUrl
        }
    })
}

/**
 * 删除商品
 * @param productId
 */
export const deleteProductApi = ({ productId }) => {
    return httpInstance({
        url: `/product/delete`,
        method: 'delete',
        params: {
            productId
        }
    })
}

/**
 * 获取商家所有商品
 * @param merchantId
 */
export const getProductsApi = ({ merchantId }) => {
    return httpInstance({
        url: `/product/list/${merchantId}`,
    })
}
