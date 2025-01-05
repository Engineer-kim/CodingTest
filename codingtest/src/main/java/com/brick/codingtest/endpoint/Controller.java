package com.brick.codingtest.endpoint;

import com.brick.codingtest.Solution1.Calculatevalue;
import com.brick.codingtest.Solution3.dto.EmployeeResult;
import com.brick.codingtest.Solution3.service.DBService;
import com.brick.codingtest.Solution5.RandomResponse;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import static com.brick.codingtest.Solution2.PinPong.PORT;
import static com.brick.codingtest.Solution678.Bracket.longestValidParentheses;

@RestController
@RequestMapping("/api")
public class Controller {

    private final DBService dbService;
    private final RandomResponse randomResponse;


    public Controller(DBService dbService, RandomResponse randomResponse) {
        this.dbService = dbService;
        this.randomResponse = randomResponse;
    }

    /**
     * 서버 실행후
     * 아래 csv 파일을 읽어서 필요한 결과를 출력하시오
     *
     * 문제에 대한 결과(엔드포인트)입니다
     *
     * http://localhost:8080/api/calculate 호출 하시어 확인 부탁드리겠습니다 감사합니다
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
     * http://localhost:8080/api/dbResult 호출 하시어 확인 부탁드리겠습니다 감사합니다
     * */
    @GetMapping("/dbResult")
    public List<EmployeeResult> getDBResults() {
        List<EmployeeResult> results;
        results = dbService.getData();
        System.out.println("Results: " + results);
        return results;
    }

    /**
     * 서버 실행후
     * 호출할 때마다 랜덤한 response 를 주는 서버가 있다. 이 서버를 100번 호출하여 각각의 결과
     *
     * 문제에 대한 결과(엔드포인트)입니다
     *
     * http://localhost:8080/api/inYongGu 호출 하시어 확인 부탁드리겠습니다 감사합니다
     * */
    @GetMapping("/inYongGu")
    public String getRandomResponses() {
        Map<String, Integer> responses = randomResponse.getRandomResponses(100);
        return randomResponse.printFormattedResponse(responses);
    }

    /**
     * 서버 실행후
     * 괄호가 알맞게 짝지어진 가장 긴 부분의 길이를 구하시오. ‘(‘와 ‘)’로만 이루어진 문자열에서, 괄호
     * 가 알맞게 짝지어진 가장 긴 부분의 길이를 구한다. “(()”의 경우 가장 긴 유효한 부분은 “()” 이므로
     *
     * 문제에 대한 결과(엔드포인트)입니다
     *
     *http://localhost:8080/api/bracket?input={소괄호 갯수}
     * 호출 하시어 확인 부탁드리겠습니다 감사합니다
     * */
    @GetMapping("/bracket")
    public String getBracketResponses(@RequestParam String input) {
        int result = longestValidParentheses(input);
        return String.format("Input: %s\nResult: %d", input, result);
    }



    @PostMapping("/ping")
    public String ping(@RequestParam String message) {
        try (Socket socket = new Socket("127.0.0.1", PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println(message);
            return in.readLine();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}

