import request from '@/utils/request'

export const list = () => {
  return request({
    url: '/api/pro/user/list',
    method: 'get'
  })
}
