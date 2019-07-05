import request from '@/utils/request'

export function getAllCategories() {
  return request.get('/huiduoduo/categories')
}

export function getCategory(id) {
  return request.get(`/huiduoduo/categories/${id}`)
}

export function addCategory(params) {
  return request({
    url: '/huiduoduo/categories',
    method: 'post',
    data: params
  })
}

export function editCategory(id, params) {
  return request({
    url: `/huiduoduo/categories/${id}`,
    method: 'put',
    data: params
  })
}

export function removeCategory(id) {
  return request({
    url: `/huiduoduo/categories/${id}`,
    method: 'delete'
  })
}
