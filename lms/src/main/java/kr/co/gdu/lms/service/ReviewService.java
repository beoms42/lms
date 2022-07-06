package kr.co.gdu.lms.service;

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
}
