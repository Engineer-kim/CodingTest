package com.brick.codingtest.endpoint;

import com.brick.codingtest.Solution1.Calculatevalue;
import com.brick.codingtest.Solution3.DB_Result;
import com.brick.codingtest.Solution5.RandomResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.brick.codingtest.Solution678.Bracket.longestValidParentheses;

@RestController
public class Controller {

    private final DB_Result dbResult;
    private final RandomResponse randomResponse;


    public Controller(DB_Result dbResult,RandomResponse randomResponse) {
        this.dbResult = dbResult;
        this.randomResponse = randomResponse;
    }

    /**
     * 서버 실행후
     * 아래 csv 파일을 읽어서 필요한 결과를 출력하시오
     *
     * 문제에 대한 결과(엔드포인트)입니다
     *
     * http://localhost:8080/calculate 호출 하시어 확인 부탁드리겠습니다 감사합니다
     * */
    @GetMapping("/calculate")
    public String calculateValues() {
        return Calculatevalue.calculateValues();
    }


    /**
     * 서버 실행후
     * 주어진 접속정보를 이용하여 DB에 접속해보면 아래 ER diagram의 형태로 된 Table들이 있다
     *
     * 문제에 대한 결과(엔드포인트)입니다
     *
     * http://localhost:8080/dbResult 호출 하시어 확인 부탁드리겠습니다 감사합니다
     * */
    @GetMapping("/dbResult")
    public List<String> getDBResults() {
        List<String> results = new ArrayList<>();
        try {
            results = dbResult.getData();
        } catch (Exception e) {
            e.printStackTrace();
            results.add("Error fetching data: " + e.getMessage());
        }
        return results;
    }

    /**
     * 서버 실행후
     * 호출할 때마다 랜덤한 response 를 주는 서버가 있다. 이 서버를 100번 호출하여 각각의 결과
     *
     * 문제에 대한 결과(엔드포인트)입니다
     *
     * http://localhost:8080/inYongGu 호출 하시어 확인 부탁드리겠습니다 감사합니다
     * */
    @GetMapping("/inYongGu")
    public String getRandomResponses() {
        Map<String, Integer> responses = randomResponse.getRandomResponses(100);
        return randomResponse.printFormattedResponse(responses);
    }

    @GetMapping("/bracket")
    public String getBracketResponses(@RequestParam String input) {
        int result = longestValidParentheses(input);
        return String.format("Input: %s\nResult: %d", input, result);
    }
}

