package com.example.batisproject.controller.jungi;

import com.example.batisproject.entity.Gather;
import com.example.batisproject.mapper.jungi.CategoryMapper;
import com.example.batisproject.mapper.jungi.GatherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CalendarController {

    private final GatherMapper gatherMapper;
    private final CategoryMapper categoryMapper;

    @GetMapping("/full-calendar/calendar-admin")
    @ResponseBody
    public List<Map<String, Object>> monthPlan(HttpSession session) {
        List<Gather> listAll = gatherMapper.getAllByEmail((String)session.getAttribute("loginId"));

        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();

        HashMap<String, Object> hash = new HashMap<>();

        for (int i = 0; i < listAll.size(); i++) {
            hash.put("id", listAll.get(i).getId());
            hash.put("title", listAll.get(i).getTitle());
            hash.put("content", listAll.get(i).getContent());
            hash.put("start", listAll.get(i).getStartDate());
            hash.put("end", listAll.get(i).getEndDate());

            jsonObj = new JSONObject(hash);
            jsonArr.add(jsonObj);
        }
        log.info("jsonArrCheck: {}", jsonArr);
        return jsonArr;
    }
}
