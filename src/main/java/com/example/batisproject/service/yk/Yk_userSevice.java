package com.example.batisproject.service.yk;

import com.example.batisproject.dto.UserDTO;

public interface Yk_userSevice {
    
    //유저아이디로 닉네임 가져오기 코멘트에 이름뿌려줄때 필요함
    UserDTO idToNick(Long userId);
}
