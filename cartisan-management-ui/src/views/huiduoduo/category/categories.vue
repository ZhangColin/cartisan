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
      <el-table-column align="center" label="分类ID" prop="id" />
      <el-table-column align="center" label="分类名称" prop="name" />
      <el-table-column align="center" label="分类图标" prop="icon" />
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
      <el-form ref="categoryForm" :model="category" :rules="rules" label-width="120px">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="category.name" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="分类图标" prop="icon">
          <el-input v-model="category.icon" placeholder="请输入分类图标" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="category.sort" placeholder="请设置分类排序" />
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
import { getAllCategories, addCategory, editCategory, removeCategory } from '@/api/huiduoduo/category-api'

const defaultCategory = {
  name: '',
  icon: '',
  sort: 0
}

export default {
  name: 'Categories',
  data() {
    return {
      list: null,
      listLoading: true,
      dialogVisible: false,
      dialogTitle: '',
      category: Object.assign({}, defaultCategory),
      rules: {
        name: [
          { required: true, message: '请输入分类名称', trigger: 'blur' }
        ],
        icon: [
          { required: true, message: '请输入分类图标', trigger: 'blur' }
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
      getAllCategories().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleAdd() {
      this.category = Object.assign({}, defaultCategory)
      this.dialogTitle = '添加分类'
      this.dialogVisible = true
    },
    handleEdit(index, row) {
      this.category = row
      this.dialogTitle = '编辑分类'
      this.dialogVisible = true
    },
    handleDelete(index, row) {
      this.$confirm('是否要删除该分类', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeCategory(row.id).then(response => {
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
      this.$refs['categoryForm'].validate((valid) => {
        if (valid) {
          if (this.dialogTitle === '添加分类') {
            addCategory(this.category).then(response => {
              this.$message({
                message: '添加成功',
                type: 'success',
                duration: 1000
              })
              this.dialogVisible = false
              this.fetchData()
            })
          } else {
            editCategory(this.category.id, this.category).then(response => {
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
