import request from '@/utils/request'

// get 请求  查询
export const list = (parameter) => {
  return request({
    url: '/api/pro/user/list',
    method: 'get',
    parameter: parameter
  })
}

//  put 请求   修改
export const update = (id, data) => {
  return request({
    url: `/api/pro/user/update/${id}`,
    method: 'put',
    data: data
  })
}

//  delete 请求  删除
export const remove = (id) => {
  return request({
    url: `/api/pro/user/remove/${id}`,
    method: 'delete'
  })
}

//  post 请求  保存
export const save = (data) => {
  return request({
    url: '/api/pro/user/save',
    method: 'post',
    data: data
  })
}
