package cn.edu.jxnu.x3104.team3.service;

import java.io.File;
import java.util.List;

public interface FeedbackService {
	/**
	 * @param feedback_user_id
	 *                        用户id
	 * @param feedback_content
	 *                        反馈内容
	 * @param feedback_keyword
	 *                        反馈关键词
	 * @param pictrues
	 *                        反馈附带图片
	 * @return
	 *                        保存反馈信息是否成功
	 */
	public boolean saveUserFeedback(String feedback_user_id,String feedback_content,
			String feedback_keyword,List<File> pictrues);
}
