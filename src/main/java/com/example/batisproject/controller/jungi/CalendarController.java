package com.example.batisproject.controller.jungi;


import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.User;

import com.example.batisproject.mapper.jungi.CategoryMapper;
import com.example.batisproject.mapper.jungi.GatherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CalendarController {

    private final GatherMapper gatherMapper;
    private final CategoryMapper categoryMapper;

    private final AuthenticationForModel authenticationForModel;

    @GetMapping("/full-calendar/calendar-admin")
    public List<Map<String, Object>> monthPlan() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authenticationForModel.getAuthentication();

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();

        HashMap<String, Object> hash = new HashMap<>();

        if (authentication.isAuthenticated()) {
            List<Gather> listAll = gatherMapper.getAllByNickname(user.getNickname());
            for (int i = 0; i < listAll.size(); i++) {
                hash.put("id", listAll.get(i).getId());
                hash.put("title", listAll.get(i).getTitle());
                hash.put("content", listAll.get(i).getContent());
                hash.put("start", listAll.get(i).getStartDate());
                hash.put("end", listAll.get(i).getEndDate());

                jsonObj = new JSONObject(hash);
                jsonArr.add(jsonObj);
                log.info("jsonArrCheck: {}", jsonArr);
            }
        }

        return jsonArr;
    }
}
