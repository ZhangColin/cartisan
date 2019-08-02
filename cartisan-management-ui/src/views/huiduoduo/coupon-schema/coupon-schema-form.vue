<template>
  <div class="app-container">
    <el-form ref="couponSchemaForm" :model="couponSchema" :rules="rules" label-width="150px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="couponSchema.name" />
      </el-form-item>
      <el-form-item label="商户" prop="merchantId">
        <el-input v-model="couponSchema.merchantId" />
      </el-form-item>
      <el-form-item label="分类" prop="categoryId">
        <el-input v-model="couponSchema.categoryId" />
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input v-model="couponSchema.title" />
      </el-form-item>
      <el-form-item label="图片" prop="image">
        <el-input v-model="couponSchema.image" />
      </el-form-item>
      <el-form-item label="基本介绍" prop="introduction">
        <el-input v-model="couponSchema.introduction " type="textarea" />
      </el-form-item>
      <el-form-item label="佣金" prop="commission">
        <el-input-number v-model="couponSchema.commission" />
      </el-form-item>
      <el-form-item label="领取开始时间" prop="getStart">
        <el-date-picker v-model="couponSchema.getStart" type="date" placeholder="请选择领取开始时间" />
      </el-form-item>
      <el-form-item label="领取结束时间" prop="getEnd">
        <el-date-picker v-model="couponSchema.getEnd" type="date" placeholder="请选择领取结束时间" />
      </el-form-item>
      <el-form-item label="有效开始时间" prop="validStart">
        <el-date-picker v-model="couponSchema.validStart" type="date" placeholder="请选择有效开始时间" />
      </el-form-item>
      <el-form-item label="有效结束时间" prop="validEnd">
        <el-date-picker v-model="couponSchema.validEnd" type="date" placeholder="请选择有效结束时间" />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="couponSchema.type">
          <el-radio :label="1">公码</el-radio>
          <el-radio :label="2">私码</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="条形码/二维码图片" prop="codeImage">
        <el-input v-model="couponSchema.codeImage" />
      </el-form-item>
      <el-form-item label="领取方式" prop="getMethod">
        <el-radio-group v-model="couponSchema.getMethod">
          <el-radio :label="1">一人一张</el-radio>
          <el-radio :label="2">使用后再领</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSubmit()">提交</el-button>
        <el-button @click="handleReset()">重置</el-button>
        <el-button @click="handleBack()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getCouponSchema, addCouponSchema, editCouponSchema } from '@/api/huiduoduo/coupon-schema-api'

const defaultCouponSchema = {
  merchantId: 0,
  categoryId: 0,
  name: '',
  title: '',
  image: '',
  introduction: '',
  commission: '',
  getStart: '',
  getEnd: '',
  validStart: '',
  validEnd: '',
  type: 1,
  codeImage: '',
  getMethod: 1
}

export default {
  name: 'CouponSchemaFrom',
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      couponSchema: Object.assign({}, defaultCouponSchema),
      rules: {}
    }
  },
  computed: {

  },
  created() {
    this.init()
  },
  methods: {
    init() {
      if (this.isEdit) {
        getCouponSchema(this.$route.query.id).then(response => {
          this.couponSchema = response.data
        })
      } else {
        this.couponSchema = Object.assign({}, defaultCouponSchema)
      }
    },
    handleSubmit() {
      this.$refs['couponSchemaForm'].validate(valid => {
        if (valid) {
          this.$confirm('是否提交数据', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            if (this.isEdit) {
              editCouponSchema(this.$route.query.id, this.couponSchema).then(response => {
                this.$message({
                  message: '修改成功',
                  type: 'success',
                  duration: 1000
                })
                this.$router.back()
              })
            } else {
              addCouponSchema(this.couponSchema).then(response => {
                this.$message({
                  message: '提交成功',
                  type: 'success',
                  duration: 1000
                })
                this.$router.back()
              })
            }
          })
        }
      })
    },
    handleReset() {
      this.init()
    },
    handleBack() {
      this.$router.back()
    }
  }
}
</script>

<style>
</style>
