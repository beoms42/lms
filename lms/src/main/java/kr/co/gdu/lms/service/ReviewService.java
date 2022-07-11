package kr.co.gdu.lms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.ReviewMapper;
import kr.co.gdu.lms.vo.EducationReview;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReviewService {
	@Autowired private ReviewMapper reviewMapper;
	public int selectEducationNo(Map<String,Object> paramMap) {
		log.debug(CF.GMC + paramMap.get("loginId")+CF.RS);
		log.debug(CF.GMC + paramMap.get("lectureName")+CF.RS);
		int educationNo = reviewMapper.selectEducationNo(paramMap);
		return educationNo;
	}
	public void insertReview(EducationReview educationreview) {
		reviewMapper.insertReview(educationreview);
	}
	//종강 강의별 별점 리스트
	public List<Map<String,Object>>selectEducationReviewList(){
		List<Map<String, Object>>list = (List<Map<String, Object>>) reviewMapper.selectEducationReviewList();
		log.debug(CF.PSH+"LectureService.selectEducationReviewList:"+list+CF.RS);
	
	return list;

	}
	
	//강의 리뷰 리스트
	public List<Map<String, Object>>selectLectureReviewList(String lectureName){
		List<Map<String,Object>>list = (List<Map<String, Object>>)reviewMapper.selectLectureReviewList(lectureName);
		log.debug(CF.PSH+"ReviewService.selectLectureReviewList:"+list+CF.RS);
		
		return list;
	}

	
	
}
