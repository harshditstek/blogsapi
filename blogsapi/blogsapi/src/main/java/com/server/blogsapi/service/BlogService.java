package com.server.blogsapi.service;

import com.server.blogsapi.dto.BlogDTO;
import com.server.blogsapi.entity.BlogEntity;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    public List<BlogDTO> findBlog();
    public BlogDTO findBlogByIds(int id);
    BlogDTO findBlogById(int id);


}
