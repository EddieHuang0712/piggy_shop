<script setup>
import { ref } from 'vue';
import {getSalesReportApi} from '@/apis/merchant';
import {useMerchantStore} from "@/stores/merchantStore";
import {onMounted,computed} from "vue";
import {timeFormat} from "@/utils/timeUtil";

const merchantStore = useMerchantStore();

const salesList = ref([
  {
    merchantId: '',
    count: '',
    sum: '',
  },
]);

// Function to fetch product data
const fetchSales = async () => {
  // Add your logic here to fetch product data
  const startTime = 0;
  const endTime = new Date() * 1000;
  const res = await getSalesReportApi({ merchantId: merchantStore.merchantInfo.merchantId,startTime,endTime });
  salesList.value = res.data.salesReport.map(item => {
    return {
      merchantId: item.merchantId,
      count: item.count,
      sum: item.sum,
      createTime: timeFormat(item.createTime),
      product: item.product,
    }
  });
};

onMounted(() => fetchSales());

// 计算总销售额
const totalSales = computed(() => {
  let total = 0;
  salesList.value.forEach(item => {
    total += item.sum;
  });
  return total;
});

//计算今日销售额
const todaySales = computed(() => {
  let total = 0;
  //根据时间戳判断是否为今日
  salesList.value.forEach(item => {
    if (new Date(item.createTime).getDate() === new Date().getDate() &&
      new Date(item.createTime).getMonth() === new Date().getMonth() &&
      new Date(item.createTime).getFullYear() === new Date().getFullYear()
    ) {
      total += item.sum;
    }
  });
  return total;
});

// 计算本月销售额
const monthSales = computed(() => {
  let total = 0;
  //根据时间戳判断是否为本月
  salesList.value.forEach(item => {
    if (new Date(item.createTime).getMonth() === new Date().getMonth()) {
      total += item.sum;
    }
  });
  return total;
});

// 计算本年销售额
const yearSales = computed(() => {
  let total = 0;
  //根据时间戳判断是否为本年
  salesList.value.forEach(item => {
    if (new Date(item.createTime).getFullYear() === new Date().getFullYear()) {
      total += item.sum;
    }
  });
  return total;
});

</script>

<template>
  <div class="sales">
    <div class="sales-container">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>销售数据</span>
        </div>
        <el-row>
          <el-col :span="12">
            <el-card>
              <div class="text item">
                <h3>今日销售额</h3><br/>
                <span class="number">￥{{ todaySales.toFixed(2) }}</span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card>
              <div class="text item">
                <h3>本月销售额</h3><br/>
                <span class="number">￥{{ monthSales.toFixed(2) }}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-card>
              <div class="text item">
                <h3>本年销售额</h3><br/>
                <span class="number">￥{{ yearSales.toFixed(2) }}</span>
              </div>
            </el-card>
          </el-col>
          <el-col :span="12">
            <el-card>
              <div class="text item">
                <h3>总销售额</h3><br/>
                <span class="number">￥{{ totalSales.toFixed(2) }}</span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-card>
    </div>
    <div class="detail">
      <h2>销售详情</h2>
    </div>
    <el-table :data="salesList" class="product-table" style="width: 100%">
      <el-table-column prop="product.productId" label="商品ID" width="250">
      </el-table-column>
      <el-table-column prop="product.name" label="商品名称" width="300">
      </el-table-column>
      <el-table-column prop="count" label="销量" width="200">
      </el-table-column>
      <el-table-column prop="sum" label="销售额" width="200">
      </el-table-column>
      <el-table-column prop="createTime" label="销售时间" width="400">
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>
.sales {
  height: 80vh;
}
.sales-container {
  padding: 0 2rem;
}
.detail {
  padding: 0 2rem;
}
.demo-form-inline el-input {
  --el-input-width: 220px;
}
.product-table {
  padding: 1rem 2rem;
  height: 40%;
}

h2 {
  color: #606266;
}

.box-card {
  width: 70%;
  margin-top: 20px;
  margin-bottom: 20px;
  border-radius: 4px;
  background-color: #fff;
  border: 1px solid #dcdfe6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -webkit-box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

  .clearfix {
    padding: 5px 10px 10px;
    font-size: 20px;
    color: #606266;
    font-weight: 600;
    line-height: 1;
    text-align: left;
    border-bottom: 1px solid #ebeef5;
  }

  .el-row {
    gap: 10px;
    flex-wrap: nowrap;
  }

  .el-col {
    flex: 1;
    margin-top: 15px;
  }

  .item {
    text-align: center;
  }
}
</style>
