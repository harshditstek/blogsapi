package com.server.blogsapi.service;

import com.server.blogsapi.dto.BlogDTO;
import com.server.blogsapi.entity.BlogEntity;
import com.server.blogsapi.mapper.BlogMapper;
import com.server.blogsapi.repository.BlogRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class BlogServiceImpl implements BlogService {

    private final String POST_URL = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    BlogRepo blogRepo;

    @Autowired
    private BlogMapper blogMapper;

    public List<BlogDTO> findBlog() {
        List<BlogDTO> list = null;
        try {
            URL obj = new URL(POST_URL);
            // HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                StringBuffer inline1 = new StringBuffer();
                Scanner scanner = new Scanner(obj.openStream());

                while (scanner.hasNext()) {
                    inline1.append(scanner.nextLine());
                }
                System.out.println(inline1);
                String data = inline1.toString();
                list = stringToJson(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<BlogDTO> stringToJson(String json) {
        List<BlogDTO> list = new ArrayList<>();
        JSONArray postArrayJson = new JSONArray(json);
        for (int i = 0; i < postArrayJson.length(); i++) {
            JSONObject oj = (JSONObject) postArrayJson.get(i);
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setId((int) oj.get("id"));
            blogDTO.setBody((String) oj.get("body"));
            blogDTO.setTitle((String) oj.get("title"));
            blogDTO.setUserId((int) oj.get("userId"));
            BlogEntity entity = blogMapper.dtoToEntity(blogDTO);
            blogRepo.save(entity);
            list.add(blogDTO);
        }
        return list;
    }

    @Override
    public BlogDTO findBlogByIds(int id) {
        BlogDTO list = null;
        try {
            URL obj = new URL(POST_URL);
            // HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                StringBuffer inline1 = new StringBuffer();
                Scanner scanner = new Scanner(obj.openStream());

                while (scanner.hasNext()) {
                    inline1.append(scanner.nextLine());
                }
                System.out.println(inline1);
                String data = inline1.toString();
                list = stringToJsonWithId(data, id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public BlogDTO findBlogById(int id) {
        BlogDTO list = null;
        Optional<BlogEntity> opt = blogRepo.findById(id);
        if(opt.isPresent()){
            return blogMapper.entityToDto(opt.get());
        }
        else {
            return list;
        }
    }


    public BlogDTO stringToJsonWithId(String json, int id) {
        JSONArray postArrayJson = new JSONArray(json);
        BlogDTO responseEntity = new BlogDTO();
        for (int i = 0; i < postArrayJson.length(); i++) {

            JSONObject oj = (JSONObject) postArrayJson.get(i);
            if (((int) oj.get("id")) == id) {
                responseEntity.setId((int) oj.get("id"));
                responseEntity.setBody((String) oj.get("body"));
                responseEntity.setTitle((String) oj.get("title"));
                responseEntity.setUserId((int) oj.get("userId"));
            }
        }
        return responseEntity;
    }
}
