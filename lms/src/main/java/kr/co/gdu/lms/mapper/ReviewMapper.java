package kr.co.gdu.lms.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.EducationReview;

@Mapper
public interface ReviewMapper {
	int selectEducationNo(Map<String,Object> paramMap);
	void insertReview(EducationReview educationreview);
}
