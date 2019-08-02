/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'
import nested from '@/layout/nested'

const huiduoduo = {
  path: '/huiduoduo',
  component: Layout,
  redirect: '/huiduoduo/categories',
  name: 'huiduoduo',
  meta: {
    title: '惠多多',
    icon: 'component',
    permissions: ['huiduoduo']
  },
  children: [{
    path: 'categories',
    name: 'categories',
    component: nested,
    redirect: '/huiduoduo/categories/categoryList',
    meta: { title: '分类管理', icon: 'nested', permissions: ['huiduoduo:caregory'] },
    children: [{
      path: 'categoryList',
      name: 'categoryList',
      component: () => import('@/views/huiduoduo/category/categories'),
      meta: { title: '分类管理', breadcrumb: false },
      hidden: false
    }]
  }, {
    path: 'merchants',
    name: 'merchants',
    component: nested,
    redirect: '/huiduoduo/merchants/merchantList',
    meta: { title: '商户管理', icon: 'nested', permissions: ['huiduoduo:merchant'] },
    children: [{
      path: 'merchantList',
      name: 'merchantList',
      component: () => import('@/views/huiduoduo/merchant/merchants'),
      meta: { title: '商户管理', breadcrumb: false },
      hidden: false
    }, {
      path: 'stores',
      name: 'stores',
      component: () => import('@/views/huiduoduo/merchant/stores'),
      meta: { title: '门店' },
      hidden: true
    }]
  }, {
    path: 'coupon-schemas',
    name: 'coupon-schemas',
    component: nested,
    redirect: '/huiduoduo/coupon-schemas/couponSchemaList',
    meta: { title: '优惠券模板管理', icon: 'nested', permissions: ['huiduoduo:couponSchema'] },
    children: [{
      path: 'couponSchemaList',
      name: 'couponSchemaList',
      component: () => import('@/views/huiduoduo/coupon-schema/coupon-schemas'),
      meta: { title: '优惠券模板管理', breadcrumb: false },
      hidden: false
    }, {
      path: 'coupon-schema-add',
      name: 'coupon-schema-add',
      component: () => import('@/views/huiduoduo/coupon-schema/coupon-schema-add'),
      meta: { title: '添加优惠券模板' },
      hidden: true
    }, {
      path: 'coupon-schema-edit',
      name: 'coupon-schema-edit',
      component: () => import('@/views/huiduoduo/coupon-schema/coupon-schema-edit'),
      meta: { title: '编辑优惠券模板' },
      hidden: true
    }, {
      path: 'store-guides',
      name: 'store-guides',
      component: () => import('@/views/huiduoduo/coupon-schema/store-guides'),
      meta: { title: '门店指南' },
      hidden: true
    }]
  }, {
    path: 'weixin-users',
    name: 'weixin-users',
    component: nested,
    redirect: '/huiduoduo/weixin-user/weixinUserList',
    meta: { title: '微信用户管理', icon: 'nested', permissions: ['huiduoduo:weixin-user'] },
    children: [{
      path: 'weixinUserList',
      name: 'weixinUserList',
      component: () => import('@/views/huiduoduo/weixin-user/weixin-users'),
      meta: { title: '微信用户管理', breadcrumb: false },
      hidden: false
    }]
  }, {
    path: 'referrers',
    name: 'referrers',
    component: nested,
    redirect: '/huiduoduo/referrers/referrerList',
    meta: { title: '推荐人管理', icon: 'nested', permissions: ['huiduoduo:referrer'] },
    children: [{
      path: 'referrerList',
      name: 'referrerList',
      component: () => import('@/views/huiduoduo/referrer/referrers'),
      meta: { title: '推荐人管理', breadcrumb: false },
      hidden: false
    }]
  }]
}

export default huiduoduo
