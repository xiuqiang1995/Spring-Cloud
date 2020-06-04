package com.example.consumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    DiscoveryClient dc;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient ec;

    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/client")
    public String client() {
        List<String> services = dc.getServices();
        for (String str :
                services) {
            System.out.println("str = " + str);
        }
        return "hi";
    }

    @GetMapping("/client2")
    public Object client2() {
        return dc.getInstances("provider");
    }

    @GetMapping("/client3")
    public Object client3() {
        List<ServiceInstance> instances = dc.getInstances("provider");
        for (ServiceInstance s :
                instances) {
            System.out.println(ToStringBuilder.reflectionToString(s));
        }
        return "success";
    }

    @GetMapping("/client4")
    public Object client4() {

        //根据 <instanceId>192.168.0.164:provider:8080</instanceId> 找到具体服务
        //List<InstanceInfo> instances = ec.getInstancesById("192.168.0.164:provider:8080");

        //使用服务名，找到服务列表
        List<InstanceInfo> instances = ec.getInstancesByVipAddress("provider", false);

        for (InstanceInfo ins : instances) {
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }

        if (instances.size() > 0) {
            //取第一个服务
            InstanceInfo instanceInfo = instances.get(0);
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/hi";
                System.out.println("url = " + url);
                RestTemplate restTemplate = new RestTemplate();
                String forObject = restTemplate.getForObject(url, String.class);
                System.out.println("forObject = " + forObject);
            }
        }
        return "1";
    }

    @GetMapping("/client5")
    public Object client5() {

        //ribbon 完成客户端负载均衡，并过滤掉 DOWN 了的节点
        ServiceInstance instance = lb.choose("provider");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hi";
        System.out.println("url = " + url);
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println("forObject = " + forObject);
        return forObject;
    }

    @GetMapping("/client6")
    public Object client6() {

        //ribbon 完成客户端负载均衡，并过滤掉 DOWN 了的节点
        ServiceInstance instance = lb.choose("provider");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/hi";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    @GetMapping("/client7")
    public Object client7() {
        String url = "http://provider/hi";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }
}
