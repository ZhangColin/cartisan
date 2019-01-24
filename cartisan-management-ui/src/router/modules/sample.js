/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout';

import componentsRouter from './sample/components';
import chartsRouter from './sample/charts';
import tableRouter from './sample/table';
import nestedRouter from './sample/nested';

const sample = {
  path: '/sample',
  component: Layout,
  redirect: '/documentation',
  name: 'sample',
  meta: {
    title: '示例',
    icon: 'example'
  },
  children: [
    {
      path: '/documentation',
      component: () => import('@/views/sample/index'),
      redirect: '/documentation/index',
      children: [
        {
          path: 'index',
          component: () => import('@/views/sample/documentation/index'),
          name: 'Documentation',
          meta: { title: 'documentation', icon: 'documentation', noCache: true }
        }
      ]
    },
    {
      path: '/guide',
      component: () => import('@/views/sample/index'),
      redirect: '/guide/index',
      children: [
        {
          path: 'index',
          component: () => import('@/views/sample/guide/index'),
          name: 'Guide',
          meta: { title: 'guide', icon: 'guide', noCache: true }
        }
      ]
    },
    {
      path: '/permission',
      component: () => import('@/views/sample/index'),
      redirect: '/permission/index',
      alwaysShow: true, // will always show the root menu
      meta: {
        title: 'permission',
        icon: 'lock',
        roles: ['admin', 'editor'] // you can set roles in root nav
      },
      children: [
        {
          path: 'page',
          component: () => import('@/views/sample/permission/page'),
          name: 'PagePermission',
          meta: {
            title: 'pagePermission',
            roles: ['admin'] // or you can only set roles in sub nav
          }
        },
        {
          path: 'directive',
          component: () => import('@/views/sample/permission/directive'),
          name: 'DirectivePermission',
          meta: {
            title: 'directivePermission'
            // if do not set roles, means: this page does not require permission
          }
        }
      ]
    },
    {
      path: '/icon',
      component: () => import('@/views/sample/index'),
      children: [
        {
          path: 'index',
          component: () => import('@/views/svg-icons/index'),
          name: 'Icons',
          meta: { title: 'icons', icon: 'icon', noCache: true }
        }
      ]
    },

    /** When your routing table is too long, you can split it into small modules**/
    componentsRouter,
    chartsRouter,
    nestedRouter,
    tableRouter,

    {
      path: '/example',
      component: () => import('@/views/sample/index'),
      redirect: '/example/list',
      name: 'Example',
      meta: {
        title: 'example',
        icon: 'example'
      },
      children: [
        {
          path: 'create',
          component: () => import('@/views/sample/example/create'),
          name: 'CreateArticle',
          meta: { title: 'createArticle', icon: 'edit' }
        },
        {
          path: 'edit/:id(\\d+)',
          component: () => import('@/views/sample/example/edit'),
          name: 'EditArticle',
          meta: { title: 'editArticle', noCache: true },
          hidden: true
        },
        {
          path: 'list',
          component: () => import('@/views/sample/example/list'),
          name: 'ArticleList',
          meta: { title: 'articleList', icon: 'list' }
        }
      ]
    },

    {
      path: '/tab',
      component: () => import('@/views/sample/index'),
      children: [
        {
          path: 'index',
          component: () => import('@/views/sample/tab/index'),
          name: 'Tab',
          meta: { title: 'tab', icon: 'tab' }
        }
      ]
    },

    {
      path: '/error',
      component: () => import('@/views/sample/index'),
      redirect: 'noredirect',
      name: 'ErrorPages',
      meta: {
        title: 'errorPages',
        icon: '404'
      },
      children: [
        {
          path: '401',
          component: () => import('@/views/errorPage/401'),
          name: 'Page401',
          meta: { title: 'page401', noCache: true }
        },
        {
          path: '404',
          component: () => import('@/views/errorPage/404'),
          name: 'Page404',
          meta: { title: 'page404', noCache: true }
        }
      ]
    },

    {
      path: '/error-log',
      component: () => import('@/views/sample/index'),
      redirect: 'noredirect',
      children: [
        {
          path: 'log',
          component: () => import('@/views/sample/errorLog/index'),
          name: 'ErrorLog',
          meta: { title: 'errorLog', icon: 'bug' }
        }
      ]
    },

    {
      path: '/excel',
      component: () => import('@/views/sample/index'),
      redirect: '/excel/export-excel',
      name: 'Excel',
      meta: {
        title: 'excel',
        icon: 'excel'
      },
      children: [
        {
          path: 'export-excel',
          component: () => import('@/views/sample/excel/exportExcel'),
          name: 'ExportExcel',
          meta: { title: 'exportExcel' }
        },
        {
          path: 'export-selected-excel',
          component: () => import('@/views/sample/excel/selectExcel'),
          name: 'SelectExcel',
          meta: { title: 'selectExcel' }
        },
        {
          path: 'upload-excel',
          component: () => import('@/views/sample/excel/uploadExcel'),
          name: 'UploadExcel',
          meta: { title: 'uploadExcel' }
        }
      ]
    },

    {
      path: '/zip',
      component: () => import('@/views/sample/index'),
      redirect: '/zip/download',
      alwaysShow: true,
      meta: { title: 'zip', icon: 'zip' },
      children: [
        {
          path: 'download',
          component: () => import('@/views/sample/zip/index'),
          name: 'ExportZip',
          meta: { title: 'exportZip' }
        }
      ]
    },

    {
      path: '/pdf',
      component: () => import('@/views/sample/index'),
      redirect: '/pdf/index',
      meta: { title: 'PDF', icon: 'pdf' },
      children: [
        {
          path: 'index',
          component: () => import('@/views/sample/pdf/index'),
          name: 'PDF',
          meta: { title: 'PDF' }
        }
      ]
    },
    {
      path: '/pdf/download',
      component: () => import('@/views/sample/pdf/download'),
      hidden: true
    },

    {
      path: '/theme',
      component: () => import('@/views/sample/index'),
      redirect: 'noredirect',
      children: [
        {
          path: 'index',
          component: () => import('@/views/sample/theme/index'),
          name: 'Theme',
          meta: { title: 'theme', icon: 'theme' }
        }
      ]
    },

    {
      path: '/clipboard',
      component: () => import('@/views/sample/index'),
      redirect: 'noredirect',
      children: [
        {
          path: 'index',
          component: () => import('@/views/sample/clipboard/index'),
          name: 'ClipboardDemo',
          meta: { title: 'clipboardDemo', icon: 'clipboard' }
        }
      ]
    },

    {
      path: '/i18n',
      component: () => import('@/views/sample/index'),
      children: [
        {
          path: 'index',
          component: () => import('@/views/sample/i18n-demo/index'),
          name: 'I18n',
          meta: { title: 'i18n', icon: 'international' }
        }
      ]
    },

    {
      path: 'external-link',
      component: () => import('@/views/sample/index'),
      children: [
        {
          path: 'https://github.com/PanJiaChen/vue-element-admin',
          meta: { title: 'externalLink', icon: 'link' }
        }
      ]
    },

    { path: '*', redirect: '/404', hidden: true }
  ]
};

export default sample;
