package com.gao.consumer.service;

import feign.RequestLine;

import java.net.URI;

public interface HttpRequestFeign {
    @RequestLine("GET")
    String sendGetRequest(URI baseUri);

    @RequestLine("POST")
    byte[] sendPostRequest(URI baseUri, String map);
}
