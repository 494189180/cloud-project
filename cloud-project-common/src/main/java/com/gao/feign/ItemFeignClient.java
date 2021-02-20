package com.gao.feign;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ItemFeignClient {
    @RequestMapping(value = "{path}", method = RequestMethod.GET)
    String getItem(@PathVariable("path") String path, @RequestParam(name = "appid") String appid, @RequestParam(name = "secret") String secret);

    @RequestMapping(value = "{path}", method = RequestMethod.POST)
    byte[] postItem(@PathVariable("path") String path);
}
