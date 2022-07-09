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
	
	// 출결상태 모두 출석으로 변경
	public void modifyAttendanceListAll(int scheduleNo) {
		
		int row = hyeinMapper.updateAttendanceListAll(scheduleNo);
		log.debug(CF.OHI+"HyeinService.modifyAttendanceListAll row : "+row+CF.RS);
	}
	
	// 강사별 맡고있는 강의의 학생이름, 출결, 강의 이름 , 출결날짜 뽑기
	public Map<String, Object> getAttendanceList(Map<String, Object> map) {
		
		log.debug(CF.OHI+"HyeinService.modifyAttendanceList map"+map+CF.RS);
		
		// 강의명
		String lectureName = hyeinMapper.selectLectureName(map);
		
		// 출결리스트
		List<Map<String, Object>> attendanceList = hyeinMapper.selectAttendance(map);
		
		// 출결데이터 없을때 띄워줄 현재 날짜
		log.debug(CF.OHI+"HyeinService.modifyAttendanceList attendanceList : "+attendanceList+CF.RS);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("lectureName", lectureName);
		returnMap.put("attendanceList", attendanceList);
		
		return returnMap;
	}
	
	// 출결 수정
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
