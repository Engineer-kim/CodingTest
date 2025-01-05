package com.brick.codingtest.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static com.brick.codingtest.Solution2.PinPong.PORT;

@Controller
@RequestMapping("/api2")
public class PinPongController {
    /**
     * 서버 실행후
     * 클라이언트 프로그램이 서버 프로그램에 Ping 을 보내면 Pong 을 응답받는 프로그램 작성하시오
     *
     * 문제에 대한 결과(엔드포인트)입니다
     *
     * http://localhost:8080/api2/pingStart 호출 하시어 확인 부탁드리겠습니다 감사합니다
     *
     *  이외의 인풋 입력시, 입력받은 파라미터 그대로 리턴
     *  ex) Ping - > Pong 응답 , ping -> ping 응답
     *
     * */
    @GetMapping("/pingStart")
    public String pingTest() {
        return "ping_test";
    }

}
