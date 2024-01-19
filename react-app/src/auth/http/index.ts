import axios from 'axios';

export const API_URL ='http://localhost:8080';

const api = axios.create({
    headers:{
        'Accepts':'application/json',
        'Access-Control-Allow-Origin':'*'
    },
    withCredentials:true,
    baseURL: '/api',

});

export default api;