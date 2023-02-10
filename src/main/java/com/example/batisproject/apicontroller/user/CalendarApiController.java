package com.example.batisproject.apicontroller.user;


import com.example.batisproject.annotaion.CurrentUser;
import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.gather.GatherService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
public class CalendarApiController {

    private final GatherService gatherService;


    @GetMapping("/api/main/calendar")
    public List<Map<String, Object>> monthPlan(@CurrentUser User user) {
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();

        HashMap<String, Object> hash = new HashMap<>();

        List<GatherDTO> listAll = gatherService.getAllByNickname(user.getNickname());
        for (int i = 0; i < listAll.size(); i++) {
            hash.put("id", listAll.get(i).getId());
            hash.put("title", listAll.get(i).getTitle());
            hash.put("content", listAll.get(i).getContent());
            hash.put("start", listAll.get(i).getStartDate());
            hash.put("end", listAll.get(i).getEndDate());

            jsonObj = new JSONObject(hash);
            jsonArr.add(jsonObj);
        }

        return jsonArr;
    }

    @Autowired
    public CalendarApiController(GatherService gatherService) {
        this.gatherService = gatherService;
    }
}
