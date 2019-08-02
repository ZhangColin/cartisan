<template>
  <coupon-schema-form :is-edit="true" />
</template>

<script>
import couponSchemaForm from './coupon-schema-form'

export default {
  name: 'EditCouponSchema',
  components: { couponSchemaForm }
}
</script>
