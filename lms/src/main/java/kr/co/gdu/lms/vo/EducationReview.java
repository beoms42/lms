package kr.co.gdu.lms.vo;

import lombok.Data;

@Data
public class EducationReview {
	private int educationNo;
	private int educationReviewStar;
	private String educationReviewContent;
	private String createDate;
	private String updateDate;
}
