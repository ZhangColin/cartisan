import store from '@/store'

export default {
  inserted(el, binding, vnode) {
    const { value } = binding
    const permissions = store.getters && store.getters.permissions

    if (value && value instanceof Array && value.length > 0) {
      const permissionDefines = value

      const hasPermission = permissions.some(permission => {
        // 这里使用 includes，因为已经具体到一个权限点了，不应该使用 startsWith 来判断
        return permissionDefines.includes(permission)
      })

      if (!hasPermission) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    } else {
      throw new Error(`need permissions! Like v-permission="['admin','editor']"`)
    }
  }
}
