package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.edu.jxnu.x3104.team3.bean.Count;
import cn.edu.jxnu.x3104.team3.bean.Feedback;
import cn.edu.jxnu.x3104.team3.dao.CountDao;
import cn.edu.jxnu.x3104.team3.dao.CountDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.FeedbackDao;
import cn.edu.jxnu.x3104.team3.dao.FeedbackDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.FeedbackService;
import cn.edu.jxnu.x3104.team3.tool.Constants;

public class FeedbackServiceImpl implements FeedbackService{
	CountDao countDao = new CountDaoImpl();
	FeedbackDao feedbackDao = new FeedbackDaoImpl();

	@Override
	public boolean saveUserFeedback(String feedback_user_id,
			String feedback_content, String feedback_keyword,
			List<File> pictrues) {
		boolean flag = false;
		SimpleDateFormat forDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Feedback feedback = new Feedback();
		Count count = countDao.findByType(0);
		count.setCount_feedback(count.getCount_feedback()+1);
		feedback.setFeedback_id(String.format("%04d", (count.getCount_feedback()+1)));
		feedback.setFeedback_user_id(feedback_user_id);
		feedback.setFeedback_content(feedback_content);
		feedback.setFeedback_keyword(feedback_keyword);
		feedback.setFeedback_date(forDate.format(new Date()));
		if(pictrues==null||pictrues.isEmpty()){
			feedback.setFeedback_photo_link("0");
			flag=true;
		}else{
			int i = 1;
			String pictruesPath="";
			for(File pictrue:pictrues){
				File temp = new File(Constants.FEEDBACKPATH);
				String fileName = String.format("%04d", (count.getCount_feedback()+1))
						+"_"+i+".png";
				pictruesPath = pictruesPath+fileName+";";
				try {
					File savaPictrue = new File(temp,fileName);
					FileUtils.copyFile(pictrue, savaPictrue);
				} catch (Exception e) {
					System.err.println("");
					e.printStackTrace();
					HibernateUtil.closeSession();
					return false;
				}
				i++;
			}
			feedback.setFeedback_photo_link(pictruesPath);
			flag=true;
		}
		countDao.update(count);
		feedbackDao.save(feedback);
		HibernateUtil.closeSession();
		return flag;
	}

	

}
