package kr.co.gdu.lms.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.NoticeFileMapper;
import kr.co.gdu.lms.mapper.NoticeMapper;
import kr.co.gdu.lms.vo.AddNoticeForm;
import kr.co.gdu.lms.vo.Notice;
import kr.co.gdu.lms.vo.NoticeFile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class NoticeService {
	@Autowired private NoticeMapper noticeMapper;
	@Autowired private NoticeFileMapper noticeFileMapper;
	
	// 공지사항 전체 리스트
	public Map<String, Object> getNoticeListByPage(Map<String, Object> map) {
		
		log.debug(CF.OHI+"NoticeService.getNoticeListByPage map : "+map+CF.RS);
		
		int totalCnt = noticeMapper.selectNoticeTotalCnt(); // 총 개수
		int rowPerPage = (int)map.get("rowPerPage"); // 한 페이지당 행 개수
		int beginRow = ((int)map.get("currentPage")-1)*10; // 시작 row
		
		int lastPage = totalCnt/ rowPerPage; // 마지막 페이지
		if(totalCnt % rowPerPage != 0) { // 나눠떨어지지않는다면
			lastPage += 1; // 마지막페이지 +1 해주기
		}
		
		map.put("beginRow", beginRow);
		
		// 공지사항 리스트
		List<Notice> list = noticeMapper.selectNoticeListByPage(map);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list",list);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
	
	// 공지사항 상세보기
	public Map<String, Object> getNoticeOne(int noticeNo) {
		
		// 해당 noticeNo의 공지사항 상세보기 
		Notice notice = noticeMapper.selectNoticeOne(noticeNo);
		List<NoticeFile> list = noticeFileMapper.selectNoticeFile(noticeNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("notice", notice);
		map.put("list", list);
		
		return map;
	}
	
	// 공지사항 입력
	public int addNotice(AddNoticeForm addNoticeForm, String path) {
		
		Notice notice = new Notice();
		notice.setNoticeTitle(addNoticeForm.getNoticeTitle());
		notice.setNoticeContent(addNoticeForm.getNoticeContent());
		notice.setNoticePw(addNoticeForm.getNoticePw());
		notice.setLoginId(addNoticeForm.getLoginId());
		
		// 공지사항 입력
		log.debug(CF.OHI+"NoticeService.addNotice 입력 전 NoticeNo : "+notice.getNoticeNo()+CF.RS);
		
		int row = noticeMapper.insertNotice(notice);
		
		log.debug(CF.OHI+"NoticeService.addNotice 입력 후 NoticeNo : "+notice.getNoticeNo()+CF.RS);
		
		if(addNoticeForm.getNoticeFileList() != null
				&& addNoticeForm.getNoticeFileList().get(0).getSize() > 0
				&& addNoticeForm.getNoticeFileList().size() > 0
				&& row == 1) {
			
			log.debug(CF.OHI+"NoticeService.addNotice : "+", 첨부된 파일이 있습니다."+CF.RS);
			
			for(MultipartFile m : addNoticeForm.getNoticeFileList()) {
				NoticeFile noticeFile = new NoticeFile();
				
				String fileOriginName = m.getOriginalFilename();
				// 확장자
				String ext = fileOriginName.substring(fileOriginName.lastIndexOf("."));
				
				// 새로운 이름 부여 (이름 중복 방지위해) 
				String fileName = UUID.randomUUID().toString();
				
				fileName = fileName.replace("-", "");
				fileName += ext;
				
				// noticeFile 데이터 바인딩
				noticeFile.setNoticeNo(notice.getNoticeNo());
				noticeFile.setLoginId(addNoticeForm.getLoginId());
				noticeFile.setNoticeFileName(fileName);
				noticeFile.setNoticeFileOriginName(fileOriginName);
				noticeFile.setNoticeFileType(m.getContentType());
				noticeFile.setNoticeFileSize(m.getSize());
				
				log.debug(CF.OHI+"NoticeService.addNotice noticeFile : "+noticeFile+CF.RS);
				
				// db 에 파일 정보 입력
				noticeFileMapper.insertNoticeFile(noticeFile);
				
				// 파일 경로에 저장
				try {
					m.transferTo(new File(path+fileName));
				} catch (Exception e) {
					e.printStackTrace();
					
					// 예외 잡으면 @transactional 작동안하니까 런타임 가능한 예외 발생
					throw new RuntimeException();
				} 
				
			}
		}
		
		return row;
	}
}
