package com.example.batisproject.apicontroller.user;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.service.MailSendService;
import com.example.batisproject.service.user.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Slf4j
@Tag(name = "인증을 담당하는 controller",
        description = "Anonymous 권한을 가진 유저들이 인증 단계를 담당함.")
@RestController
public class UserApiController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MailSendService mailSendService;

    @Operation(summary = "Login method", description = "Login을 담당하는 method")
    @PostMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {

        log.info("UserDTO : " + userDTO.toString());
        int result = userService.insert(userDTO);
        if (result == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Signup method", description = "회원가입을 담당하는 method")
    @PostMapping(value = "/api/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        int insert = userService.insert(userDTO);

        if (insert == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "MailCheck method", description = "인증메일을 보내는 method")
    @PostMapping(value = "/api/mailcheck", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mailCheck(@RequestBody HashMap<String, Object> user) {

        String username = (String) user.get("username");
        String authNum = mailSendService.joinEmail(username);
        if (authNum != null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.info("email : " + user.get("username"));
        log.info("checkNum : " + authNum);

        return ResponseEntity.status(HttpStatus.OK).body(authNum);
    }

//    @GetMapping("/api/pay")
//    public String pay() {
//
//        try {
//            URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setRequestProperty("Authorization", "KakaoAK 305529f33234bb93678608a317d8889c");
//            httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//            // 서버에 전해 줄것이 있는지 없는지 설정이며 HttpURLConnection 의 기본값으로 input은 true로 되어져 있음.
//            httpURLConnection.setDoOutput(true);
//
//            String parameter = "cid=TC0ONETIME" +
//                    "&partner_order_id=partner_order_id" +
//                    "&partner_user_id=partner_user_id" +
//                    "&item_name=초코파이" +
//                    "&quantity=1" +
//                    "&total_amount=2200" +
//                    "&vat_amount=200" +
//                    "&tax_free_amount=0" +
//                    "&approval_url=https://developers.kakao.com/success" +
//                    "&fail_url=https://developers.kakao.com/fail" +
//                    "&cancel_url=https://developers.kakao.com/cancel";
//
//            // 값을 보내기 위해서 사용함.
//            OutputStream outputStream = httpURLConnection.getOutputStream();
//            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//            //데이터를 바이트 값으로 변경
//            dataOutputStream.writeBytes(parameter);
//            dataOutputStream.close();
//
//            // 송신 결과를 int 값으로 받음.
//            int result = httpURLConnection.getResponseCode();
//
//            InputStream inputStream;
//
//            if (result == 200) {
//                inputStream = httpURLConnection.getInputStream();
//            } else {
//                // 에러 상태값은 아래와 같은 코드로 받아야함.
//                inputStream = httpURLConnection.getErrorStream();
//            }
//
//            // 결과 값을 읽기 위한 코드
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//            return bufferedReader.readLine();
//
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


}
