package com.gao.feign;

import com.gao.model.WxQrTokenCmd;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wechat", url = "https://api.weixin.qq.com")
public interface WxQrCodeFeignService {
    /**
     * 获取微信授权token
     * @param appid
     * @param secret
     * @return
     */
    @RequestMapping(value = "/cgi-bin/token?grant_type=client_credential", method = RequestMethod.GET)
    String getAccessToken(@RequestParam(name = "appid") String appid, @RequestParam(name = "secret") String secret);

    /**
     * 生成无限制的小程序码
     * @param accessToken
     * @param cmd
     * @return
     */
    @RequestMapping(value = "/wxa/getwxacodeunlimit?access_token={accessToken}", method = RequestMethod.POST, consumes = "application/json")
    byte[] getUnlimited(@PathVariable("accessToken") String accessToken, WxQrTokenCmd cmd);

}
