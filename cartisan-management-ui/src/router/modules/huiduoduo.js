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
      path: 'categoriesList',
      name: 'categoriesList',
      component: () => import('@/views/huiduoduo/category/categories'),
      meta: { title: '分类列表', breadcrumb: false },
      hidden: false
    }]
  }]
}

export default huiduoduo
