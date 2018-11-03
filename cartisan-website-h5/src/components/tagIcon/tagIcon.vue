<template>
  <span :class="iconClass"></span>
</template>

<script>
  export default {
    name: 'tagIcon',
    props: ['type', 'index'],
    computed: {
      iconClass() {
        return this.classMap[this.type] + '_' + this.index;
      }
    },
    created() {
      this.classMap = ['decrease', 'discount', 'special', 'invoice', 'guarantee'];
    }
  };
</script>

<style scoped lang="stylus" rel="stylesheet/stylus">
  @import "../../assets/stylus/mixin"

  .decrease_1
    bg-image('decrease_1')

  .discount_1
    bg-image('discount_1')

  .guarantee_1
    bg-image('guarantee_1')

  .invoice_1
    bg-image('invoice_1')

  .special_1
    bg-image('special_1')

  .decrease_2
    bg-image('decrease_2')

  .discount_2
    bg-image('discount_2')

  .guarantee_2
    bg-image('guarantee_2')

  .invoice_2
    bg-image('invoice_2')

  .special_2
    bg-image('special_2')

  .decrease_3
    bg-image('decrease_3')

  .discount_3
    bg-image('discount_3')

  .guarantee_3
    bg-image('guarantee_3')

  .invoice_3
    bg-image('invoice_3')

  .special_3
    bg-image('special_3')

  .decrease_4
    bg-image('decrease_4')

  .discount_4
    bg-image('discount_4')

  .guarantee_4
    bg-image('guarantee_4')

  .invoice_4
    bg-image('invoice_4')

  .special_4
    bg-image('special_4')

</style>
