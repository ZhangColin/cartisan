/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout';
import nested from '@/views/layout/nested';

const goods = {
  path: '/goods',
  component: Layout,
  redirect: '/goods/brands/brandList',
  name: 'Goods',
  meta: {
    title: '商品',
    icon: 'component'
  },
  children: [{
    path: 'attributes',
    name: 'attributes',
    component: nested,
    redirect: '/goods/attributes/categories',
    meta: { title: '商品类型', icon: 'example' },
    children: [{
      path: 'categories',
      name: 'categories',
      component: () => import('@/views/goods/productAttribute/productAttributeCategories'),
      meta: { title: '商品类型', breadcrumb: false },
      hidden: false
    }]
  },
  {
    path: 'brands',
    name: 'Brands',
    component: nested,
    redirect: '/goods/brands/brandList',
    meta: { title: '品牌管理', icon: 'nested' },
    children: [{
      path: 'brandList',
      name: 'brandList',
      component: () => import('@/views/goods/brand/brands'),
      meta: { title: '品牌管理', breadcrumb: false },
      hidden: false
    }, {
      path: 'brandAdd',
      name: 'BrandAdd',
      component: () => import('@/views/goods/brand/brandAdd'),
      meta: { title: '添加品牌' },
      hidden: true
    },
    {
      path: 'brandEdit',
      name: 'BrandEdit',
      component: () => import('@/views/goods/brand/brandEdit'),
      meta: { title: '编辑品牌' },
      hidden: true
    }]
  }]
};

export default goods;
