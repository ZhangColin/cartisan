import request from '@/utils/request';

export function findCountries(continentId) {
  return request({
    url: '/country',
    method: 'get',
    params: {continentId}
  });
};

export function searchCountries(params) {
  return request({
    url: '/country/search',
    method: 'get',
    params
  });
};
