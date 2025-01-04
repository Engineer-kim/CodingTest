package com.brick.codingtest.Solution5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RandomResponse {

    private static final String SERVER_URL = "http://codingtest.brique.kr:8080/random";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Integer> getRandomResponses(int callNumber) {
        Map<String, Integer> responseCountMap = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < callNumber; i++) {
            try {
                ResponseEntity<String> responseEntity = restTemplate.exchange(SERVER_URL, HttpMethod.GET, null, String.class);
                String response = responseEntity.getBody();
                responseCountMap.put(response, responseCountMap.getOrDefault(response, 0) + 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseCountMap;
    }
    
    //출력 포맷팅에 맞추기 위함
    public String printFormattedResponse(Map<String, Integer> responseCountMap) {
        StringBuilder result = new StringBuilder();
        int totalCount = 0;

        for (Integer count : responseCountMap.values()) {
            totalCount += count;
        }

        System.out.println("Total count: " + totalCount);

        for (String jsonResponse : responseCountMap.keySet()) {
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                int count = responseCountMap.get(jsonResponse);
                String quote = jsonNode.get("quote").asText();
                String id = jsonNode.get("id").asText();
                result.append(String.format("count: %d {'id': %s, 'quote': '%s'}%n", count, id, quote));
            } catch (Exception e) {
                result.append("JSON 파싱 오류: ").append(e.getMessage()).append("\n");
            }
        }

        return result.toString(); // 포맷팅된 결과 문자열 반환
    }
}
