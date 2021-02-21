package com.gao.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gao.consumer.service.HttpRequestFeign;
import com.gao.feign.WxQrCodeFeignService;
import com.gao.model.WxQrTokenCmd;
import com.gao.service.UserService;
import feign.Feign;
import feign.Target;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class QRCreatedService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private WxQrCodeFeignService wxQrCodeFeignService;

    /**
     * <img :src="imgurl" alt="">
     * @param map
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    @PostMapping("/qrcreated")
    public Map<String,Object> qrcreated(@RequestParam Map<String,Object> map) throws IOException, URISyntaxException {
        Map<String,Object> resMap = new HashMap<>();
        /**
         * 1.获取token
         */
        String reqStr = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s","wx8a8797c55a1a6227","0dcbe6e8323088f97b620320b6cbb839");
        HttpRequestFeign httpRequestFeign = Feign.builder().target(Target.EmptyTarget.create(HttpRequestFeign.class));
        String accessToken = httpRequestFeign.sendGetRequest(new URI(reqStr));
        //String accessToken = wxQrCodeFeignService.getAccessToken("wx8a8797c55a1a6227", "0dcbe6e8323088f97b620320b6cbb839");
        Map<String, String> parse = (Map<String, String>) JSONObject.parse(accessToken);
        logger.info("accessToken:"+accessToken);
        /**
         * 2.获取小程序码
         */
        WxQrTokenCmd cmd = new WxQrTokenCmd("anneng", "pages/ql/index/index");
        String access_token = parse.get("access_token");
        String reqStr2 = String.format("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s",access_token);
        byte[] buffer = httpRequestFeign.sendPostRequest(new URI(reqStr2), JSON.toJSONString(cmd));
        //byte[] buffer = wxQrCodeFeignService.getUnlimited(access_token, cmd);
        String baseStr = "data:image/png;base64," + new String(Base64.encodeBase64(buffer));
        logger.info("baseStr:"+baseStr);
        resMap.put("base64",baseStr);
        return resMap;
    }

}
