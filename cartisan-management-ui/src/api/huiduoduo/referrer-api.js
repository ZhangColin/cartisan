import request from '@/utils/request'

export function searchReferrers(currentPage, pageSize, params) {
  return request.get(`/huiduoduo/referrers/search/${currentPage}/${pageSize}`, { params })
}
