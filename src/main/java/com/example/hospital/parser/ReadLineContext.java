package com.example.hospital.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadLineContext<T> {
    private Parser<T> parser;

    public ReadLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    // 파일이 들어오면 라인을 읽는다.
    public List<T> readByLine(String filename) throws IOException {
        // Buffer는 삽 느낌?
        List<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename) // fileReader형의 filename을 reader에 넣는다.
        );
        String str;
        // 예외처리
        while ((str = reader.readLine()) != null) {
            try {
                result.add(parser.parse(str));
            } catch (Exception e) {
                System.out.printf("파싱중 문제가 생겨 이 라인은 넘어갑니다. 파일내용:%s", str);
            }
        }
        // 종료해주기
        reader.close();
        return result;
    }

}