package com.gao.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class User extends Model<User> {
    private String id;
    private String name;
    private String code;
    private String status;
}
