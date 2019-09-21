package cn.lianhy.demo.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        jsonObject.put("_id",2);
        jsonObject.put("userName","root");
        jsonObject.put("password","223355");
        mongoTemplate.save(jsonObject,"QP_MEMBERS");
        return "Hello：mongo";
    }

    @GetMapping(value = "getOne.json")
    public String get(String _id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(1));
        JSONObject jsonObject=mongoTemplate.findOne(query,JSONObject.class,"QP_MEMBERS");
        return jsonObject.toString();
    }

    @GetMapping(value = "getAll.json")
    public String getAll(String _id){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("root"));
        List<JSONObject> list=mongoTemplate.find(query,JSONObject.class,"QP_MEMBERS");
        return JSON.toJSONString(list);
    }

    @GetMapping(value = "remove.json")
    public String remove(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("root"));
        DeleteResult deleteResult=mongoTemplate.remove(query,JSONObject.class,"QP_MEMBERS");
        return JSON.toJSONString(deleteResult);
    }

    @GetMapping(value = "removeAll.json")
    public String removeAll(){
        Query query = new Query();
        mongoTemplate.dropCollection("QP_MEMBERS");

        return "success";
    }
}
