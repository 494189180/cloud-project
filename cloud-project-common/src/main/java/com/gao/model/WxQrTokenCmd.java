package com.gao.model;

import lombok.Data;

@Data
public class WxQrTokenCmd {
    private String scene;
    private String page;

    public WxQrTokenCmd(String scene, String page) {
        this.scene = scene;
        this.page = page;
    }
}
