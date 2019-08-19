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
      <el-table-column align="center" label="门店ID" prop="storeId" />
      <el-table-column align="center" label="门店名称" prop="storeName" />
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
      <el-form ref="storeGuideForm" :model="storeGuide" :rules="rules" label-width="120px">
        <el-form-item label="门店Id" prop="storeId">
          <el-input v-model="storeGuide.storeId" placeholder="请输入门店Id" />
        </el-form-item>
        <el-form-item label="门店指南" prop="guide">
          <el-input v-model="storeGuide.guide" type="textarea" placeholder="请输入门店指南" />
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
import { getAllStoreGuides, addStoreGuide, editStoreGuide, removeStoreGuide } from '@/api/huiduoduo/store-guide-api'

const defaultStoreGuide = {
  storeId: '',
  guide: ''
}

export default {
  name: 'StoreGuides',
  data() {
    return {
      list: null,
      listLoading: true,
      dialogVisible: false,
      dialogTitle: '',
      storeGuide: Object.assign({ }, defaultStoreGuide),
      rules: {
        storeId: [
          { required: true, message: '请输入门店Id', trigger: 'blur' }
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
      getAllStoreGuides(this.$route.query.couponSchemaId).then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleAdd() {
      this.storeGuide = Object.assign({}, defaultStoreGuide)
      this.storeGuide.couponSchemaId = this.$route.query.couponSchemaId
      this.dialogTitle = '添加门店指南'
      this.dialogVisible = true
    },
    handleEdit(index, row) {
      this.storeGuide = row
      this.dialogTitle = '编辑门店指南'
      this.dialogVisible = true
    },
    handleDelete(index, row) {
      this.$confirm('是否要删除该门店指南', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeStoreGuide(this.$route.query.couponSchemaId, row.id).then(response => {
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
      this.$refs['storeGuideForm'].validate((valid) => {
        if (valid) {
          if (this.dialogTitle === '添加门店指南') {
            addStoreGuide(this.$route.query.couponSchemaId, this.storeGuide).then(response => {
              this.$message({
                message: '添加成功',
                type: 'success',
                duration: 1000
              })
              this.dialogVisible = false
              this.fetchData()
            })
          } else {
            editStoreGuide(this.$route.query.couponSchemaId, this.storeGuide).then(response => {
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
