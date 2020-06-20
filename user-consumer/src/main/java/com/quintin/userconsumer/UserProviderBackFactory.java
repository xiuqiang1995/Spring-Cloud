package com.quintin.userconsumer;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import java.util.Map;

@Component
public class UserProviderBackFactory implements FallbackFactory<ComsumerApi> {
    @Override
    public ComsumerApi create(Throwable cause) {
        return new ComsumerApi() {
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

            @Override
            public String alive() {
//                // TODO Auto-generated method stub
//                System.out.println(throwable.getLocalizedMessage());
//                throwable.printStackTrace();
//                return ToStringBuilder.reflectionToString(throwable);

                // TODO Auto-generated method stub
                System.out.println(cause);
                if(cause instanceof HttpServerErrorException.InternalServerError) {
                    System.out.println("InternalServerError");
                    return "远程服务报错";
                }else if(cause instanceof RuntimeException) {
                    return "请求时异常：" + cause;
                }else {
                    return "都算不上";
                }
            }

            @Override
            public String getById(Integer id) {
                return null;
            }
        };
    }
}
