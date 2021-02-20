package com.gao.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.gao.feign.WxQrCodeFeignService;
import com.gao.model.WxQrTokenCmd;
import com.gao.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@RestController
public class QRCreatedService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private WxQrCodeFeignService wxQrCodeFeignService;

    //<img :src="imgurl" alt="">
    @PostMapping("/qrcreated")
    public Map<String,Object> qrcreated(@RequestParam Map<String,Object> map) throws IOException {
        Map<String,Object> resMap = new HashMap<>();
        //1获取token
        String accessToken = wxQrCodeFeignService.getAccessToken("wx8a8797c55a1a6227", "0dcbe6e8323088f97b620320b6cbb839");
        Map<String, String> parse = (Map<String, String>) JSONObject.parse(accessToken);
        logger.info("accessToken:"+accessToken);
        //2.获取小程序码
        WxQrTokenCmd cmd = new WxQrTokenCmd("anneng|ANNENG", "pages/ql/index/index");
        byte[] buffer = wxQrCodeFeignService.getUnlimited(parse.get("access_token"), cmd);
        String baseStr = "data:image/png;base64," + new String(Base64.encodeBase64(buffer));
        logger.info("baseStr:"+baseStr);
        resMap.put("base64",baseStr);
        return resMap;
    }

}
