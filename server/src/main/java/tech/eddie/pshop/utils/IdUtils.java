package tech.eddie.pshop.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IdUtils {
    private static final Snowflake CONSUMERID_SNOWFLAKE = IdUtil.getSnowflake(1, 1);
    private static final Snowflake PRODUCT_SNOWFLAKE = IdUtil.getSnowflake(2, 1);


    public static Long genConsumerId() {
        return CONSUMERID_SNOWFLAKE.nextId();
    }

    public static Long genProductId() {
        return PRODUCT_SNOWFLAKE.nextId();
    }

}
