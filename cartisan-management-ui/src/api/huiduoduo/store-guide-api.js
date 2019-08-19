import request from '@/utils/request'

export function getAllStoreGuides(couponSchemaId) {
  return request.get(`/huiduoduo/couponSchemas/${couponSchemaId}/storeGuides`)
}

export function getStoreGuide(couponSchemaId, id) {
  return request.get(`/huiduoduo/couponSchemas/${couponSchemaId}/storeGuides/${id}`)
}

export function addStoreGuide(couponSchemaId, params) {
  return request({
    url: `/huiduoduo/couponSchemas/${couponSchemaId}/storeGuides`,
    method: 'put',
    data: params
  })
}

export function editStoreGuide(couponSchemaId, params) {
  return request({
    url: `/huiduoduo/couponSchemas/${couponSchemaId}/storeGuides/`,
    method: 'put',
    data: params
  })
}

export function removeStoreGuides(couponSchemaId, id) {
  return request({
    url: `/huiduoduo/couponSchemas/${couponSchemaId}/storeGuides/${id}`,
    method: 'delete'
  })
}

