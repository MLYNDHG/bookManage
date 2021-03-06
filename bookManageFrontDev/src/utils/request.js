import axios from 'axios'
import { Message } from 'element-ui' // 项目用的element ui
import router from '@/router/index.js'

const service = axios.create({
        timeout: 7000, // 超过7秒提示超时
    })
    // service.baseUrl = 'http://192.168.0.50:9090';//跨域用的
    //service.baseUrl = 'http://4651639y9h.zicp.vip:51395';
service.baseUrl = 'http://localhost:9090';

// 请求拦截器
service.interceptors.request.use(
    config => { //config配置
        if (sessionStorage.getItem('Authorization')) {
            config.headers.Authorization = sessionStorage.getItem('Authorization');
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
)

// 响应拦截器
service.interceptors.response.use(
    response => {
        // 接收后台参数状态
        const res = response.data;
        if (res.status == 20000 || res.status == 200 || res.status || res.status == 20000) {
            return res;
        } else {
            let message = res.message || '未知错误';
            Message({
                message,
                type: 'error',
                duration: 5 * 1000
            });
            // 这里可以设置后台返回状态码是500或者是其他,然后重定向跳转
            if (res.status == 500) {
                router.push('/login')
            }
            return Promise.reject(
                new Error(res.message || '未知错误')
            );
        }
    },
    error => {
        if (error.response) {
            // error.response有错误信息,是接口错误,不是取消请求
            // 获取错误码,弹出提示信息,reject()
            let code = error.response.data.status;
            if (code == 401) {
                return Promise.reject(
                    new Error('登录过期,请重新登录')
                );
            } else if (code == 500) {
                Message({
                    message: error.response.data.message,
                    type: 'error',
                    duration: 3 * 1000
                });
            } else {
                Message({
                    message: '服务器繁忙, 请稍后再试!  ' + code,
                    type: 'error',
                    duration: 3 * 1000
                });
            }
            return Promise.reject(error.message);
        } else {
            // 是取消请求
            // 直接reject
            return Promise.reject(error.message);
        }
    }
)
export default service; //暴露变量，导出，别人可以引用