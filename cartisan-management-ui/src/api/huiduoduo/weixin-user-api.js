import request from '@/utils/request'

export function searchWeixinUsers(currentPage, pageSize, params) {
  return request.get(`/huiduoduo/weixinusers/search/${currentPage}/${pageSize}`, { params })
}
