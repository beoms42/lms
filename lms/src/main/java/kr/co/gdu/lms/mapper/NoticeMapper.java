package kr.co.gdu.lms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.Notice;

@Mapper
public interface NoticeMapper {
	
	// 공지사항 총 개수
	int selectNoticeTotalCnt(); 
	
	// 공지사항 리스트
	List<Notice> selectNoticeListByPage(Map<String, Object> map);
	
	// 공지사항 상세보기
	Notice selectNoticeOne(int noticeNo);
	
	// 공지사항 입력
	int insertNotice(Notice notice);
	
	// 공지사항 삭제
	int deleteNotice(int noticeNo);
	
	// 공지사항 수정
	int updateNotice(Notice notice);
}
