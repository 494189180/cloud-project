package com.gao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "wechat", url = "https://api.weixin.qq.com")
public interface WxOpenIdFeignService {

    @RequestMapping(value = "/sns/jscode2session?appid=wx8a8797c55a1a6227&secret=0dcbe6e8323088f97b620320b6cbb839&js_code={code}&grant_type=authorization_code", method = RequestMethod.GET,consumes = "application/json")
    String getOpenId(@PathVariable("code") String code);
}
