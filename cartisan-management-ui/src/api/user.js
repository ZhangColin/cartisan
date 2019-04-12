import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/management/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/management/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/management/user/logout',
    method: 'post'
  })
}

