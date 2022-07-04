package kr.co.gdu.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.HyeinMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HyeinService {
@Autowired HyeinMapper hyeinMapper;

	// 강사별 맡고있는 강의의 학생이름, 출결, 강의 이름 , 출결날짜 뽑기
	public List<Map<String, Object>> getAttendanceList(Map<String, Object> map) {
		
		log.debug(CF.OHI+"HyeinService.getAttendanceList map"+map+CF.RS);
		
		List<Map<String, Object>> attendanceList = hyeinMapper.selectAttendance(map);
		
		log.debug(CF.OHI+"HyeinService.getAttendanceList attendanceMap : "+attendanceList+CF.RS);
		
		return attendanceList;
	}
	
	public void modifyAttendace(List<String> list) {
		
		for(String s : list) {
			List<String> attendanceList = new ArrayList<>();
			
			String[] attendanceAry = s.split("/");
			
			for(String a : attendanceAry) {
				attendanceList.add(a);
			}
			
			String scheduleNo = attendanceList.get(0);
			String loginId = attendanceList.get(1);
			String attendanceRecord = attendanceList.get(2);
			log.debug(CF.OHI+"HyeinService.modifyAttendance  scheduleNo : "+scheduleNo+CF.RS);
			log.debug(CF.OHI+"HyeinService.modifyAttendance  loginId "+loginId+CF.RS);
			log.debug(CF.OHI+"HyeinService.modifyAttendance  attendanceRecord "+attendanceRecord+CF.RS);
			
			// attendance 테이블 변경위한 학생별 educationNo 들고오기
			int educationNo = hyeinMapper.selectEducationNo(loginId);
			
			Map<String, Object> map = new HashMap<>();
			map.put("scheduleNo", scheduleNo);
			map.put("educationNo", educationNo);
			map.put("attendanceRecord", attendanceRecord);
			
			int row = hyeinMapper.updateAttendance(map);
			log.debug(CF.OHI+"HyeinService. modifyAttendance row : "+row+CF.RS);
		}
	}
}
