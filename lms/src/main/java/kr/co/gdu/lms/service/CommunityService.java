package kr.co.gdu.lms.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.CommunityMapper;
import kr.co.gdu.lms.mapper.LoginMapper;
import kr.co.gdu.lms.vo.Community;
import kr.co.gdu.lms.vo.Lecture;
import kr.co.gdu.lms.vo.Notice;
import kr.co.gdu.lms.vo.Qna;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class CommunityService {
	@Autowired private CommunityMapper communityMapper;
	
	
	public Map<String, Object> getCommunityOne(Map<String, Object> map) {
		
		int communityNo = (int)map.get("communityNo");
		log.debug(CF.PHW+"CommunityService.getCommunityOne communityNo : "+communityNo+CF.RS );
		
		Community community = communityMapper.selectCommunityOne(communityNo);
		List<String> communityFileList = communityMapper.selectCommunityFileOne(communityNo);
		
		log.debug(CF.PHW+"CommunityService.getCommunityOne community : "+community+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityOne communityFileList : "+communityFileList+CF.RS );
		
		
		map.put("communityNo", communityNo);
		map.put("community", community);
		map.put("communityFileList", communityFileList);
		
		
		return map;
	}
	
	
	// 희원 - communityList
	public Map<String, Object> getCommunityList(int currentPage, int rowPerPage){
		int startRow = (currentPage - 1)* rowPerPage;
		
		log.debug(CF.PHW+"CommunityService.getCommunityList. currentPage : "+currentPage+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityList. rowPerPage : "+rowPerPage+CF.RS );
		log.debug(CF.PHW+"CommunityService.getCommunityList. startRow : "+startRow+CF.RS );
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("rowPerPage", rowPerPage);
		List<Community> communityList = communityMapper.selectCommunityList(map);
		
		int totalCount = communityMapper.countCommunityList();
		int lastPage = (int)Math.ceil((double)totalCount/(double)rowPerPage);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("communityList", communityList);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
		
	}
	
	// 영인 - qna리스트
	public List<Qna> getQnaList() {
		List<Qna> qnaList = communityMapper.selectQnaList();
		
		return qnaList;
	}
	
}
