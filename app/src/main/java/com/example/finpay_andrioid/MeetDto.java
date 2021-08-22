package com.example.finpay_andrioid;

import lombok.Builder;

public class MeetDto {
    private Long id;
    private String name;

    @Builder
    public MeetDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
