import request from '@/utils/request';

export function searchVehicles(params) {
  return request({
    url: '/vehicle',
    method: 'get',
    params
  });
}

export function saveVehicle(vehicle) {
  let method = 'post';
  let url = '/vehicle';
  if (vehicle.id) {
    method = 'put';
    url += '/' + vehicle.id;
  }
  return request({
    url: url,
    method: method,
    data: vehicle
  });
}
