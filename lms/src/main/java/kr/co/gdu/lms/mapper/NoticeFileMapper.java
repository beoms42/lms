package kr.co.gdu.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.NoticeFile;

@Mapper
public interface NoticeFileMapper {
	
	// 공지사항 파일 
	NoticeFile selectNoticeFile(String noticeNo);
	
	// 공지사항 파일 삭제
	int deleteNoticeFile(String noticeNo);
	
	// 공지사항 파일 추가
	int insertNoticeFile(NoticeFile noticeFile);
	
}
