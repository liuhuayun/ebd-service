/*
 * Copyright 2018
 * Created by janz (zhangzhi93@hotmail.com)
 */

/* eslint-disable */

import axios from 'axios';
import { Message } from 'element-ui';


const instance = axios.create({
  // -------  线上环境 -------------------------
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 100000,
  withCredentials: true,
});

// 添加请求拦截器
instance.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么 能做的事如下 检查权限 增加页面loading  网络状态判断等
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});


// 添加响应拦截器
instance.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  if (response.data.code !== 200 && !response.data.access_token) {
    Message.error(response.data.message);
  }
  return response;
}, function (error) {
  // 对响应错误做点什么
  // 例如YH请求失效，返回登录页什么的
  if (error.response.status === 401) {
    // window.location.replace('#/login');
  }
  return Promise.reject(error);
});


export default instance;
