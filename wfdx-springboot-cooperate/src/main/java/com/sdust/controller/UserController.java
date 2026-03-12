package com.sdust.controller;


import com.sdust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/* alt + enter 万能的快捷键 */
/*  用户-->前端界面-->发请求-->controller-->service-->mapper-->对应的mapper.xml执行sql语句
 最终数据逆向返回给前端用户  */
/*@Controller spring中用来标注这个类是个控制器 + @RequestBody */
@RestController//1.接受前端的请求（参数） 2.最终的响应数据给前端
//@RequestMapping表示接受前端的请求  controlle层用来拦截前端发送的异步请求
@RequestMapping("/user")
//处理跨域
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;


}
