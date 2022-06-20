package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class LectureRoom {
	private String lectureRoomName;
	private String lectureRoomLocation;
	private int lectureRoomAdmit;
	private int lectureRoomSize;
	private String createDate;
	private String updateDate;
}
