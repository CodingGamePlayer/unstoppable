package com.example.batisproject.controller.jungi;

import com.example.batisproject.annotaion.CurrentUser;
import com.example.batisproject.controller.AuthenticationForModel;
import com.example.batisproject.dto.*;
import com.example.batisproject.entity.Category;
import com.example.batisproject.entity.Gather;
import com.example.batisproject.entity.GatherComment;
import com.example.batisproject.entity.User;
import com.example.batisproject.mapper.jungi.GatherCommentMapper;
import com.example.batisproject.service.category.CategoryService;
import com.example.batisproject.service.gather.GatherService;
import com.example.batisproject.service.location.LocationService;
import com.example.batisproject.service.user.UserService;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class GatherController {

    private final LocationService locationService;
    private final GatherService gatherService;
    private final CategoryService categoryService;
    private final GatherCommentMapper gatherCommentMapper;
    private final AuthenticationForModel authenticationForModel;
    private final UserService userService;

    @GetMapping("/user/gather")
    public String gatherList(@RequestParam(value = "category", required = false, defaultValue = "")Integer category,
                             @RequestParam(value = "viewMode", required = false, defaultValue = "")String viewMode,
                             Model model, @CurrentUser User user,
                             @Valid PageRequestDTO pageRequestDTO,
                             BindingResult bindingResult) {

        // point logic
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        model.addAttribute("user", userDTO);

        // gather logic

        List<CategoryDTO> categoryList = categoryService.getAllMainCategory();
        PageResponseDTO<GatherResponseDTO> gatherList;
        LocationDTO locationDTO = locationService.getByUsername(userDTO.getUsername());

        PageRequestDTO revisedPageDTO = setKeyword(pageRequestDTO, bindingResult);
        revisedPageDTO.setCategory(category);
        revisedPageDTO.setLocation(userDTO.getLocation());
        revisedPageDTO.setNickname(userDTO.getNickname());
        gatherList = gatherService.getAllOtherList(revisedPageDTO);

        model.addAttribute("location", locationDTO);
        model.addAttribute("gatherList", gatherList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", category);
        model.addAttribute("viewMode", viewMode);
        return "gather/gatherList";
    }
    @GetMapping("/user/myGather")
    public String myGatherList(@RequestParam(value = "category", required = false, defaultValue = "")Integer category,
                               @RequestParam(value = "viewMode", required = false, defaultValue = "")String viewMode,
                               Model model, @CurrentUser User user,
                               @Valid PageRequestDTO pageRequestDTO,
                               BindingResult bindingResult) {

        // point logic

        UserDTO userDTO = userService.existsByEmail(user.getUsername());

        model.addAttribute("user", userDTO);

        LocationDTO locationDTO = locationService.getByUsername(userDTO.getUsername());
        List<CategoryDTO> categoryList = categoryService.getAllMainCategory();
        PageResponseDTO<GatherResponseDTO> gatherList;

        PageRequestDTO revisedPageDTO = setKeyword(pageRequestDTO, bindingResult);

        revisedPageDTO.setCategory(category);
        revisedPageDTO.setLocation(userDTO.getLocation());
        revisedPageDTO.setNickname(userDTO.getNickname());
        gatherList = gatherService.getAllMyList(revisedPageDTO);

        model.addAttribute("location", locationDTO);
        model.addAttribute("gatherList", gatherList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", category);
        model.addAttribute("viewMode", viewMode);
        return "gather/myGatherList";
    }


    @GetMapping("/user/gatherDummyMaker")
    public String makeDummy(Model model) {

        List<CategoryDTO> categoryList = categoryService.getAllMainCategory();

        User user = authenticationForModel.getAuthentication();
        UserDTO userDTO = userService.existsByEmail(user.getUsername());
        for(int idx = 0; idx < 3; idx++) {
            for(int i = 0; i < 18; i++) {
                GatherDTO gatherDTO = GatherDTO.builder().user(Long.valueOf(user.getId()))
                        .location(user.getLocation())
                        .category(categoryList.get(i).getId())
                        .title("테스트용 "+i+"번째 모임글")
                        .content("test content 입니다.")
                        .startDate(LocalDateTime.now().plusDays(i+1))
                        .endDate(LocalDateTime.now().plusDays(i+3))
                        .peopleNum(4)
                        .point(Long.valueOf(10000)).build();
                        int insertG_id = gatherService.create(gatherDTO);

                GatherCommentDTO gatherCommentDTO = GatherCommentDTO.builder()
                        .gather(Long.valueOf(insertG_id))
                        .user(Long.valueOf(user.getId()))
                        .role(4)
                        .build();
                gatherCommentMapper.insert(new ModelMapper().map(gatherCommentDTO, GatherComment.class));
            }
        }
        model.addAttribute("user", userDTO);
        return "user/main";
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

    PageRequestDTO setKeyword(PageRequestDTO pageRequestDTO, BindingResult bindingResult) {

        if(pageRequestDTO.getKeyword() != null)
            pageRequestDTO.setKeyword(pageRequestDTO.getKeyword());

        if (bindingResult.hasErrors()){

            pageRequestDTO = PageRequestDTO.builder().build();
        }

        return pageRequestDTO;
    }



    @Autowired

    public GatherController(LocationService locationService, GatherService gatherService, CategoryService categoryService, GatherCommentMapper gatherCommentMapper, AuthenticationForModel authenticationForModel, UserService userService) {
        this.locationService = locationService;
        this.gatherService = gatherService;
        this.categoryService = categoryService;
        this.gatherCommentMapper = gatherCommentMapper;
        this.authenticationForModel = authenticationForModel;
        this.userService = userService;
    }
}
