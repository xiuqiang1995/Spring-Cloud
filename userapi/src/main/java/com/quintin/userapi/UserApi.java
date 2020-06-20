package com.quintin.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//面向接口编程
//但是不跨语言了
//@RequestMapping("/user")
public interface UserApi {

    /**
     * 查看当前服务状态
     * @return
     */
    @GetMapping("/alive")
    String alive();

    @RequestMapping("/getById")
    String getById(@RequestParam("id") Integer id);
}
