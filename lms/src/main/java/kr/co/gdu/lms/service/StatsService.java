package kr.co.gdu.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.mapper.StatsMapper;
import lombok.extern.slf4j.Slf4j;

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
		dropList = statsmapper.selectDropRecord();
		return dropList;
	}
	public List<Map<String,Object>> selectClassAverAge(){
		List<Map<String,Object>> ageList = new ArrayList<>();
		ageList = statsmapper.selectClassAverAge();
		return ageList;
	}
	public List<Map<String,Object>> genderRate(){
		List<Map<String,Object>> genderList = new ArrayList<>();
		genderList = statsmapper.selectGenderRate();
		return genderList;
	}
	public List<Map<String,Object>> addemployment(String lectureName){
		List<Map<String,Object>> genderList = new ArrayList<>();
		genderList = statsmapper.selectEmployment(lectureName);
		return genderList;
	}
	public List<Map<String,Object>> addPerClass(){
		List<Map<String,Object>> classList = new ArrayList<>();
		classList = statsmapper.selectPerClass();
		return classList;
	}
	public List<Map<String,Object>> addGraduate(){
		List<Map<String,Object>> graduateList = new ArrayList<>();
		graduateList = statsmapper.selectGraduate();
		return graduateList;
	}
	public String selectLectureName(String loginId) {
		String lectureName = statsmapper.selectLectureName(loginId);
		return lectureName;
	}

}
	
