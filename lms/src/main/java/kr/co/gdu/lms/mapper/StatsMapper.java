package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface StatsMapper {
	
	List<Map<String,Object>> selectAvgScore(); // 반당 평균 점수
	List<Map<String,Object>> selectDropRecord(); // 반당 중탈 인원수
	List<Map<String,Object>> selectClassAverAge(); // 반당 나이 평균
	List<Map<String,Object>> selectGenderRate(); //남녀 성비
	List<Map<String,Object>> selectPerClass(); //반당 인원수
	List<Map<String,Object>> selectGraduate();
	List<Map<String,Object>> selectEmployment(String lectureName);
	String selectLectureName(String loginId);
	}
