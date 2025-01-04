package com.brick.codingtest.Solution1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Calculatevalue {
    public static String calculateValues() {
        InputStream inputStream = Calculatevalue.class.getResourceAsStream("/static/sample.csv");
        if (inputStream == null) {
            return "지정한 CSV 파일이 존재하지 않습니다.";
        }

        List<String> stringList = new ArrayList<>();
        int totalNumberOfLines = 0;
        int calculatedLines = 0;
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                totalNumberOfLines++;
                String[] values = currentLine.split(",");
                List<Double> numberList = new ArrayList<>();
                boolean isNumber = false;

                for (String value : values) {
                    try {
                        numberList.add(Double.parseDouble(value));
                    } catch (NumberFormatException e) {
                        stringList.add(value);
                        isNumber = true;
                    }
                }
                if (!isNumber && !numberList.isEmpty()) {
                    double min = numberList.stream().mapToDouble(Double::doubleValue).min().orElse(Double.NaN);
                    double max = numberList.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
                    double sum = numberList.stream().mapToDouble(Double::doubleValue).sum();
                    double avg = sum / numberList.size();
                    double bunsan = numberList.stream().mapToDouble(v -> Math.pow(v - avg, 2)).sum() / numberList.size();
                    double pyogunpyeoncha = Math.sqrt(bunsan);
                    double middleValue;

                    numberList.sort(Double::compareTo);
                    if (numberList.size() % 2 == 0) {
                        middleValue = (numberList.get(numberList.size() / 2 - 1) + numberList.get(numberList.size() / 2)) / 2;
                    } else {
                        middleValue = numberList.get(numberList.size() / 2);
                    }
                    result.append(String.format("최소값: %.2f%n", min));
                    result.append(String.format("최대값: %.2f%n", max));
                    result.append(String.format("합계: %.2f%n", sum));
                    result.append(String.format("평균: %.2f%n", avg));
                    result.append(String.format("표준편차: %.2f%n", pyogunpyeoncha));
                    result.append(String.format("중간값: %.2f%n", middleValue));
                    calculatedLines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output = String.format("%s---------------------------------------------\n" +
                        "The total number of lines: %d\n" +
                        "The calculated lines: %d\n" +
                        "The error values: %d\n",
                result.toString(), totalNumberOfLines, calculatedLines, stringList.size());
        return output;

    }
}
