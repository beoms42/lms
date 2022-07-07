package kr.co.gdu.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.mapper.StatsMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class StatsService {
	@Autowired private StatsMapper statsmapper;
	public List<Map<String,Object>> selectAvgScore(){
		List<Map<String,Object>> avgList = new ArrayList<Map<String,Object>>();
		avgList = statsmapper.selectAvgScore();
		
		return avgList;
	}
	public List<Map<String,Object>> addDropRecord(){
		List<Map<String,Object>> dropList = new ArrayList<>();
		List<Map<String,Object>> lectureList = new ArrayList<>();
		dropList = statsmapper.selectDropRecord();
		lectureList = statsmapper.selectlectureCount();
		for(int i=0; i<lectureList.size(); i++) {
			if(lectureList.get(i).get("lectureName") == dropList.get(i)) {
				
			}
		}
		return dropList;
	}
}
	
