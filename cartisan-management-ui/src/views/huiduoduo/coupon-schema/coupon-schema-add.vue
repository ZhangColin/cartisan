<template>
  <coupon-schema-form :is-edit="false" />
</template>

<script>
import couponSchemaForm from './coupon-schema-form'

export default {
  name: 'AddCouponSchema',
  components: { couponSchemaForm }
}
</script>
