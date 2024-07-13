import axios from 'axios';

const api = axios.create({
    // headers:{
    //     // 'Accepts':'application/json',
    //     // 'Access-Control-Allow-Origin':'*'
    // },
    withCredentials:true, //отправка куки вместе с запросом
    baseURL: '/api',

});

api.interceptors.request.use((config:any)=>{
//     let refreshToken=localStorage.getItem('token');
//     if(refreshToken)
    config.headers.Authorization=`Bearer ${localStorage.getItem('accessToken')}`;
    return config;
});

export default api;