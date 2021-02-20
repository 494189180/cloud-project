package com.gao.consumer.controller;

import com.gao.feign.WxOpenIdFeignService;
import com.gao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class WxOpenIdService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    private WxOpenIdFeignService wxOpenIdFeignService;
    @PostMapping("/getOpenId")
    public String getOpenId(@RequestParam Map<String,Object> map) throws URISyntaxException {
        String code = (String) map.get("code");
        String openId = wxOpenIdFeignService.getOpenId(code);
        logger.info("openId:"+openId);
        return openId;
    }
}
