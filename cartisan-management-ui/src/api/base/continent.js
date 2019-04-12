import request from '@/utils/request'

export function findContinents() {
  return request({
    url: '/continent',
    method: 'get'
  })
}
