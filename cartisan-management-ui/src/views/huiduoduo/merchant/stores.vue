<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col>
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
      <el-table-column align="center" label="门店ID" prop="id" />
      <el-table-column align="center" label="商户" prop="merchantName" />
      <el-table-column align="center" label="名称" prop="name" />
      <el-table-column align="center" label="电话" prop="phone" />
      <el-table-column align="center" label="区域" prop="area" />
      <el-table-column align="center" label="排序" prop="sort" />
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-dropdown split-button @click="handleEdit(scope.$index, scope.row)">
            编辑
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
    >
      <el-form ref="storeForm" :model="store" :rules="rules" label-width="120px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="store.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="store.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="所属区域" prop="area">
          <el-input v-model="store.area" placeholder="请输入所属区域" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="store.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="门店指引" prop="description">
          <el-input v-model="store.description" type="textarea" placeholder="请输入门店指引" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="store.sort" />
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
import { getAllStores, addStore, editStore, removeStore } from '@/api/huiduoduo/store-api'

const defaultStore = {
  name: '',
  phone: '',
  area: '',
  address: '',
  description: '',
  sort: 0
}

export default {
  name: 'Stores',
  data() {
    return {
      list: null,
      listLoading: true,
      dialogVisible: false,
      dialogTitle: '',
      store: Object.assign({ }, defaultStore),
      rules: {
        name: [
          { required: true, message: '请输入门店名称', trigger: 'blur' }
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
      getAllStores(this.$route.query.merchantId).then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleAdd() {
      this.store = Object.assign({}, defaultStore)
      this.dialogTitle = '添加门店'
      this.dialogVisible = true
    },
    handleEdit(index, row) {
      this.store = row
      this.dialogTitle = '编辑门店'
      this.dialogVisible = true
    },
    handleDelete(index, row) {
      this.$confirm('是否要删除该门店', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeStore(this.$route.query.merchantId, row.id).then(response => {
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
      this.$refs['storeForm'].validate((valid) => {
        if (valid) {
          if (this.dialogTitle === '添加门店') {
            addStore(this.$route.query.merchantId, this.store).then(response => {
              this.$message({
                message: '添加成功',
                type: 'success',
                duration: 1000
              })
              this.dialogVisible = false
              this.fetchData()
            })
          } else {
            editStore(this.$route.query.merchantId, this.store.id, this.store).then(response => {
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
    }
  }
}
</script>
