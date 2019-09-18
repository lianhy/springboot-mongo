package cn.lianhy.demo.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/demo")
public class HelloWorld {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "add.json")
    public String add(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName","admin");
        jsonObject.put("password","123456");
        mongoTemplate.save(jsonObject);
        return "Hello：mongo";
    }

    @GetMapping(value = "get.json")
    public String get(){
        List<JSONObject> list=mongoTemplate.findAll(JSONObject.class,"123");
        return "Hello：mongo"+ JSON.toJSONString(list);
    }
}
