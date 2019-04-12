import request from '@/utils/request'

export function findCities(countryId) {
  return request({
    url: '/city',
    method: 'get',
    params: { countryId }
  })
}

export function searchCities(currentPage, pageSize, params) {
  return request({
    url: `/city/search/${currentPage}/${pageSize}`,
    method: 'get',
    params
  })
}
