-- --------------------------------------------------------
-- 호스트:                          3.39.249.73
-- 서버 버전:                        10.3.35-MariaDB-1:10.3.35+maria~bionic - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- lms 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `lms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lms`;

-- 테이블 lms.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `login_id` varchar(50) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_birth` varchar(50) NOT NULL,
  `admin_gender` enum('남','여') NOT NULL,
  `admin_email` varchar(50) NOT NULL,
  `admin_phone` varchar(50) NOT NULL,
  `admin_create_date` datetime NOT NULL,
  `admin_update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`) USING BTREE,
  CONSTRAINT `FK_admin_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.assignment_exam 구조 내보내기
CREATE TABLE IF NOT EXISTS `assignment_exam` (
  `assignment_exam_no` int(11) NOT NULL AUTO_INCREMENT,
  `lecture_name` varchar(50) NOT NULL,
  `assignment_exam_title` varchar(50) NOT NULL,
  `assignment_exam_content` varchar(50) NOT NULL,
  `assignment_deadline` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`assignment_exam_no`),
  KEY `FK_assignment_exam_lecture` (`lecture_name`),
  CONSTRAINT `FK_assignment_exam_lecture` FOREIGN KEY (`lecture_name`) REFERENCES `lecture` (`lecture_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.assignment_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `assignment_file` (
  `assignment_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `assignment_exam_no` int(11) NOT NULL,
  `login_id` varchar(50) NOT NULL,
  `assignment_file_name` varchar(50) NOT NULL,
  `assignment_file_origin_name` varchar(50) NOT NULL,
  `assignment_file_type` varchar(50) NOT NULL,
  `assignment_file_size` bigint(20) NOT NULL,
  `assignment_file_create_date` datetime NOT NULL,
  PRIMARY KEY (`assignment_file_no`),
  KEY `FK_assignment_file_login` (`login_id`),
  KEY `FK_assignment_file_assignment_exam` (`assignment_exam_no`),
  CONSTRAINT `FK_assignment_file_assignment_exam` FOREIGN KEY (`assignment_exam_no`) REFERENCES `assignment_exam` (`assignment_exam_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_assignment_file_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.assignment_submit 구조 내보내기
CREATE TABLE IF NOT EXISTS `assignment_submit` (
  `assignment_exam_no` int(11) NOT NULL,
  `education_no` int(11) NOT NULL,
  `assignment_signfile_name` varchar(50) NOT NULL,
  `assignment_signfile_origin_name` varchar(50) NOT NULL,
  `assignment_signfile_type` varchar(50) NOT NULL,
  `assignment_signfile_size` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `assignment_submit_score` int(11) NOT NULL,
  PRIMARY KEY (`assignment_exam_no`,`education_no`) USING BTREE,
  KEY `FK_assignment_submit_education` (`education_no`),
  CONSTRAINT `FK_assignment_submit_assignment_exam` FOREIGN KEY (`assignment_exam_no`) REFERENCES `assignment_exam` (`assignment_exam_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_assignment_submit_education` FOREIGN KEY (`education_no`) REFERENCES `education` (`education_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.attendance 구조 내보내기
CREATE TABLE IF NOT EXISTS `attendance` (
  `attendance_no` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_no` int(11) NOT NULL,
  `education_no` int(11) NOT NULL,
  `attendance_record` enum('출석','결석') NOT NULL,
  PRIMARY KEY (`attendance_no`) USING BTREE,
  KEY `FK_attendance_schedule` (`schedule_no`),
  KEY `FK_attendance_education` (`education_no`),
  CONSTRAINT `FK_attendance_education` FOREIGN KEY (`education_no`) REFERENCES `education` (`education_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_attendance_schedule` FOREIGN KEY (`schedule_no`) REFERENCES `schedule` (`schedule_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.attendance_reason 구조 내보내기
CREATE TABLE IF NOT EXISTS `attendance_reason` (
  `attendance_no` int(11) NOT NULL,
  `attendance_reason` varchar(50) NOT NULL,
  PRIMARY KEY (`attendance_no`) USING BTREE,
  CONSTRAINT `FK_attendance_reason_attendance` FOREIGN KEY (`attendance_no`) REFERENCES `attendance` (`attendance_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.career 구조 내보내기
CREATE TABLE IF NOT EXISTS `career` (
  `login_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `company_name` varchar(50) NOT NULL,
  `long_service_year` int(11) NOT NULL,
  `service` varchar(50) NOT NULL,
  `position_no` int(11) NOT NULL,
  PRIMARY KEY (`login_id`,`company_name`) USING BTREE,
  KEY `FK_career_position` (`position_no`),
  CONSTRAINT `FK_career_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_career_position` FOREIGN KEY (`position_no`) REFERENCES `position` (`position_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.community 구조 내보내기
CREATE TABLE IF NOT EXISTS `community` (
  `community_no` int(11) NOT NULL AUTO_INCREMENT,
  `community_title` varchar(50) NOT NULL,
  `community_content` text NOT NULL,
  `login_id` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `community_pw` varchar(50) NOT NULL,
  PRIMARY KEY (`community_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.community_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `community_comment` (
  `login_id` varchar(200) NOT NULL,
  `create_date` datetime NOT NULL,
  `community_comment_content` text NOT NULL,
  `community_no` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`,`create_date`),
  KEY `FK_community_comment_community` (`community_no`),
  CONSTRAINT `FK_community_comment_community` FOREIGN KEY (`community_no`) REFERENCES `community` (`community_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.community_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `community_file` (
  `community_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `community_no` int(11) NOT NULL,
  `login_id` varchar(50) NOT NULL,
  `community_file_name` varchar(50) NOT NULL,
  `community_file_type` varchar(50) NOT NULL,
  `community_file_origin_name` varchar(50) NOT NULL,
  `community_file_size` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`community_file_no`),
  KEY `FK_community_file_community` (`community_no`),
  CONSTRAINT `FK_community_file_community` FOREIGN KEY (`community_no`) REFERENCES `community` (`community_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.dept 구조 내보내기
CREATE TABLE IF NOT EXISTS `dept` (
  `dept_no` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) NOT NULL,
  `dept_location` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`dept_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.drop_record 구조 내보내기
CREATE TABLE IF NOT EXISTS `drop_record` (
  `education_no` int(11) NOT NULL,
  `drop_record_date` datetime NOT NULL,
  `drop_record_reason` varchar(50) NOT NULL,
  PRIMARY KEY (`education_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.education 구조 내보내기
CREATE TABLE IF NOT EXISTS `education` (
  `education_no` int(11) NOT NULL AUTO_INCREMENT,
  `lecture_name` varchar(50) NOT NULL,
  `login_id` varchar(200) NOT NULL,
  `education_date` datetime NOT NULL,
  PRIMARY KEY (`education_no`),
  KEY `FK_education_lecture` (`lecture_name`),
  KEY `FK_education_login` (`login_id`),
  CONSTRAINT `FK_education_lecture` FOREIGN KEY (`lecture_name`) REFERENCES `lecture` (`lecture_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_education_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.education_review 구조 내보내기
CREATE TABLE IF NOT EXISTS `education_review` (
  `education_no` int(11) NOT NULL,
  `education_review_star` int(11) NOT NULL,
  `education_review_content` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`education_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture` (
  `lecture_name` varchar(200) NOT NULL,
  `teacher` varchar(200) NOT NULL,
  `manager` varchar(200) NOT NULL,
  `lecture_start_date` date NOT NULL,
  `lecture_end_date` date NOT NULL,
  `lecture_room_name` varchar(200) NOT NULL,
  `lecture_student_capacity` int(11) NOT NULL,
  `login_id` varchar(200) NOT NULL,
  `lecture_active` enum('0','1') NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`lecture_name`),
  KEY `FK_lecture_lecture_room` (`lecture_room_name`),
  KEY `FK_lecture_login` (`login_id`),
  CONSTRAINT `FK_lecture_lecture_room` FOREIGN KEY (`lecture_room_name`) REFERENCES `lecture_room` (`lecture_room_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_lecture_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture_room 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture_room` (
  `lecture_room_name` varchar(50) NOT NULL,
  `lecture_room_location` varchar(50) NOT NULL,
  `lecture_room_admit` int(11) NOT NULL,
  `lecture_room_size` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`lecture_room_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.lecture_subject 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture_subject` (
  `lecture_subject_no` int(11) NOT NULL AUTO_INCREMENT,
  `lecture_name` varchar(50) NOT NULL,
  `subject_name` varchar(50) NOT NULL,
  PRIMARY KEY (`lecture_subject_no`),
  KEY `FK_lecture_subject_lecture` (`lecture_name`),
  KEY `FK_lecture_subject_subject` (`subject_name`),
  CONSTRAINT `FK_lecture_subject_lecture` FOREIGN KEY (`lecture_name`) REFERENCES `lecture` (`lecture_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_lecture_subject_subject` FOREIGN KEY (`subject_name`) REFERENCES `subject` (`subject_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.license 구조 내보내기
CREATE TABLE IF NOT EXISTS `license` (
  `license_no` int(11) NOT NULL AUTO_INCREMENT,
  `license_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `login_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `get_license_date` datetime NOT NULL,
  `license_agency` varchar(50) CHARACTER SET utf8 NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`license_no`) USING BTREE,
  KEY `FK_license_login` (`login_id`),
  CONSTRAINT `FK_license_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.login 구조 내보내기
CREATE TABLE IF NOT EXISTS `login` (
  `login_id` varchar(200) NOT NULL,
  `login_pw` varchar(200) NOT NULL,
  `level` enum('1','2','3','4') NOT NULL,
  `login_active` enum('0','1') NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.major 구조 내보내기
CREATE TABLE IF NOT EXISTS `major` (
  `login_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `major_name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`,`major_name`) USING BTREE,
  CONSTRAINT `FK_major_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.manager 구조 내보내기
CREATE TABLE IF NOT EXISTS `manager` (
  `login_id` varchar(200) NOT NULL,
  `manager_name` varchar(50) NOT NULL,
  `manager_birth` varchar(50) NOT NULL,
  `manager_gender` enum('남','여') NOT NULL,
  `address` varchar(200) NOT NULL,
  `detail_addr` varchar(200) NOT NULL,
  `manager_email` varchar(50) NOT NULL,
  `manager_phone` varchar(50) NOT NULL,
  `last_login_date` date NOT NULL,
  `dept_no` int(11) NOT NULL,
  `position_no` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`),
  KEY `FK_manager_dept` (`dept_no`),
  KEY `FK_manager_position` (`position_no`),
  CONSTRAINT `FK_manager_dept` FOREIGN KEY (`dept_no`) REFERENCES `dept` (`dept_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_manager_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_manager_position` FOREIGN KEY (`position_no`) REFERENCES `position` (`position_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.member_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `member_file` (
  `member_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(200) NOT NULL,
  `member_file_name` varchar(200) NOT NULL,
  `member_file_origin_name` varchar(50) NOT NULL,
  `member_file_type` varchar(50) NOT NULL,
  `member_file_size` int(11) NOT NULL,
  `member_file_create_date` datetime NOT NULL,
  PRIMARY KEY (`member_file_no`),
  KEY `FK_member_file_login` (`login_id`),
  CONSTRAINT `FK_member_file_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.notice 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice` (
  `notice_no` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(200) NOT NULL,
  `notice_content` text NOT NULL,
  `login_id` varchar(200) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`notice_no`),
  KEY `FK_notice_login` (`login_id`),
  CONSTRAINT `FK_notice_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.notice_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `notice_file` (
  `notice_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `notice_no` int(11) NOT NULL,
  `login_id` varchar(50) NOT NULL,
  `notice_file_name` varchar(50) NOT NULL,
  `notice_file_origin_name` varchar(50) NOT NULL,
  `notice_file_type` varchar(50) NOT NULL,
  `notice_file_size` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`notice_file_no`),
  KEY `FK_notice_file_notice` (`notice_no`),
  CONSTRAINT `FK_notice_file_notice` FOREIGN KEY (`notice_no`) REFERENCES `notice` (`notice_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.position 구조 내보내기
CREATE TABLE IF NOT EXISTS `position` (
  `position_no` int(11) NOT NULL AUTO_INCREMENT,
  `position_name` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`position_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.pw_record 구조 내보내기
CREATE TABLE IF NOT EXISTS `pw_record` (
  `login_id` varchar(200) NOT NULL,
  `login_pw` varchar(50) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`),
  CONSTRAINT `FK_pw_record_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.qna 구조 내보내기
CREATE TABLE IF NOT EXISTS `qna` (
  `qna_no` int(11) NOT NULL AUTO_INCREMENT,
  `qna_title` varchar(50) NOT NULL,
  `qna_content` text NOT NULL,
  `login_id` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `qna_disclosure` enum('공개','비공개') NOT NULL,
  `qna_kind` enum('문의','답변') NOT NULL,
  `qna_state` enum('대기중','답변완료') NOT NULL,
  PRIMARY KEY (`qna_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.qna_file 구조 내보내기
CREATE TABLE IF NOT EXISTS `qna_file` (
  `qna_file_no` int(11) NOT NULL AUTO_INCREMENT,
  `qna_no` int(11) NOT NULL,
  `login_id` varchar(50) NOT NULL,
  `qna_file_name` varchar(50) NOT NULL,
  `qna_file_origin_name` varchar(50) NOT NULL,
  `qna_file_type` varchar(50) NOT NULL,
  `qna_file_size` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`qna_file_no`) USING BTREE,
  KEY `FK_qna_file_qna` (`qna_no`),
  CONSTRAINT `FK_qna_file_qna` FOREIGN KEY (`qna_no`) REFERENCES `qna` (`qna_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.recommend 구조 내보내기
CREATE TABLE IF NOT EXISTS `recommend` (
  `login_id` varchar(50) NOT NULL,
  `community_no` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`,`community_no`),
  KEY `FK_recommend_community` (`community_no`),
  CONSTRAINT `FK_recommend_community` FOREIGN KEY (`community_no`) REFERENCES `community` (`community_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.reference 구조 내보내기
CREATE TABLE IF NOT EXISTS `reference` (
  `reference_no` int(11) NOT NULL AUTO_INCREMENT,
  `reference_title` varchar(50) NOT NULL,
  `reference_content` text NOT NULL,
  `lecture_name` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`reference_no`),
  KEY `FK_reference_lecture` (`lecture_name`),
  CONSTRAINT `FK_reference_lecture` FOREIGN KEY (`lecture_name`) REFERENCES `lecture` (`lecture_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.schedule 구조 내보내기
CREATE TABLE IF NOT EXISTS `schedule` (
  `schedule_no` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_date` date NOT NULL,
  `lecture_subject_no` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`schedule_no`),
  KEY `FK_schedule_lecture_subject` (`lecture_subject_no`),
  CONSTRAINT `FK_schedule_lecture_subject` FOREIGN KEY (`lecture_subject_no`) REFERENCES `lecture_subject` (`lecture_subject_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.skill 구조 내보내기
CREATE TABLE IF NOT EXISTS `skill` (
  `login_id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `skill_language` varchar(50) CHARACTER SET utf8 NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`,`skill_language`) USING BTREE,
  CONSTRAINT `FK_skill_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.student 구조 내보내기
CREATE TABLE IF NOT EXISTS `student` (
  `login_id` varchar(200) NOT NULL,
  `student_name` varchar(50) NOT NULL,
  `student_birth` varchar(50) NOT NULL,
  `student_gender` enum('남','여') NOT NULL,
  `address` varchar(50) NOT NULL,
  `detail_address` varchar(50) NOT NULL,
  `student_email` varchar(50) NOT NULL,
  `student_phone` varchar(50) NOT NULL,
  `last_login_date` datetime NOT NULL,
  `military_status` enum('군필','미필','해당 없음') NOT NULL,
  `graduate` enum('대졸','초대졸','고졸') NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`),
  CONSTRAINT `FK_student_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject` (
  `subject_name` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`subject_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.subject_textbook 구조 내보내기
CREATE TABLE IF NOT EXISTS `subject_textbook` (
  `subject_textbook_no` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(50) NOT NULL,
  `textbook_no` int(11) NOT NULL,
  PRIMARY KEY (`subject_textbook_no`),
  KEY `FK_subject_textbook_textbook` (`textbook_no`),
  KEY `FK_subject_textbook_subject` (`subject_name`),
  CONSTRAINT `FK_subject_textbook_subject` FOREIGN KEY (`subject_name`) REFERENCES `subject` (`subject_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_textbook_textbook` FOREIGN KEY (`textbook_no`) REFERENCES `textbook` (`textbook_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.teacher 구조 내보내기
CREATE TABLE IF NOT EXISTS `teacher` (
  `login_id` varchar(200) NOT NULL,
  `teacher_name` varchar(50) NOT NULL,
  `teacher_birth` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `detail_addr` varchar(50) NOT NULL,
  `teacher_email` varchar(50) NOT NULL,
  `teacher_phone` varchar(50) NOT NULL,
  `last_login_date` date NOT NULL,
  `graduate` enum('대졸','초대졸','고졸') NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`login_id`),
  CONSTRAINT `FK_teacher_login` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.textbook 구조 내보내기
CREATE TABLE IF NOT EXISTS `textbook` (
  `textbook_no` int(11) NOT NULL AUTO_INCREMENT,
  `textbook_name` varchar(50) NOT NULL,
  `textbook_publisher` varchar(50) NOT NULL,
  `textbook_writer` varchar(50) NOT NULL,
  `textbook_price` int(11) NOT NULL,
  `textbook_page` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`textbook_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 lms.textbook_record 구조 내보내기
CREATE TABLE IF NOT EXISTS `textbook_record` (
  `education_no` int(11) NOT NULL,
  `subject_textbook_no` int(11) NOT NULL,
  `textbook_signfile_name` varchar(50) NOT NULL,
  `textbook_signfile_origin_name` varchar(50) NOT NULL,
  `textbook_signfile_type` varchar(50) NOT NULL,
  `textbook_signfile_size` bigint(20) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`education_no`,`subject_textbook_no`),
  KEY `FK_textbook_record_subject_textbook` (`subject_textbook_no`),
  CONSTRAINT `FK_textbook_record_subject_textbook` FOREIGN KEY (`subject_textbook_no`) REFERENCES `subject_textbook` (`subject_textbook_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
