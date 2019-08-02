<template>
  <div class="app-container">
    <el-row :gutter="24" class="filter-container">
      <el-col :span="6">
        <el-input v-model="searchParams.nickName" class="filter-item" placeholder="请输入用户昵称查询" />
      </el-col>
      <el-col :span="12">
        <el-button class="filter-item" type="primary" @click="handleSearch">查询</el-button>
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
      <el-table-column align="center" label="用户ID" prop="id" />
      <el-table-column align="center" label="用户昵称" prop="nickName" />
      <el-table-column align="center" label="头像" prop="avatar" />
      <el-table-column align="center" label="推荐人" prop="referrer" />
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
import { searchWeixinUsers } from '@/api/huiduoduo/weixin-user-api'

export default {
  name: 'WeixinUser',
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
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      searchWeixinUsers(this.page.currentPage, this.page.pageSize, this.searchParams).then(response => {
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
    }
  }
}
</script>
