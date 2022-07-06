package kr.co.gdu.lms.component;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.LectureMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LectureScheduler {
	@Autowired LectureMapper lectureMapper;
	
	@Scheduled(cron = "0 0 0 * * *") 
	public void modifyActiveByEndLecture() {
		
		List<Map<String, Object>> EndLectureList = lectureMapper.selectLectureListByEndDate();
		
		int lectureRow = 0;
		for(Map m : EndLectureList) {
			if(m.get("lectureActive").equals("1")) {
				lectureRow += lectureMapper.updateActiveByEndLecture((String)m.get("lectureName"));
			}
		}
		
		log.debug(CF.LCH + "총 " + lectureRow + "개의 강의가 비활성화 되었습니다.");
	}
}
