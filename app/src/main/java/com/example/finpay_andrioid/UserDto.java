package com.example.finpay_andrioid;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserDto {

    private String user_id;
    private String user_name;

    @Builder
    public UserDto(String user_id, String user_name) {
        this.user_id = user_id;
        this.user_name = user_name;
    }
}
