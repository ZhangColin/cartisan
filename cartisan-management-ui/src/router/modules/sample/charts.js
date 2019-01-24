/** When your routing table is too long, you can split it into small modules**/

const chartsRouter = {
  path: '/charts',
  component: () => import('@/views/sample/index'),
  redirect: 'noredirect',
  name: 'Charts',
  meta: {
    title: 'charts',
    icon: 'chart'
  },
  children: [
    {
      path: 'keyboard',
      component: () => import('@/views/sample/charts/keyboard'),
      name: 'KeyboardChart',
      meta: { title: 'keyboardChart', noCache: true }
    },
    {
      path: 'line',
      component: () => import('@/views/sample/charts/line'),
      name: 'LineChart',
      meta: { title: 'lineChart', noCache: true }
    },
    {
      path: 'mixchart',
      component: () => import('@/views/sample/charts/mixChart'),
      name: 'MixChart',
      meta: { title: 'mixChart', noCache: true }
    }
  ]
};

export default chartsRouter;
