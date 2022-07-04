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
	CommunityMember selectCommunityOne(int communityNo);
	
	// 희원 - Community 게시글 1개의 file List 가져오기
	List<CommunityFile> selectCommunityFileOne(int communityNo);
	
	// 희원- Community 입력
	int insertCommunity(Community community);
	
	// 희원 - CommunityFile 입력 / 수정-입력
	int insertCommunityFile(CommunityFile communityFile);
	
	// 희원 - Community 삭제
	int deleteCommunity(int communityNo, String communityPw);
	
	// 희원 - CommunityFile 삭제
	int deleteCommunityFileList(int communityNo);
	
	// 희원 - CommunityFileName select
	List<String> selectCommunityfileNameList(int communityNo);
	
	// 희원 - Community 수정
	int updateCommunity(Community community);
	
	// 희원 - CommunityFile 수정-삭제
	int deleteCommunityFileOne(int communityFileNo);
	
}
