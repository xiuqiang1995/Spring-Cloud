package com.quintin.userconsumer;

import com.quintin.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@FeignClient(name="user-provider",fallbackFactory = UserProviderBackFactory.class)
public interface ComsumerApi extends UserApi {

    /**
     * 这里 getMapping 是给 Feign 看的 get 请求
     * 拼起来就是 user-provider/getMap?id={1}
     * @RequestParam("id") 也是给 Feign 看的
     * @param id
     * @return
     */
    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);

    @GetMapping("/getMap2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id,@RequestParam("name") String name);

    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);

    @PostMapping("/postMap")
    Map<Integer, String> postMap(Map<String, Object> map);
}
