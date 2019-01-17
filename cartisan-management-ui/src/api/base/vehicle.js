import request from '@/utils/request';

export function searchVehicles(params) {
  return request({
    url: '/vehicle',
    method: 'get',
    params
  });
};

export function save(vehicle) {
  let method = 'post';
  if (vehicle.id) {
    method = 'get';
  }
  return request({
    url: '/vehicle',
    method: method,
    params: vehicle
  });
};
