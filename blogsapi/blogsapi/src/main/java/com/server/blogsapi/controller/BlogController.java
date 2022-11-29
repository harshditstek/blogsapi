package com.server.blogsapi.controller;

import com.server.blogsapi.dto.BlogDTO;
import com.server.blogsapi.dto.ErrorEntity;
import com.server.blogsapi.entity.BlogEntity;
import com.server.blogsapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blog/{id}")
    public ResponseEntity<Object> findBlogByIds(@PathVariable("id") int id){
        BlogDTO entity = null;
        String msg = "nams'"+id+"'";
        entity = blogService.findBlogById(id);
        if(entity != null){
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        else{
            ErrorEntity er = new ErrorEntity();
            er.setStatus("Id not Available, hit 'http://localhost:8080/blog' or 'http://localhost:8080/blogs/"+id+"' url, if you used 'http://localhost:8080/blog' then after you used 'http://localhost:8080/blog/"+id+"'");
            er.setMsg(String.valueOf(400));
            return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Object> findBlogById(@PathVariable("id") int id){
        BlogDTO entity = null;
        entity = blogService.findBlogByIds(id);
        if(entity.getTitle() != null){
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        else{
            ErrorEntity er = new ErrorEntity();
            er.setStatus("Id not Available");
            er.setMsg(String.valueOf(400));
            return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/blog")
    public List<BlogDTO> findBlog(){
        return blogService.findBlog();
    }
}
