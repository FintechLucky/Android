package com.example.finpay_andrioid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class  User{
    private long id;
    private String user_id;
    private String user_name;
    private String user_pass;
}