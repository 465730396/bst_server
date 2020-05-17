import request from '@/utils/request'

// 查询违章订单信息
export function listOrders(data) {
  return request({
    url: '/mp/illega/order',
    method: 'post',
    params: data
  })
}
