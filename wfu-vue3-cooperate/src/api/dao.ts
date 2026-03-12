import {request} from "@/utils/request.ts";

//添加消息
export const addMsg = (data:any)=> {
    return request({
        url: '/chat/addMessage',
        method: 'post',/*后台是@PostMapping*/
        data: data/*属性名和属性值相同时可以只写一个data 同时要设置json请求参数*/

    })
}