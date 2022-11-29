package com.server.blogsapi.repository;

import com.server.blogsapi.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<BlogEntity, Integer> {

}
