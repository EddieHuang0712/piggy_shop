<script setup>
import { ref } from 'vue';
import {addProductApi, deleteProductApi, updateProductApi, getProductsApi} from '@/apis/product';
import {useMerchantStore} from "@/stores/merchantStore";
import {onMounted} from "vue";
import {ElMessage} from "element-plus";

const addFlag = ref(false);
const editFlag = ref(false);

const product = ref({
  name: '',
  coverUrl: '',
  category: '',
  price: '',
  stock: '',
});

const newProduct = ref({
  name: '',
  coverUrl: '',
  category: '',
  price: '',
  stock: '',
});

const merchantStore = useMerchantStore();

const productList = ref([]);

// Function to add a new product
const addProduct = async (product) => {
  // Add your logic here to handle adding a new product
  await addProductApi({ ...product, merchantId: merchantStore.merchantInfo.merchantId });
  ElMessage.success('添加成功');
  addFlag.value = false;
  newProduct.value = {
    name: '',
    coverUrl: 'data:image/jpeg;base64,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',
    category: '',
    price: '',
    stock: '',
  };
  await fetchProducts()
};

// Function to delete a product
const deleteProduct = async (productId) => {
  // Add your logic here to handle deleting a product
  await deleteProductApi({ productId });
  await fetchProducts()
};

// Function to update a product
const updateProduct = async (product) => {
  // Add your logic here to handle updating a product
  await updateProductApi({ ...product, merchantId: merchantStore.merchantInfo.merchantId });
  ElMessage.success('修改成功');
  editFlag.value = false;
};

// Function to fetch product data
const fetchProducts = async () => {
  // Add your logic here to fetch product data
  const res = await getProductsApi({ merchantId: merchantStore.merchantInfo.merchantId });
  productList.value = res.data;
};

onMounted(() => fetchProducts());

const onEdit = (row) => {
  console.log(row);
  product.value = row;
  editFlag.value = true;
};
</script>

<template>
  <div class="product-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="addFlag = true">添加商品</el-button>
      </el-form-item>
    </el-form>

    <!-- Display table of products -->
    <el-table :data="productList" class="product-table" style="width: 100%">
      <!-- Define columns for your table -->
      <el-table-column prop="productId" label="商品id" width="250"></el-table-column>
      <el-table-column prop="name" label="商品名" width="200"></el-table-column>
      <el-table-column prop="description" label="商品描述" width="300"></el-table-column>
      <el-table-column prop="coverUrl" label="图片" width="250">
        <template #default="{ row }">
          <img :src="row.coverUrl" alt="" style="width: 100px; height: 100px" />
        </template>
      </el-table-column>
      <el-table-column prop="price" label="价格" width="200"></el-table-column>
      <el-table-column prop="stock" label="存货" width="200"></el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template #default="{ row }">
          <!-- Add buttons for editing and deleting products -->
          <el-button type="primary" size="small" @click="onEdit(row)">修改</el-button>
          <el-button type="danger" size="small" @click="deleteProduct(row.productId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="editFlag" title="修改商品详情" width="30%" center>
      <el-form :model="product" label-width="100px" class="demo-ruleForm">
        <el-form-item label="商品名" prop="name">
          <el-input v-model="product.name"></el-input>
        </el-form-item>
        <el-form-item label="商品描述" prop="description">
          <el-input v-model="product.description"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="coverUrl">
          <el-input v-model="product.coverUrl"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="product.price"></el-input>
        </el-form-item>
        <el-form-item label="存货" prop="stock">
          <el-input v-model="product.stock"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="editFlag = false">取 消</el-button>
      <el-button type="primary" @click="updateProduct(product)">确 定</el-button>
    </span>
    </el-dialog>

    <el-dialog v-model="addFlag" title="添加商品" width="30%" center>
      <el-form :model="newProduct" label-width="100px" class="demo-ruleForm">
        <el-form-item label="商品名" prop="name">
          <el-input v-model="newProduct.name"></el-input>
        </el-form-item>
        <el-form-item label="商品描述" prop="description">
          <el-input v-model="newProduct.description"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="coverUrl">
          <el-input v-model="newProduct.coverUrl"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="newProduct.price"></el-input>
        </el-form-item>
        <el-form-item label="存货" prop="stock">
          <el-input v-model="newProduct.stock"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="addFlag = false">取 消</el-button>
      <el-button type="primary" @click="addProduct(newProduct)">确 定</el-button>
    </span>
    </el-dialog>
  </div>

</template>

<style scoped>
.product-container {
  width: 100%;
  height: 88vh;

  .product-table {
    width: 100%;
    height: 76vh;
    overflow: auto;
  }
}
.demo-form-inline {
  padding: 2rem 2rem;
  float: right;
}
.demo-form-inline el-input {
  --el-input-width: 220px;
}
.product-table {
  padding: 0 2rem;
}
</style>
