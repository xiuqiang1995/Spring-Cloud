package com.quintin.userprovider2;

import com.quintin.userapi.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    String port;

    private AtomicInteger count = new AtomicInteger();

    @Override
    public String alive(){
        System.out.println("alive");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = count.getAndIncrement();
        System.out.println("port:"+ port + "、弟 " + i + " 次调用");
        return "port : " + port;
    }

    @Override
    public String getById(Integer id) {
        System.out.println("id = " + id);
        return "id : "+id;
    }

    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        System.out.println(id);
        System.out.println(Collections.singletonMap(id, "mmeme"));
        return Collections.singletonMap(id, "memeda");
    }
    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id,String name) {
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }
}
