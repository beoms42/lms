package kr.co.gdu.lms.component;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.gdu.lms.log.CF;
import kr.co.gdu.lms.mapper.LoginMapper;
import kr.co.gdu.lms.mapper.ManagerMapper;
import kr.co.gdu.lms.mapper.MemberFileMapper;
import kr.co.gdu.lms.mapper.StudentMapper;
import kr.co.gdu.lms.mapper.TeacherMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberScheduler {
	// corn = "초 분 시 일 월 요일", 요일은 0일, 1월, 2화, ....6토
	@Autowired private LoginMapper loginMapper;
	@Autowired private StudentMapper studentMapper;
	@Autowired private TeacherMapper teacherMapper;
	@Autowired private ManagerMapper managerMapper;
	@Autowired private MemberFileMapper memberFileMapper;
	
	// 로그인 안한지 3개월이 지나면 휴먼계정으로 변경
	@Scheduled(cron = "0 0 0 * * *")
	public void modifyMemberActive() {
		
		List<String> memberIdList = loginMapper.selectMemberIdListByLastLoginDate();
		int dormantRow = 0;
		
		for(String s : memberIdList) {
			dormantRow += loginMapper.updateActiveByDormantMember(s);
		}
		
		log.debug(dormantRow + "명의 사용자가 비활성화 됐습니다.");
	}
	
	// 0초 0분 0시 1일 매월 요일 상관없이
	@Scheduled(cron = "0 0 0 * * *")
	public void deleteDormantMember() {
		
		List<Map<String,Object>> dormantMemberIdList = loginMapper.selectDormantMemberId();
		int stRow = 0;
		int teRow = 0;
		int maRow = 0;
		for(Map s : dormantMemberIdList) {
			if(s.get("level").equals("1")) {
				stRow += studentMapper.deleteStudent((String)s.get("loginId"));
			} else if(s.get("level").equals("2")) {
				teRow += teacherMapper.deleteTeacher((String)s.get("loginId"));
			} else {
				maRow += managerMapper.deleteManager((String)s.get("loginId"));
			}
			memberFileMapper.deleteMemberFile((String)s.get("loginId"));
		}
		
		log.debug(CF.LCH + "학생" + stRow + "명의 아이디가 비활성화 됐습니다." + CF.RS);
		log.debug(CF.LCH + "강사" + teRow + "명의 아이디가 비활성화 됐습니다." + CF.RS);
		log.debug(CF.LCH + "매니저" + maRow + "명의 아이디가 비활성화 됐습니다." + CF.RS);
	}
}
