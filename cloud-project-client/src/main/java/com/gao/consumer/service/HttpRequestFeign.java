package com.gao.consumer.service;

import feign.RequestLine;

import java.net.URI;
import java.util.Map;

public interface HttpRequestFeign {
    @RequestLine("GET")
    String sendGetRequest(URI baseUri);

    @RequestLine("POST")
    String sendPostRequest(URI baseUri, Map map);
}
