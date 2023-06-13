package com.hzlx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfo implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String tel;
    private String address;
    private String avatar;
    private Date createTime;
    private Integer status;
}
