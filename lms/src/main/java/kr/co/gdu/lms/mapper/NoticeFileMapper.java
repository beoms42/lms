package kr.co.gdu.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.gdu.lms.vo.NoticeFile;
	
@Mapper
public interface NoticeFileMapper {
	
	// 공지사항 파일 
	List<NoticeFile> selectNoticeFile(int noticeNo);
	
	// 공지사항 파일 삭제
	int deleteNoticeFile(String noticeFileName);
	
	// 공지사항 파일 추가
	int insertNoticeFile(NoticeFile noticeFile);
	
	// 해당 공지사항 번호 공지사항 파일 이름 리스트
	List<String> selectNoticeFileNameList(int noticeNo);
}
