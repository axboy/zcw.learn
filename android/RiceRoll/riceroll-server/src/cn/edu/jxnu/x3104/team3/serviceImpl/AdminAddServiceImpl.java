package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.edu.jxnu.x3104.team3.bean.Count;
import cn.edu.jxnu.x3104.team3.bean.Recommend;
import cn.edu.jxnu.x3104.team3.dao.CondimentDao;
import cn.edu.jxnu.x3104.team3.dao.CondimentDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CookbookDao;
import cn.edu.jxnu.x3104.team3.dao.CookbookDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CountDao;
import cn.edu.jxnu.x3104.team3.dao.CountDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDao;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.RecommendDao;
import cn.edu.jxnu.x3104.team3.dao.RecommendDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.AdminAddService;
import cn.edu.jxnu.x3104.team3.tool.Constants;

public class AdminAddServiceImpl implements AdminAddService{
	RecommendDao recommendDao = new RecommendDaoImpl();
	CookbookDao cookbookDao = new CookbookDaoImpl();
	FoodMaterialDao foodMaterialDao = new FoodMaterialDaoImpl();
	CondimentDao condimetDao = new CondimentDaoImpl();
	CountDao countDao = new CountDaoImpl();

	@Override
	public int addRecommend(String recommend_cookbook_name,
			String recommend_brief_introduction, String recommend_reason,
			List<File> pictrues) {
		Recommend recommend = new Recommend();
		Count count = countDao.findByType(0);
		SimpleDateFormat forId = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String currentTime = forId.format(new Date());
		if(count.getCount_recommend_date().equals(currentTime.trim())||     //检测日期
				count.getCount_recommend_date()==currentTime.trim()){
			if(count.getCount_recommend()==5){
				return Constants.RECOMMEND_FULL;
			}else{
				//按格式生成并储存id
				recommend.setRecommend_id(currentTime.trim()+(count.getCount_recommend()+1));
				recommend.setRecommend_brief_introduction(recommend_brief_introduction);
				recommend.setRecommend_cookbook_name(recommend_cookbook_name);
				recommend.setRecommend_reason(recommend_reason);
				if(pictrues.isEmpty()||pictrues==null){
					return Constants.RECOMMEND_PICTRUE_NULL;
				}else{
					int i = 0;
					String fileName;
					String pictruesPath="";
					for(File pictrue:pictrues){
						File temp = new File(Constants.RECOMMEND_PATH);
						if(i==0){
							fileName = recommend.getRecommend_id()
									+"_"+"title"+".png";
						}else{
							fileName = recommend.getRecommend_id()
									+"_"+i+".png";
						}						
						pictruesPath = pictruesPath+fileName+";";
						try {
							File savaPictrue = new File(temp,fileName);
							FileUtils.copyFile(pictrue, savaPictrue);
						} catch (Exception e) {
							System.err.println("");
							e.printStackTrace();
							HibernateUtil.closeSession();
							return Constants.RECOMMEND_PICTRUE_ERROR;
						}
						i++;
					}
					recommend.setRecommend_pictures_link(pictruesPath);
				}
				recommendDao.save(recommend);
				count.setCount_recommend(count.getCount_recommend()+1);
				countDao.update(count);
				return Constants.RECOMMEND_SUCCESS;
			}			
		}else{
			count.setCount_recommend(0);
			count.setCount_recommend_date(currentTime);
			recommend.setRecommend_id(currentTime.trim()+(count.getCount_recommend()+1));
			recommend.setRecommend_brief_introduction(recommend_brief_introduction);
			recommend.setRecommend_cookbook_name(recommend_cookbook_name);
			recommend.setRecommend_reason(recommend_reason);
			if(pictrues.isEmpty()||pictrues==null){
				return Constants.RECOMMEND_PICTRUE_NULL;
			}else{
				int i = 0;
				String fileName;
				String pictruesPath="";
				for(File pictrue:pictrues){
					File temp = new File(Constants.RECOMMEND_PATH);
					if(i==0){
						fileName = recommend.getRecommend_id()
								+"_"+"title"+".png";
					}else{
						fileName = recommend.getRecommend_id()
								+"_"+i+".png";
					}						
					pictruesPath = pictruesPath+fileName+";";
					try {
						File savaPictrue = new File(temp,fileName);
						FileUtils.copyFile(pictrue, savaPictrue);
					} catch (Exception e) {
						System.err.println("");
						e.printStackTrace();
						HibernateUtil.closeSession();
						return Constants.RECOMMEND_PICTRUE_ERROR;
					}
					i++;
				}
				recommend.setRecommend_pictures_link(pictruesPath);
			}
			recommendDao.save(recommend);
			count.setCount_recommend(count.getCount_recommend()+1);
			countDao.update(count);
			return Constants.RECOMMEND_SUCCESS;
		}
	}

	

}
