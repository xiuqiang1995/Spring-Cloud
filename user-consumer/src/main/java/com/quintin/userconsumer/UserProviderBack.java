package com.quintin.userconsumer;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class UserProviderBack implements ComsumerApi{


    @Override
    public String alive() {
        System.out.println("降级");
        return "降级";
    }

    @Override
    public String getById(Integer id) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap(Integer id) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }
}
