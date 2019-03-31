/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout';

const base = {
  path: '/goods',
  component: Layout,
  redirect: '/goods/brand',
  name: 'Goods',
  meta: {
    title: '商品',
    icon: 'clipboard'
  },
  children: [
    {
      path: 'brand',
      name: 'Brand',
      component: () => import('@/views/goods/brands'),
      meta: { title: '品牌管理' }
    },
    {
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
    }
  ]
};

export default base;
