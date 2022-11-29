package com.server.blogsapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BlogDTO {

    private int id;
    private int userId;

    private String title;
    private String body;

//    public BlogDTO(int id, int userId, String title, String body){
//        this.id = id;
//        this.userId = userId;
//        this.title = title;
//        this.body = body;
//
//    }
}
