import request from '@/utils/request';

export function searchAirports(currentPage, pageSize, params) {
  return request({
    url: `/airport/search/${currentPage}/${pageSize}`,
    method: 'get',
    params
  });
};
