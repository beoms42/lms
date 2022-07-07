package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface StatsMapper {
	
	List<Map<String,Object>> selectAvgScore();
	List<Map<String,Object>> selectDropRecord();
	List<Map<String,Object>> selectClassAverAge();
	List<Map<String,Object>> genderRate();
}
