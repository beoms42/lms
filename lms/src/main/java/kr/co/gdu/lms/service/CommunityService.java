package kr.co.gdu.lms.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.mapper.CommunityMapper;
import kr.co.gdu.lms.mapper.LoginMapper;
import kr.co.gdu.lms.vo.Lecture;
import kr.co.gdu.lms.vo.Qna;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class CommunityService {
	@Autowired private CommunityMapper communityMapper;
	
	// 영인 - qna리스트
	public List<Qna> selectQnaList() {
		List<Qna> qnaList = communityMapper.selectQnaList();
		
		return qnaList;
	}
}
