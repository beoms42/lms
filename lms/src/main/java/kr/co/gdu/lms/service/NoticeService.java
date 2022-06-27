package kr.co.gdu.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.NoticeFileMapper;
import kr.co.gdu.lms.mapper.NoticeMapper;
import kr.co.gdu.lms.vo.Notice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class NoticeService {
	@Autowired private NoticeMapper noticeMapper;
	@Autowired private NoticeFileMapper noticeFileMapper;
	
	public Map<String, Object> getNoticeListByPage(Map<String, Object> map) {
		
		log.debug(CF.OHI+"NoticeService.getNoticeListByPage map : "+map+CF.RS);
		
		int totalCnt = noticeMapper.selectNoticeTotalCnt(); // 총 개수
		int rowPerPage = (int)map.get("rowPerPage"); // 한 페이지당 행 개수
		int beginRow = ((int)map.get("currentPage")-1)*10; // 시작 row
		
		int lastPage = totalCnt/ rowPerPage; // 마지막 페이지
		if(totalCnt % rowPerPage != 0) {
			lastPage += 1;
		}
		
		map.put("beginRow", beginRow);
		map.put("lastPage", lastPage);
		
		// 공지사항 리스트
		List<Notice> list = noticeMapper.selectNoticeListByPage(map);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("list",list);
		returnMap.put("lastPage", lastPage);
		
		return returnMap;
	}
}
