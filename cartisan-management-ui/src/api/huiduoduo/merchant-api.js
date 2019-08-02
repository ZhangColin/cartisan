import request from '@/utils/request'

export function searchMerchants(currentPage, pageSize, params) {
  return request.get(`/huiduoduo/merchants/search/${currentPage}/${pageSize}`, { params })
}

export function getAllMerchants() {
  return request.get('/huiduoduo/merchants')
}

export function getMerchant(id) {
  return request.get(`/huiduoduo/merchants/${id}`)
}

export function addMerchant(params) {
  return request({
    url: '/huiduoduo/merchants',
    method: 'post',
    data: params
  })
}

export function editMerchant(id, params) {
  return request({
    url: `/huiduoduo/merchants/${id}`,
    method: 'put',
    data: params
  })
}

export function removeMerchant(id) {
  return request({
    url: `/huiduoduo/merchants/${id}`,
    method: 'delete'
  })
}

