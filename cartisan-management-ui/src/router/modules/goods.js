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
    path: 'brands',
    name: 'Brands',
    component: nested,
    redirect: '/goods/brands/brandList',
    meta: { title: '品牌管理', icon: 'example' },
    children: [{
      path: 'brandList',
      name: 'brandList',
      component: () => import('@/views/goods/brands'),
      meta: { title: '品牌管理', breadcrumb: false },
      hidden: false
    }, {
      path: 'brandAdd',
      name: 'BrandAdd',
      component: () => import('@/views/goods/brandAdd'),
      meta: { title: '添加品牌' },
      hidden: true
    },
    {
      path: 'brandEdit',
      name: 'BrandEdit',
      component: () => import('@/views/goods/brandEdit'),
      meta: { title: '编辑品牌' },
      hidden: true
    }]
  }]
};

export default goods;
