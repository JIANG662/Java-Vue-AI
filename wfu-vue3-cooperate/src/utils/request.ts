import axios from "axios";

//创建一个axios实例
export const request = axios.create({
    //默认的前缀地址
    baseURL: 'http://localhost:9090',
    timeout: 10000
})