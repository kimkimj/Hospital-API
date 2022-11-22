package com.example.hospital.parser;

import com.example.hospital.domain.Hospital;

import java.time.LocalDateTime;
import java.util.Arrays;

public class HospitalParser implements Parser<Hospital> { // 인스턴스를 상속
    @Override
    public Hospital parse(String str) { // 인스턴스 오버라이딩

        String[] row = str.split("\",\""); // \은 뒤에 오는 특수문자를 그대로 받아들이게 합니다. 의미는 ","을 기준으로 자르라는 뜻입니다.
        System.out.println(Arrays.toString(row));

        Hospital hospital = new Hospital();

        hospital.setId(Integer.parseInt(row[0].replace("\"","")));// "," 을 기준으로 나눴기 때문에 첫번째 열과 마지막 열에 특수문자가 제거되지 못했습니다.
        // 제거를 해주고 첫번째 열은 int형 id이기 때문에 "을 빈칸으로 변경(즉, 삭제)하고
        // int형으로 변경해줍니다.
        hospital.setOpenServiceName(row[1]); // Hospital에 저장된 변수를 set으로 저장해줍니다.
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(row[3]));
        hospital.setManagementNumber(row[4]);

        // 날짜 자르기
        int year = Integer.parseInt(row[5].substring(0, 4)); // year에 5번째 배열에 있는 String 0번째에서 3번째까지 수를 저장 == yyyy
        int month = Integer.parseInt(row[5].substring(4, 6)); // month에 5번째 배열에 있는 String 4, 5번째 수 저장 == mm
        int day = Integer.parseInt(row[5].substring(6, 8)); // day에 6, 7번째 수 저장 == dd
        //System.out.printf("%d %d %d \n", year, month, day); <- 확인용

        hospital.setLicenseDate(LocalDateTime.of(year, month, day,0,0,0)); // LocalDateTime의 of메서드를 통해서 날짜와 시간 설정
        hospital.setBusinessStatus(Integer.parseInt(row[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(row[9]));
        hospital.setPhone(row[15]);
        hospital.setFullAddress(row[18]);
        hospital.setRoadNameAddress(row[19]);
        hospital.setHospitalName(row[21]);
        hospital.setBusinessTypeName(row[25]);
        hospital.setHealthcareProviderCount(Integer.parseInt(row[29]));
        hospital.setPatientRoomCount(Integer.parseInt(row[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(row[31]));
        hospital.setTotalAreaSize(Float.parseFloat(row[32].replace("\"",""))); // 마지막 문자열도 첫번째와 마찬가지로 "," 분리되지 않아서 추가 작업
        // "를 없애주고 float로 변경해서 TotalAreaSize(총 면적)에 저장

        return hospital;
    }

}