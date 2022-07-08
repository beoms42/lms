package kr.co.gdu.lms.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.LoginMapper;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.mapper.MemberFileMapper;
import kr.co.gdu.lms.vo.Dept;
import kr.co.gdu.lms.vo.Login;
import kr.co.gdu.lms.vo.Manager;
import kr.co.gdu.lms.vo.MemberFile;
import kr.co.gdu.lms.vo.MemberForm;
import kr.co.gdu.lms.vo.Position;
import kr.co.gdu.lms.vo.Student;
import kr.co.gdu.lms.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class LoginService {
	@Autowired private LoginMapper loginMapper;
	@Autowired private ManagerMapper managerMapper;
	@Autowired private MemberFileMapper memberFileMapper;
	@Autowired private JavaMailSender javaMailSender;
	
	public void modifyActiveByMember(String loginId) {
		loginMapper.updateActiveByMember(loginId);
	}
	
	public List<Map<String, Object>> getRecoveryMember() {
		return loginMapper.selectRecoveryMemberList();
	}
	
	// 로그인 성공했을 때 레벨 출력
	public Login getLoginLevel(Login loginTest) {
		return loginMapper.loginAndSelectLevel(loginTest);
	}
	
	// 마지막 로그인 날짜 업데이트
	public void modifyLastChangePwDate(String loginId) {
		log.debug(CF.LCH + "LoginService.modifyLastChangePwDate loginId : " + loginId + CF.RS);
		String pwRecordDate = loginMapper.selectPwRecordDate(loginId);
		log.debug(CF.LCH + "LoginService.modifyLastChangePwDate pwRecordDate : " + pwRecordDate + CF.RS);
		loginMapper.updatePwRecord(pwRecordDate);
	}
	
	// 비밀번호 변경한지 얼마나 지났는지 출력
	public int getDiffDay(String loginId) {
		log.debug(CF.LCH + "LoginService.getDiffDay loginId : " + loginId + CF.RS);
		return loginMapper.selectDiffDay(loginId);
	}

	// 회원가입 거절
	public void modifyAddMemberActiveDenied(String loginId) {
		log.debug(CF.LCH + "LoginService.modifyAddMemberActiveDenied loginId : " + loginId + CF.RS);
		loginMapper.updateAddMemberActiveDenied(loginId);
	}
	
	// 회원가입 승인
	public void modifyAddMemberActive(String loginId) {
		log.debug(CF.LCH + "LoginService.modifyAddMemberActive loginId : " + loginId + CF.RS);
		loginMapper.updateAddMemberActive(loginId);
	}
	
	// 회원가입 승인 기다리는 사람들 리스트
	public Map<String, Object> acceptAddMember() {
		List<Manager> managerList = loginMapper.selectAddManagerList();
		List<Student> studentList = loginMapper.selectAddStudentList();
		List<Teacher> teacherList = loginMapper.selectAddTeacherList();
		
		log.debug(CF.LCH + "LoginService.acceptAddMember managerList : " + managerList + CF.RS);
		log.debug(CF.LCH + "LoginService.acceptAddMember studentList : " + studentList + CF.RS);
		log.debug(CF.LCH + "LoginService.acceptAddMember teacherList : " + teacherList + CF.RS);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("managerList", managerList);
		returnMap.put("studentList", studentList);
		returnMap.put("teacherList", teacherList);
		
		return returnMap;
	}
	
	// 바꾸는 비밀번호와 비밀번호 변경 이력 비교
	public String lastLoginPwCheck(Login login) {
		log.debug(CF.PHW+"LoginService.lastLoginPwCheck login : "+login+CF.RS );
		return loginMapper.lastLoginPwCheck(login);
	}
	
	// 학생, 강사, 매니저 비밀번호 변경 이력 테이블 삽입
	public int addPwRecord(Login login) {
		log.debug(CF.PHW+"LoginService.addPwRecord login : "+login+CF.RS );
		return loginMapper.insertPwRecord(login);
	}
	
	// 학생, 강사, 매니저 비밀번호 변경
	public int modifyLoginPw(Login login) {
		log.debug(CF.PHW+"LoginService.modifyLoginPw login : "+login+CF.RS );
		return loginMapper.updatePw(login);
	}
	
	// 학생, 강사, 매니저 비밀번호 찾기
	public int searchAllLoginPw(Map<String, Object> map) {
		log.debug(CF.PHW+"LoginService.searchLoginPwByStudent map : "+map+CF.RS );
		return loginMapper.selectAllLoginPw(map);
	}

	// 학생, 강사, 매니저 아이디 찾기
	public String searchAllLoginId(Map<String, Object> map) {
		
		log.debug(CF.PHW+"LoginService.searchLoginIdByStudent map : "+map+CF.RS);
		String loginId = loginMapper.selectAllLoginId(map);
		
		log.debug(CF.OHI+"LoginService.searchAllLoginId loginId : "+loginId+CF.RS);
		
		if(loginId != null) {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo((String) map.get("email"));
			simpleMailMessage.setFrom("LMS-TFT");
			simpleMailMessage.setSubject("LMS-TFT 아이디 발송");
			simpleMailMessage.setText("회원님의 아이디는 "+loginId+" 입니다.");
			
			log.debug(CF.OHI+"LoginService.searchAllLoginId simpleMailMessage"+simpleMailMessage+CF.RS);
			
			javaMailSender.send(simpleMailMessage);
			
			return "true";
		} else {
			return "false";
		}
		
	}
		
	// 로그인
	public Login login(Login loginTest) {
		log.debug(CF.OHI+"LoginService.login loginTest : "+loginTest+CF.RS);
		
		// 로그인 정보 넣어서 맞다면 로그인아이디와 level 들고오기
		Login login = loginMapper.loginAndSelectLevel(loginTest);
		// 마지막 로그인 날짜 업데이트
		int row = loginMapper.updateLastLoginDate(loginTest.getLoginId());
		
		log.debug(CF.OHI+"LoginService.login login : "+login+CF.RS);
		log.debug(CF.OHI+"LoginService.login row : "+row+CF.RS);
		
		return login;
	}
	
	// 이메일 중복 체크 
	public int emailCheck(String email, String addChk) {
	
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("addChk", addChk);
		
		log.debug(CF.OHI+"LoginService.emailCheck map : "+map+CF.RS);
		
		// 해당 이메일 있는지 체크해서 일치하는 개수 받아오기
		int count = loginMapper.selectEmailCnt(map);
		
		return count;
	}
	
	// 아이디 중복 체크  
	public int idCheck(String id) {
		
		// 해당 아이디 있는지 체크해서 일치하는 개수 받아오기
		int count = loginMapper.selectIdCnt(id);
		log.debug(CF.OHI+"LoginService.idCheck count : "+count+CF.RS);
		
		return count;
	}
	
	// 회원가입 - post 
	public void addMember(MemberForm memberForm, String path) {
		
		log.debug(CF.OHI+"LoginService.addMember path : "+path+CF.RS );
		log.debug(CF.OHI+"LoginService.addMember map : "+memberForm+CF.RS );

		// 레벨 구분할 변수 선언
		String level = "";
		
		if("student".equals(memberForm.getAddChk())) {// 강사인지 학생인지 매니저인지 구별하는 addChk가 학생이라면
			level = "1";
		} else if("teacher".equals(memberForm.getAddChk())) { // 강사라면
			level = "2";
		} else { // 둘다 아니라면 - 매니저
			level = "3";
		}
		
		log.debug(CF.OHI+"LoginService.addMember level : "+level+CF.RS);
		
		// level vo에 값 지정
		memberForm.setLevel(level);
		
		// 로그인 테이블에 로그인 정보 추가
		loginMapper.insertLogin(memberForm);
		
		// 해당 멤버 테이블에 멤버 정보 추가
		loginMapper.insertMember(memberForm);

		// 비밀번호 이력테이블에 추가하기 위한 데이터 바인딩
		Login login = new Login();
		login.setLoginId(memberForm.getLoginId());
		login.setLoginPw(memberForm.getLoginPw());
		
		// 비밀번호 이력테이블에 비밀번호 추가
		loginMapper.insertPwRecord(login);
		
		// vo에 담긴 file꺼내서 mf에 담기 (편리성 위해)
		MultipartFile mf = memberForm.getCustomFile();
		
		// mf이름 originalName에 담기
		String originalName = mf.getOriginalFilename();
		
		// originalName에서 마지막 . 문자열 위치
		String ext = originalName.substring(originalName.lastIndexOf("."));
		
		// 파일 저장할때 사용할 새로운 이름 부여 (이름 중복 방지하기위해) (UUID API 사용)
		String fileName = UUID.randomUUID().toString();
		
		// - 있다면 빼기
		fileName = fileName.replace("-", "");
		fileName += ext; // fileName에 ext 붙이기
		
		// memberFile 데이터바인딩
		MemberFile memberFile = new MemberFile();
		memberFile.setLoginId(memberForm.getLoginId());
		memberFile.setMemberFileName(fileName);
		memberFile.setMemberFileOriginName(originalName);
		memberFile.setMemberFileType(mf.getContentType());
		memberFile.setMemberFileSize(mf.getSize());
		
		log.debug(CF.OHI+"LoginService.addMember memberFile : "+memberFile+CF.RS);
		
		try {
			// 파일 위치에 저장
			mf.transferTo(new File(path+fileName)); 
		} catch (Exception e) { //runTime계열 익셉션 아니라서 꼭 예외 처리 필요
			e.printStackTrace();
			
			//예외 처리하면 트랜잭션 발생안하니까 컴파일 가능한 예외 발생시켜주기
			throw new RuntimeException();
		}
		
		// memberFile 테이블에 파일정보 넣어주기
		int row = memberFileMapper.insertMemberFile(memberFile);
		
		log.debug(CF.OHI+"LoginService.addMember row : "+row+CF.RS);
		
	}
	
	// 회원가입 - get ( 매니저 회원가입시 부서정보랑 직급 정보 뽑기)
	public Map<String, Object> addMemberGetDeptAndPosition() {
		//리턴할 맵 선언
		Map<String, Object> map = new HashMap<>();
		
		List<Dept> dept = managerMapper.selectDept();
		log.debug(CF.OHI+"LoginService.addMemberGetDeptAndPosition dept : "+dept+CF.RS );
		List<Position> position = managerMapper.selectPosition();
		log.debug(CF.OHI+"LoginService.addMemberGetDeptAndPosition position : "+position+CF.RS );
		
		//맵에 담기
		map.put("dept", dept);
		map.put("position", position);
		return map;
		
	}
	
	// 로그인시 레벨만 받는 서비스
	public int getLevelByLoginId(String loginId) {
		int level = loginMapper.selectLevelByLoginId(loginId);
		return level;		
	}
}
