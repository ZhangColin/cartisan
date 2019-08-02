import request from '@/utils/request'

export function searchCouponSchemas(currentPage, pageSize, params) {
  return request.get(`/huiduoduo/couponSchemas/search/${currentPage}/${pageSize}`, { params })
}

export function getAllCouponSchemas() {
  return request.get('/huiduoduo/couponSchemas')
}

export function getCouponSchema(id) {
  return request.get(`/huiduoduo/couponSchemas/${id}`)
}

export function addCouponSchema(params) {
  return request({
    url: '/huiduoduo/couponSchemas',
    method: 'post',
    data: params
  })
}

export function editCouponSchema(id, params) {
  return request({
    url: `/huiduoduo/couponSchemas/${id}`,
    method: 'put',
    data: params
  })
}

export function removeCouponSchema(id) {
  return request({
    url: `/huiduoduo/couponSchemas/${id}`,
    method: 'delete'
  })
}

