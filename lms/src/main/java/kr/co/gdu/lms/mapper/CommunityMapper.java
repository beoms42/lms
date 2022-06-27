package kr.co.gdu.lms.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.*;

@Mapper
public interface CommunityMapper {
	
	// 영인 - qna리스트 가져오기
	List<Qna> selectQnaList();
	
	// 희원 - Community리스트 가져오기
	List<Community> selectCommunityList(Map<String, Integer> map);
	
	// 희원 - Community 게시글 총 개수
	int countCommunityList();
	
	// 희원 - Community 게시글 1개 선택
	Community selectCommunityOne(int communityNo);
	
	// 희원 - Community 게시글 1개의 file List 가져오기
	List<String> selectCommunityFileOne(int communityNo);
}
