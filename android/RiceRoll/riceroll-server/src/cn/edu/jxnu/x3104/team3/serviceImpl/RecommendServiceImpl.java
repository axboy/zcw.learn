package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.jxnu.x3104.team3.bean.Recommend;
import cn.edu.jxnu.x3104.team3.dao.RecommendDao;
import cn.edu.jxnu.x3104.team3.dao.RecommendDaoImpl;
import cn.edu.jxnu.x3104.team3.service.RecommendService;

public class RecommendServiceImpl implements RecommendService{

	@Override
	public JSONArray RefreshRecommend() {
		RecommendDao recommendDao = new RecommendDaoImpl();
		List<Recommend> recommends = recommendDao.findByPage(1, 5);
		JSONArray recommendArray = new JSONArray();		
		int i = 0;
		if(!recommends.isEmpty()&&recommends!=null){
			for(Recommend recommend:recommends){
				if(recommend!=null){
					JSONObject json = JSONObject.parseObject(JSONObject
							.toJSONString(recommend));
					recommendArray.add(i++, json); 
				//	System.out.println(cookbook.getCookbook_id());
				}
			}
			return recommendArray;
		}else{
			return null;
		}
		
	}
	
	

}
