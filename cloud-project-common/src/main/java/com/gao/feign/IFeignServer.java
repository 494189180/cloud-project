package com.gao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("cloud-server")
public interface IFeignServer {

    @PostMapping("/consuleUser")
    Map<String,Object> consuleUser();

    @PostMapping("{path}")
    Object excuteForPost(@PathVariable("path")String path,@RequestBody Map<String,Object> map);
}
