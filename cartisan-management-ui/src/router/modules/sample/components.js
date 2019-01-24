/** When your routing table is too long, you can split it into small modules**/

const componentsRouter = {
  path: '/components',
  component: () => import('@/views/sample/index'),
  redirect: 'noredirect',
  name: 'ComponentDemo',
  meta: {
    title: 'components',
    icon: 'component'
  },
  children: [
    {
      path: 'tinymce',
      component: () => import('@/views/sample/components-demo/tinymce'),
      name: 'TinymceDemo',
      meta: { title: 'tinymce' }
    },
    {
      path: 'markdown',
      component: () => import('@/views/sample/components-demo/markdown'),
      name: 'MarkdownDemo',
      meta: { title: 'markdown' }
    },
    {
      path: 'json-editor',
      component: () => import('@/views/sample/components-demo/jsonEditor'),
      name: 'JsonEditorDemo',
      meta: { title: 'jsonEditor' }
    },
    {
      path: 'splitpane',
      component: () => import('@/views/sample/components-demo/splitpane'),
      name: 'SplitpaneDemo',
      meta: { title: 'splitPane' }
    },
    {
      path: 'avatar-upload',
      component: () => import('@/views/sample/components-demo/avatarUpload'),
      name: 'AvatarUploadDemo',
      meta: { title: 'avatarUpload' }
    },
    {
      path: 'dropzone',
      component: () => import('@/views/sample/components-demo/dropzone'),
      name: 'DropzoneDemo',
      meta: { title: 'dropzone' }
    },
    {
      path: 'sticky',
      component: () => import('@/views/sample/components-demo/sticky'),
      name: 'StickyDemo',
      meta: { title: 'sticky' }
    },
    {
      path: 'count-to',
      component: () => import('@/views/sample/components-demo/countTo'),
      name: 'CountToDemo',
      meta: { title: 'countTo' }
    },
    {
      path: 'mixin',
      component: () => import('@/views/sample/components-demo/mixin'),
      name: 'ComponentMixinDemo',
      meta: { title: 'componentMixin' }
    },
    {
      path: 'back-to-top',
      component: () => import('@/views/sample/components-demo/backToTop'),
      name: 'BackToTopDemo',
      meta: { title: 'backToTop' }
    },
    {
      path: 'drag-dialog',
      component: () => import('@/views/sample/components-demo/dragDialog'),
      name: 'DragDialogDemo',
      meta: { title: 'dragDialog' }
    },
    {
      path: 'drag-select',
      component: () => import('@/views/sample/components-demo/dragSelect'),
      name: 'DragSelectDemo',
      meta: { title: 'dragSelect' }
    },
    {
      path: 'dnd-list',
      component: () => import('@/views/sample/components-demo/dndList'),
      name: 'DndListDemo',
      meta: { title: 'dndList' }
    },
    {
      path: 'drag-kanban',
      component: () => import('@/views/sample/components-demo/dragKanban'),
      name: 'DragKanbanDemo',
      meta: { title: 'dragKanban' }
    }
  ]
};

export default componentsRouter;
