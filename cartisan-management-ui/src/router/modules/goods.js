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
    name: 'Attributes',
    component: nested,
    redirect: '/goods/attributes/categories',
    meta: { title: '商品类型', icon: 'example' },
    children: [{
      path: 'categories',
      name: 'Categories',
      component: () => import('@/views/goods/productAttribute/productAttributeCategories'),
      meta: { title: '商品类型', breadcrumb: false },
      hidden: false
    },
    {
      path: 'productAttributes',
      name: 'ProductAttributes',
      component: () => import('@/views/goods/productAttribute/productAttributes'),
      meta: { title: '商品属性管理' },
      hidden: true
    }, {
      path: 'productAttributeAdd',
      name: 'ProductAttributeAdd',
      component: () => import('@/views/goods/productAttribute/productAttributeAdd'),
      meta: { title: '添加商品属性' },
      hidden: true
    },
    {
      path: 'productAttributeEdit',
      name: 'ProductAttributeEdit',
      component: () => import('@/views/goods/productAttribute/productAttributeEdit'),
      meta: { title: '编辑商品属性' },
      hidden: true
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
