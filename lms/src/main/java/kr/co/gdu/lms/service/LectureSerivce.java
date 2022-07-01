package kr.co.gdu.lms.service;

import java.io.File;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.LectureMapper;
import kr.co.gdu.lms.vo.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class LectureSerivce {
	@Autowired private LectureMapper lectureMapper;
	
	// 강의개설시 필요한 드롭다운 메뉴 : 강사 / 매니저 / 강의실 / 정원(강의실)
	public HashMap<String, Object> getMakeLectureNeed() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// 강사 가져오기
		List<Teacher> teacherList = lectureMapper.selectTeacher();
		
		// 매니저 가져오기
		List<Manager> managerList = lectureMapper.selectManager();
		
		// 강의실, 정원 가져오기
		List<LectureRoom> lectureRoomList = lectureMapper.selectLectureRoom();
		
		log.debug(CF.JYI+"서비스 - 강사 : "+teacherList+CF.RS);
		log.debug(CF.JYI+"서비스 - 매니저 : "+managerList+CF.RS);
		log.debug(CF.JYI+"서비스 - 강의실 : "+lectureRoomList+CF.RS);
		
		//내가 필요한건 이름의 리스트지...
		// 강사의 이름만 추출하자
		List<String> teacherNameList = new ArrayList<String>();
		for(Teacher t : teacherList) {
			teacherNameList.add(t.getTeacherName());
		}
		//매니저 이름만 추출
		List<String> managerNameList = new ArrayList<String>();
		for(Manager t : managerList) {
			managerNameList.add(t.getManagerName());
		}
		//강의실 이름은 두개를 추출해야 강의실 / 정원인데 걍 넣자
		
		map.put("teacherNameList", teacherNameList);
		map.put("managerNameList", managerNameList);
		map.put("lectureRoomList", lectureRoomList);
		
		return map;
	}
	
	// 강의개설 강의명중복 ajax
	public int selectLectureName(String lectureName) {
		int row = lectureMapper.selectLectureName(lectureName);
		
		return row;
	}
		
	public void addLecutre(Lecture lecture) {
		lectureMapper.insertLecture(lecture);
		
	}
	
	public List<Lecture> selectLectureList() {
		List<Lecture> lectList = lectureMapper.selectLectureList();
		log.debug(CF.JYI+"LectureService.selectLectureList LectureList : "+lectList+CF.RS);
		
		return lectList;
	}
	
	public List<Lecture> selectNotAcceptLectureList() {
		List<Lecture> lectList = lectureMapper.selectNotAcceptLectureList();
		log.debug(CF.JYI+"LectureService.selectNotAcceptLectureList LectureList : "+lectList+CF.RS);
		
		return lectList;
	}
	
	public void updateLectureActive(String lectureName) {
		lectureMapper.updateLectureActive(lectureName);
	}
	
	// 과목리스트 디스틴트로 가져오는 서비스
	public List<String> selectSubejctList() {
		List<String> subList = lectureMapper.selectSubejctList();
		
		return subList;
	}
	
	// 과목리스트 강의명으로 디스틴트로 가져오는 서비스
	public List<String> selectSubjectListByLectureName(String lectureName) {
		List<String> subList = lectureMapper.selectSubjectListByLectureName(lectureName);
		
		return subList;
	}
	
	// 강의에 과목리스트 넣기
	public void insertSubjectList(List<String> subList, String lectureName) {
		for(String s : subList) {
			lectureMapper.insertSubject(s, lectureName);
		}
		
	}
	
	// 수정용 - 이름으로 Lecture(1개) 받아오기
	public Lecture selectLectureOneByLectureName(String lectureName) {
		Lecture lect = lectureMapper.selectLectureOneByLectureName(lectureName);
		return lect;
	}
	
	// 수정용 - 이름으로 과목에 포함된 책 보기
	public List<Map<String, Object>> selectTextbookByLectureName(String lectureName) {
		List<Map<String, Object>> mapList = lectureMapper.selectTextbookByLectureName(lectureName);
		return mapList;
	}
	
	// 수정용 - 실제 액션
	public void updateLecture(Lecture lecture) {
		lectureMapper.updateLecture(lecture);
	}
	
// 시간표
	
	// 시간표리스트보기
	public Map<String, Object> getSheduleListByMonth(int y, int m, int loginLv, String loginId) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth m : "+m+CF.RS);
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth y : "+y+CF.RS);
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth loginId : "+loginId+CF.RS);
		
		Calendar now = Calendar.getInstance(); // ex) 2022.06.22
		
		// date 변환
		if(y == -1) {	// null이면 현재 년도를 받겠다.
			y = now.get(Calendar.YEAR);
		}
		if(m == -1) {	//	null이면 현재 달을 받겠다.
			m = now.get(Calendar.MONTH) + 1; // 0 - 1월, 1 - 2월, ... 11 - 12월
		}
		if(m==0) {	// 작년으로 갈때 12월로 변경
			m = 12;
			y = y-1;
		}
		if(m==13) {	// 내년으로 갈때 1월로 변경
			m = 1;
			y = y+1;
		}
		
		// 시작시 필요한 공백 <TD>의 갯수를 구하는 알고리즘 -> startBlank 
		// firstDay는 오늘날짜를 먼저구하여 DAY만 1일로 변경
		Calendar firstDay = Calendar.getInstance(); // ex) 2022.06.22
		firstDay.set(Calendar.YEAR, y);		// 2022
		firstDay.set(Calendar.MONTH, m-1); // 자바 달력API는 1월을 0으로, 2월을 1로, ... 12월을 11로 설정
		firstDay.set(Calendar.DATE, 1); // 1로 변경 ex) 2022.06.01
		int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		
		// dayOfWeek 	일1, 월2, 화3, 수4, 목5, 금6, 토7
		// startBlank 	일0, 월1, 화2, 수3, 목4, 금5, 토6
		
		// 1) startBlank
		int startBlank = dayOfWeek - 1;
		
		// 2) endDay
		int endDay = firstDay.getActualMaximum(Calendar.DATE);// firstDay달의 제일 큰 숫자 -> 마지막날짜
		// strartBlank와 endDay를 합의 결과에 endBlank를 더해서 7의 배수가 되도록 함
		
		// 3) endBlank
		int endBlank = 0;
		// endBlank가 생길경우
		if((startBlank+endDay)%7 != 0) {
			// 7에서 startBlank+endDay의 7로 나눈 나머지값을 빼면
			endBlank = 7-((startBlank+endDay)%7);
		}
		// 4) TotalTd
		int totalTd = startBlank + endDay + endBlank;
		// 모두 더하여 달력에 들어갈 블록갯수 구현
		
		// 디버깅
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth dayOfweek : "+dayOfWeek+CF.RS);
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth totalTd : "+totalTd+CF.RS);
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth startBlank : "+startBlank+CF.RS);
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth endDay : "+endDay+CF.RS);
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth endBlank : "+endBlank+CF.RS);
		
		// 한번에 map으로 전환히야 Mapper로 보내기 위한 준비
		Map<String, Object> mapMapper = new HashMap<>();
		if(loginLv >= 3) {
			mapMapper.put("m", m);
			mapMapper.put("y", y);
		} else {
			mapMapper.put("m", m);
			mapMapper.put("y", y);
			mapMapper.put("loginId", loginId);
		}

		// 디버깅
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth mapMapper : "+mapMapper+CF.RS);
		
		// mapper로 모델값 추출
		List<CalendarMap> list = new ArrayList<CalendarMap>();
		if(loginLv >= 3) {	// 관리자, 강사
			list = lectureMapper.selectScheduleList(mapMapper);
		} else {	// 강사, 학생
			list = lectureMapper.selectMemberSchedule(mapMapper);
		}
		
		// 디버깅
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth mapMapper list : "+list+CF.RS);
		
		// 강의과목리스트 mapper로 모델값 추출
		List<LectureSubject> lectureSubjectList = lectureMapper.selectLectureSubjecList();
		log.debug(CF.HJI+"LectureService.getSheduleListByMonth mapMapper lectureSubjectList : "+lectureSubjectList+CF.RS);
		
		// Controller에 보내기 위해 준비
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("lectureSubjectList", lectureSubjectList);
		map.put("startBlank", startBlank);
		map.put("endDay", endDay);
		map.put("endBlank", endBlank);
		map.put("totalTd", totalTd);
		map.put("m", m);
		map.put("y", y);
				
		return map;
	}
	
	// 시간표 상세보기
	public Map<String,Object> getScheduleOne(int scheduleNo) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.getScheduleOne scheduleNo : "+scheduleNo+CF.RS);
		
		// mapper로 모델값 추출
		Map<String,Object> map = new HashMap<String,Object>();
		map = lectureMapper.selectScheduleOne(scheduleNo);
		log.debug(CF.HJI+"LectureService.getScheduleOne map : "+map+CF.RS);
		
		return map;
	}
	
	// 시간표 추가
	public int addSchedule(Schedule schedule) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.addSchedule : "+schedule+CF.RS);
		// 실행
		int row = lectureMapper.insertSchedule(schedule);
		
		// 디버깅
		log.debug(CF.HJI+"LectureService.addSchedule row : "+row+CF.RS);
		if(row == 1) {
			log.debug(CF.HJI+"LectureService.addSchedule : "+"성공"+CF.RS);
		} else {
			log.debug(CF.HJI+"LectureService.addSchedule : "+"실패"+CF.RS);
		}
		
		return row;
	}
	
	// 시간표 수정액션
	public int modifyScheduleAction(@RequestBody Schedule schedule) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.modifySchedule endBlank : "+schedule+CF.RS);
		
		// mapper로 모델값 추출
		int row = lectureMapper.updateSchedule(schedule);
		if(row == 1) {
			log.debug(CF.HJI+"LectureService.modifySchedule : "+"성공"+CF.RS);
		} else {
			log.debug(CF.HJI+"LectureService.modifySchedule : "+"실패"+CF.RS);
		}
		
		return row;
	}
	
	// 시간표 수정폼
	public Map<String,Object> modifyScheduleForm(int scheduleNo) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.modifyScheduleForm scheduleNo : "+scheduleNo+CF.RS);
		
		// mapper로 모델값 추출
		Map<String,Object> selectScheduleOne = new HashMap<String,Object>();
		selectScheduleOne = lectureMapper.selectScheduleOne(scheduleNo);
		
		List<LectureSubject> lectureSubjectList = lectureMapper.selectLectureSubjecList();
		
		// map으로 리턴
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lectureSubjectList", lectureSubjectList);
		map.put("selectScheduleOne", selectScheduleOne);
		log.debug(CF.HJI+"LectureService.getScheduleOne map : "+map+CF.RS);
		
		return map;
	}
	
	// 시간표 삭제
	public int removeSchedule(int scheduleNo) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.removeSchedule endBlank : "+scheduleNo+CF.RS);
		
		// mapper로 모델값 추출
		int row = lectureMapper.deleteSchedule(scheduleNo);
		if(row == 1) {
			log.debug(CF.HJI+"LectureService.removeSchedule : "+"성공"+CF.RS);
		} else {
			log.debug(CF.HJI+"LectureService.removeSchedule : "+"실패"+CF.RS);
		}
		
		return row;
	}
	
	// 자료실 리스트(selectLectureReferenceList(lectureName)) 
	public Map<String, Object> getLectureReferenceList(Map<String, Object> map) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.selectLectureReferenceList map : "+map+CF.RS);
		
		// 매개변수 분리
		String loginId = (String)map.get("loginId");
		
		int loginLv = (int)map.get("loginLv");
		int currentPage = (int)map.get("currentPage");
		int rowPerPage = (int)map.get("rowPerPage");
		int beginRow = (currentPage-1) * rowPerPage;
		
		// begeinRow가 0일경우
		if(beginRow == 0) {
			beginRow = 1;
		}
		
		// 강사, 학생 강의명 찾기
		String lectureName = "";
		String teacher = "";
		if(loginLv > 1) {
			teacher = lectureMapper.selectTeacherName(loginId);
			lectureName = lectureMapper.selectTeacherLectureName(teacher);
			log.debug(CF.HJI+"LectureService.selectLectureReferenceList lectureName : "+lectureName+CF.RS);
		} else {
			lectureName = lectureMapper.selectStudentLectureName(loginId);
			log.debug(CF.HJI+"LectureService.selectLectureReferenceList lectureName : "+lectureName+CF.RS);
		}
		
		// 페이징
		int totalCount = lectureMapper.selectReferenceCnt(lectureName);
		int lastPage = totalCount / rowPerPage;
		if(totalCount % rowPerPage != 0) {
			lastPage += 1;
		}
		
		// map에 넣기
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("lectureName", lectureName);
		log.debug(CF.HJI+"LectureService.selectLectureReferenceList lectureName : "+paramMap+CF.RS);
		
		// list 모델값 추출
		List<Reference> list = new ArrayList<Reference>();
		list = lectureMapper.selectLectureReferenceList(paramMap);
		log.debug(CF.HJI+"LectureService.selectLectureReferenceList list : "+list+CF.RS);
		
		// list 와 map 맵에 넣어서 리턴
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("lectureName", lectureName);
		returnMap.put("list", list);
		returnMap.put("lastPage", lastPage);
		log.debug(CF.HJI+"LectureService.selectLectureReferenceList list : "+returnMap+CF.RS);
		
		return returnMap;
	}
	
	// 자료실 상세보기(selectReferenceOne(referenceNo),selectReferenceFileList(referenceNo));
	public Map<String,Object> getReferenceOne(int referenceNo) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.selectReferenceOne referenceNo : "+referenceNo+CF.RS);
		
		// Reference 모델값
		Reference reference = new Reference();
		reference = lectureMapper.selectReferenceOne(referenceNo);
		log.debug(CF.HJI+"LectureService.selectReferenceOne reference : "+reference+CF.RS);
		
		// ReferenceFile 모델값
		List<ReferenceFile> referenceFileList = new ArrayList<ReferenceFile>();
		referenceFileList = lectureMapper.selectReferenceFileList(referenceNo);
		log.debug(CF.HJI+"LectureService.selectReferenceOne referenceFilelist : "+referenceFileList+CF.RS);
		
		// 맵에 넣고 리턴
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("reference", reference);
		map.put("referenceFileList", referenceFileList);
		log.debug(CF.HJI+"LectureService.selectReferenceOne map : "+map+CF.RS);
		
		return map;
	}
		
	// 자료실 입력(insertReference(reference),insertReferenceFile(referenceFile))
	public void addReference(ReferenceForm referenceForm, String path) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.addReference path : "+referenceForm+CF.RS);
		log.debug(CF.HJI+"LectureService.addReference reference : "+path+CF.RS);
		
		// lectureMapper를 호출하기 위한 객체 생성
		Reference reference = new Reference();
		reference.setLectureName(referenceForm.getLectureName());
		reference.setReferenceTitle(referenceForm.getReferenceTitle());
		reference.setReferenceContent(referenceForm.getReferenceContent());
		log.debug(CF.HJI+"LectureService.addReference reference : "+reference+CF.RS);
		
		log.debug(CF.HJI+"LectureService.addReference reference 호출 전 referenceNo : "+reference.getReferenceNo()+CF.RS);
		int row = lectureMapper.insertReference(reference);
		log.debug(CF.HJI+"LectureService.addReference reference 호출 후 referenceNo : "+reference.getReferenceNo()+CF.RS);
		
		//lectureMapper(file)를 호출하기 위한 객체 생성
		if(referenceForm.getReferenceFileList().get(0).getSize() > 0 && row == 1) {
			log.debug(CF.HJI+"LectureService.addReference reference : 첨부된 파일이 있습니다."+CF.RS);
			for(MultipartFile mf : referenceForm.getReferenceFileList()) {
				
				ReferenceFile referenceFile = new ReferenceFile();
				String originName = mf.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf("."));
				
				// 파일을 저장할때 사용할 중복되지않는 새로운 이름 필요(UUID API 사용)
				String fileName = UUID.randomUUID().toString();
				fileName = fileName.replace("-", "");
				fileName = fileName + ext;
				
				referenceFile.setReferenceFileName(fileName);
				referenceFile.setReferenceFileSize(mf.getSize());
				referenceFile.setReferenceFileOrginName(originName);
				referenceFile.setReferenceFileType(mf.getContentType());
				referenceFile.setReferenceNo(reference.getReferenceNo());
				
				row = 0;
				row = lectureMapper.insertReferenceFile(referenceFile);
				log.debug(CF.HJI+"LectureService.addReference 파일 입력 성공! : "+row+CF.RS);
				try {
					mf.transferTo(new File(path+fileName)); // multipart안의 파일을 저장장치로 저장
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동을 함
					throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일 된다.
				}
			}
		}
	}
		
	// 자료실 수정(updateReferenceFile(referenceFile), deleteReferenceFile(referenceFileNo),insertReferenceFile(referenceFile))
	// 자료실 파일 개별 삭제
	public Map<String,Object> removeReferenceFile(int referenceFileNo, String path, int referenceNo) {
		
		// 매개변수 디버깅
		log.debug(CF.HJI+"LectureService.removeReferenceFile.referenceFileNo : "+referenceFileNo+CF.RS);
		log.debug(CF.HJI+"LectureService.removeReferenceFile.path : "+path+CF.RS);
		log.debug(CF.HJI+"LectureService.removeReferenceFile.referenceNo : "+referenceNo+CF.RS);
		
		// 모델값 추출
		String deleteReferenceFile = lectureMapper.selectReferenceFileOne(referenceFileNo);
		log.debug(CF.HJI+"LectureService.removeReferenceFile.deleteReferenceFile : "+deleteReferenceFile+CF.RS);
		
		// Spring boot내에 있는 파일 삭제
		File f = new File(path+deleteReferenceFile);
		// 파일이 있다면
		if(f.exists()) {
			// 삭제
			f.delete();
			log.debug(CF.HJI+"LectureService.removeReferenceFile f : "+f+CF.RS);
		}
		// 파일 삭제후 DB삭제
		int row = lectureMapper.deleteReferenceFile(referenceFileNo);
		if(row == 1) {
			log.debug(CF.HJI+"LectureService.removeReferenceFile.deleteReferenceFile 성공! : " + row+CF.RS);
		} else {
			log.debug(CF.HJI+"LectureService.removeReferenceFile.deleteReferenceFile 실패! : " + row+CF.RS);
		}
		
		// 수정폼 가기 위해 상세보기 모델값 추출
		Reference reference = new Reference();
		reference = lectureMapper.selectReferenceOne(referenceNo);
		log.debug(CF.HJI+"LectureService.removeReferenceFile reference : "+reference+CF.RS);
		
		List<ReferenceFile> referenceFileList = new ArrayList<ReferenceFile>();
		referenceFileList = lectureMapper.selectReferenceFileList(referenceNo);
		log.debug(CF.HJI+"LectureService.removeReferenceFile referenceFilelist : "+referenceFileList+CF.RS);
		
		String deleteNoticeFile = lectureMapper.selectReferenceFileOne(referenceFileNo);
		log.debug(CF.HJI+"LectureService.removeReferenceFile.deleteNoticeFile : "+deleteNoticeFile+CF.RS);
		
		// map에 넣고 리턴
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("reference", reference);
		map.put("referenceFileList", referenceFileList);
		log.debug(CF.HJI+"LectureService.selectReferenceOne map : "+map+CF.RS);
		
		return map;
	}
	
	
	// 자료실 전체 수정
	public void updateAddReference(ReferenceForm referenceForm, int referenceNo, String path, Reference reference) {
		
		// 요청값 디버깅
		log.debug(CF.HJI+"LectureService.updateAddReference.referenceForm : "+referenceForm+CF.RS);
		log.debug(CF.HJI+"LectureService.updateAddReference.referenceNo : "+referenceNo+CF.RS);
		log.debug(CF.HJI+"LectureService.updateAddReference.path : "+path+CF.RS);
		reference.setReferenceNo(referenceNo);
		log.debug(CF.HJI+"LectureService.updateAddReference.reference : "+reference+CF.RS);
		
		// mapper 모델값 추출
		int row = lectureMapper.updateReference(reference);
		log.debug(CF.HJI+"LectureService.updateAddReference.row : "+row+CF.RS);
		
		// 초기화
		row = 0;
		
		// NoticefileMapper를 호출하기 위한 객체 생성
		log.debug(CF.HJI+"LectureService.updateAddReference.ReferenceFileList : "+referenceForm.getReferenceFileList()+CF.RS);
		log.debug(CF.HJI+"LectureService.updateAddReference.referenceNo : "+referenceNo+CF.RS);
		log.debug(CF.HJI+"LectureService.updateAddReference.path : "+path+CF.RS);
		List<MultipartFile> updateAddReferenceFileList = referenceForm.getReferenceFileList();
		
		// 넣을 파일이 있다면
		if(updateAddReferenceFileList.get(0).getSize() > 0) {
			log.debug(CF.HJI+"LectureService.updateAddReferenceFileList() : 첨부된 파일이 있습니다."+CF.RS);
			for(MultipartFile mf : updateAddReferenceFileList) {
				ReferenceFile referencefile = new ReferenceFile();
				
				String originName = mf.getOriginalFilename();
				String ext = originName.substring(originName.lastIndexOf("."));
				
				// 파일을 저장할때 사용할 중복되지않는 새로운 이름 필요(UUID API 사용)
				String fileName = UUID.randomUUID().toString();
				fileName = fileName.replace("-", "");
				fileName = fileName + ext;
				
				referencefile.setReferenceFileName(fileName);
				referencefile.setReferenceFileOrginName(originName);
				referencefile.setReferenceFileSize(mf.getSize());
				referencefile.setReferenceFileType(mf.getContentType());
				referencefile.setReferenceNo(referenceNo);
				
				log.debug(CF.HJI+"LectureService.updateAddReference.referencefile : "+referencefile+CF.RS);
				lectureMapper.insertReferenceFile(referencefile);
				
				try {
					mf.transferTo(new File(path+fileName)); // multipart안의 파일을 저장장치로 저장
				} catch (Exception e) {
					e.printStackTrace();
					// 새로운 예외 발생시켜야지만 @Transactional 작동을 함
					throw new RuntimeException(); // RuntimeException은 예외처리를 하지 않아도 컴파일 된다.
				}
			}
		}
	}
	
	// 자료실 삭제(deleteReference(referenceNo), deleteReferenceFileList(referenceNo))
	public void removeReference(int referenceNo, String path) {
		
		// 1) 저장 장치의 파일을 삭제 -> 파일이름필요 
		List<String> referenceFileNameList = lectureMapper.selectReferencefileNameList(referenceNo);
		log.debug(CF.HJI+"LectureService.removeReference.referenceFileNameList : "+referenceFileNameList+CF.RS);
		for(String filename : referenceFileNameList) {
			File f = new File(path+filename);
			if(f.exists()) {
				f.delete();
			}
		}
		// 2) db행 삭제
		if(referenceFileNameList.size() > 0) {
			int row= lectureMapper.deleteReferenceFileList(referenceNo);
			log.debug(CF.HJI+"LectureService.removeReference.row : "+row+CF.RS);
		}
		int row2= lectureMapper.deleteReference(referenceNo);
		log.debug(CF.HJI+"LectureService.removeReference.row2 : "+row2+CF.RS);
	}
}
