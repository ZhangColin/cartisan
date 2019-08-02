import request from '@/utils/request'

export function getAllStores(merchantId) {
  return request.get(`/huiduoduo/merchants/${merchantId}/stores`)
}

export function getStore(merchantId, id) {
  return request.get(`/huiduoduo/merchants/${merchantId}/stores/${id}`)
}

export function addStore(merchantId, params) {
  return request({
    url: `/huiduoduo/merchants/${merchantId}/stores`,
    method: 'post',
    data: params
  })
}

export function editStore(merchantId, id, params) {
  return request({
    url: `/huiduoduo/merchants/${merchantId}/stores/${id}`,
    method: 'put',
    data: params
  })
}

export function removeStore(merchantId, id) {
  return request({
    url: `/huiduoduo/merchants/${merchantId}/stores/${id}`,
    method: 'delete'
  })
}

