import request from '@/utils/request';

export function findCountries(continentId) {
  return request({
    url: '/management/country',
    method: 'get',
    params: { continentId }
  });
}

export function searchCountries(params) {
  return request({
    url: '/management/country/search',
    method: 'get',
    params
  });
}
