<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6">
        <el-input v-model="searchParams.name" class="filter-item" placeholder="请输入名称查询" />
      </el-col>
      <template v-if="toggleSearchStatus">
        <el-col :span="6">
          <el-input v-model="searchParams.title" class="filter-item" placeholder="请输入标题查询" />
        </el-col>
        <el-col :span="6">
          <el-select v-model="searchParams.merchantId" placeholder="请选择商户" class="filter-item" clearable style="width: 100%">
            <el-option v-for="merchant in merchants" :key="merchant.id" :label="merchant.name" :value="merchant.id" />
          </el-select>
          <!--        <el-input v-model="searchParams.merchantId" class="filter-item" placeholder="请输入商户Id" />-->
        </el-col>
        <el-col :span="6">
          <el-select v-model="searchParams.categoryId" placeholder="请选择分类" class="filter-item" clearable style="width: 100%">
            <el-option v-for="category in categories" :key="category.id" :label="category.name" :value="category.id" />
          </el-select>
        </el-col>
        <el-col :span="6">
          <el-select
            v-model="searchParams.type"
            class="filter-item"
            placeholder="请输入类型查询"
            clearable
            style="width: 100%"
          >
            <el-option label="公码" value="1" />
            <el-option label="私码" value="2" />
          </el-select>
        </el-col>
      </template>
      <el-col :span="12">
        <el-button class="filter-item" type="primary" @click="handleSearch">查询</el-button>
        <el-button class="filter-item" type="primary" @click="searchParams={}">重置</el-button>
        <el-button class="filter-item" type="text" @click="toggleSearchStatus=!toggleSearchStatus">
          {{ toggleSearchStatus?'收起':'展开' }}
          <i :class="toggleSearchStatus?'el-icon-arrow-up':'el-icon-arrow-down'" class="el-icon--right" />
        </el-button>
      </el-col>
    </el-row>
    <div class="filter-container">
      <el-button type="primary" class="filter-item" @click="handleAdd()">新增</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      row-key="id"
      class="table-container"
      element-loading-text="加载中..."
      stripe
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="模板ID" prop="id" />
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="商户" prop="merchantName" />
      <el-table-column align="center" label="分类" prop="categoryName" />
      <el-table-column align="center" label="标题" prop="title" />
      <el-table-column align="center" label="类型" prop="type">
        <template slot-scope="scope">
          <span>{{ scope.row.type===1?'公码':'私码' }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="领取时间" prop="getStart">
        <template slot-scope="scope">
          <div>{{ scope.row.getStart | parseTime('{y}-{m}-{d}') }}</div>
          <div>至</div>
          <div>{{ scope.row.getEnd | parseTime('{y}-{m}-{d}') }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="有效时间" prop="validStart">
        <template slot-scope="scope">
          <div>{{ scope.row.validStart | parseTime('{y}-{m}-{d}') }}</div>
          <div>至</div>
          <div>{{ scope.row.validEnd | parseTime('{y}-{m}-{d}') }}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="佣金" prop="commission">
        <template slot-scope="scope">
          <div>{{ scope.row.commission }}%</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleStoreGuides(scope.$index, scope.row)">门店指南</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page.sync="page.currentPage"
      :page-sizes="[5, 10, 20]"
      :page-size="page.pageSize"
      :total="page.total"
      class="pagination-container"
      background
      align="right"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { searchCouponSchemas, removeCouponSchema } from '@/api/huiduoduo/coupon-schema-api'
import { getAllMerchants } from '@/api/huiduoduo/merchant-api'
import { getAllCategories } from '@/api/huiduoduo/category-api'

export default {
  name: 'CouponSchema',
  data() {
    return {
      list: null,
      listLoading: true,
      toggleSearchStatus: false,
      merchants: [],
      categories: [],
      searchParams: {
        merchantId: '',
        categoryId: '',
        name: '',
        title: '',
        type: ''
      },
      page: {
        total: 0,
        currentPage: 1,
        pageSize: 10
      }
    }
  },
  created() {
    Promise.all([getAllMerchants(), getAllCategories()])
      .then(responses => {
        this.merchants = responses[0].data
        this.categories = responses[1].data
        this.fetchData()
      })
  },
  methods: {
    fetchData() {
      this.listLoading = true
      searchCouponSchemas(this.page.currentPage, this.page.pageSize, this.searchParams).then(response => {
        this.list = response.data.rows
        this.page.total = response.data.total
        this.listLoading = false
      })
    },
    handleSearch() {
      this.page.currentPage = 1
      this.fetchData()
    },
    handleSizeChange(pageSize) {
      this.page.currentPage = 1
      this.page.pageSize = pageSize
      this.fetchData()
    },
    handleCurrentChange(currentPage) {
      this.page.currentPage = currentPage
      this.fetchData()
    },
    handleAdd() {
      this.$router.push({ path: '/huiduoduo/coupon-schemas/coupon-schema-add' })
    },
    handleEdit(index, row) {
      this.$router.push({ path: '/huiduoduo/coupon-schemas/coupon-schema-edit', query: { id: row.id }})
    },
    handleDelete(index, row) {
      this.$confirm('是否要删除该优惠券模板', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeCouponSchema(row.id).then(response => {
          this.$message({
            message: '删除成功',
            type: 'success',
            duration: 1000
          })
          this.fetchData()
        })
      }).catch(() => {
      })
    },
    handleStoreGuides(index, row) {
      this.$router.push({ path: '/huiduoduo/coupon-schemas/store-guides', query: { couponSchemaId: row.id }})
    }
  }
}
</script>
