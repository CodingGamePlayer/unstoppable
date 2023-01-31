package com.example.batisproject.controller.jungi;

import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.CategoryDTO;
import com.example.batisproject.dto.GatherDTO;
import com.example.batisproject.dto.LocationDTO;
import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.entity.Category;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.User;
import com.example.batisproject.service.category.CategoryService;
import com.example.batisproject.service.gather.GatherService;
import com.example.batisproject.service.location.LocationService;
import com.example.batisproject.service.user.UserService;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class GatherController {

    private final LocationService locationService;
    private final GatherService gatherService;
    private final CategoryService categoryService;
    private final AuthenticationForModel authenticationForModel;
    private final UserService userService;

    @GetMapping("/user/gather")
    public String gatherList(@RequestParam(value = "category", required = false, defaultValue = "")Integer category, Model model) {

        // point logic
        User user = new AuthenticationForModel().getAuthentication();

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("user", userDTO);

        // gather logic

        List<CategoryDTO> categoryList = categoryService.getAllMainCategory();
        List<GatherDTO> gatherList;
        LocationDTO locationDTO = locationService.getByUsername(user.getUsername());
        if(category == null) {
            gatherList = gatherService.getAllOtherList(user.getNickname(), user.getLocation());
        } else{
            gatherList = gatherService.getAllOtherList(category, user.getNickname(), user.getLocation());
        }
        model.addAttribute("location", locationDTO);
        model.addAttribute("gatherList", gatherList);
        model.addAttribute("categoryList", categoryList);
        return "gather/gatherList";
    }
    @GetMapping("/user/myGather")
    public String myGatherList(@RequestParam(value = "category", required = false, defaultValue = "")Integer category, Model model) {

        // point logic
        User user = new AuthenticationForModel().getAuthentication();

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("user", userDTO);

        LocationDTO locationDTO = locationService.getByUsername(user.getUsername());
        List<CategoryDTO> categoryList = categoryService.getAllMainCategory();
        List<GatherDTO> gatherList;

        if(category == null) {
            gatherList = gatherService.getAllMyList(user.getNickname(), user.getLocation());
        } else {
            gatherList = gatherService.getAllMyList(category, user.getNickname(), user.getLocation());
        }
        model.addAttribute("location", locationDTO);
        model.addAttribute("gatherList", gatherList);
        model.addAttribute("categoryList", categoryList);
        return "gather/myGatherList";
    }

    @GetMapping("/user/gatherDummyMaker")
    public String makeDummy() {
        User user = authenticationForModel.getAuthentication();
        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        return "/";
    }


    @GetMapping("/user/locationSearch")
    public String locationSearch(Model model) {
        // point logic
        User user = new AuthenticationForModel().getAuthentication();

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("user", userDTO);

        List<LocationDTO> locationList = locationService.getAll();
        model.addAttribute("locationList", locationList);
        return "gather/locationSearchTest";
    }

    @PostMapping("/user/locationSubmit")
    public String locationSubmit(@RequestParam String lid , Model model) {
        // point logic
        User user = new AuthenticationForModel().getAuthentication();

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("user", userDTO);
        log.info("-----------------------");
        log.info("lid : " + lid);
        List<LocationDTO> locationList = locationService.getAll();
        model.addAttribute("locationList", locationList);
        return "gather/locationSearchTest";
    }



    @Autowired
    public GatherController(GatherService gatherService, CategoryService categoryService, AuthenticationForModel authenticationForModel, UserService userService, LocationService locationService) {
        this.gatherService = gatherService;
        this.categoryService = categoryService;
        this.authenticationForModel = authenticationForModel;
        this.userService = userService;
        this.locationService = locationService;
    }
}
