<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6">
        <el-input v-model="searchParams.name" class="filter-item" placeholder="请输入商户名称查询" />
      </el-col>
      <el-col :span="12">
        <el-button class="filter-item" type="primary" @click="handleSearch">查询</el-button>
        <el-button class="filter-item" type="primary" @click="handleAdd">新增</el-button>
      </el-col>
    </el-row>
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
      <el-table-column align="center" label="商户ID" prop="id" />
      <el-table-column align="center" label="商户名称" prop="name" />
      <el-table-column align="center" label="商户logo" prop="logo" />
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleStores(scope.$index, scope.row)">门店</el-dropdown-item>
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
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
    >
      <el-form ref="merchantForm" :model="merchant" :rules="rules" label-width="120px">
        <el-form-item label="商户名称" prop="name">
          <el-input v-model="merchant.name" />
        </el-form-item>
        <el-form-item label="商户logo" prop="logo">
          <el-input v-model="merchant.logo" />
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="handleConfirm()">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { searchMerchants, addMerchant, editMerchant, removeMerchant } from '@/api/huiduoduo/merchant-api'

const defaultMerchant = {
  name: '',
  logo: ''
}

export default {
  name: 'Merchant',
  data() {
    return {
      list: null,
      listLoading: true,
      searchParams: {
        name: ''
      },
      page: {
        total: 0,
        currentPage: 1,
        pageSize: 10
      },
      dialogVisible: false,
      dialogTitle: '',
      merchant: Object.assign({}, defaultMerchant),
      rules: {
        name: [
          { required: true, message: '请输入商户名称', trigger: 'blur' }
        ],
        logo: [
          { required: true, message: '请输入商户logo', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      searchMerchants(this.page.currentPage, this.page.pageSize, this.searchParams).then(response => {
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
      this.merchant = Object.assign({}, defaultMerchant)
      this.dialogTitle = '添加商户'
      this.dialogVisible = true
    },
    handleEdit(index, row) {
      this.merchant = row
      this.dialogTitle = '编辑商户'
      this.dialogVisible = true
    },
    handleDelete(index, row) {
      this.$confirm('是否要删除该商户', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeMerchant(row.id).then(response => {
          this.$message({
            message: '删除成功',
            type: 'success',
            duration: 1000
          })
          this.fetchData()
        })
      })
    },
    handleConfirm() {
      this.$refs['merchantForm'].validate((valid) => {
        if (valid) {
          if (this.dialogTitle === '添加商户') {
            addMerchant(this.merchant).then(response => {
              this.$message({
                message: '添加成功',
                type: 'success',
                duration: 1000
              })
              this.dialogVisible = false
              this.fetchData()
            })
          } else {
            editMerchant(this.merchant.id, this.merchant).then(response => {
              this.$message({
                message: '修改成功',
                type: 'success',
                duration: 1000
              })
              this.dialogVisible = false
              this.fetchData()
            })
          }
        }
      })
    },
    handleStores(index, row) {
      this.$router.push({ path: '/huiduoduo/merchants/stores', query: { merchantId: row.id }})
    }
  }
}
</script>
