package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.dao.CookbookDao;
import cn.edu.jxnu.x3104.team3.dao.CookbookDaoImpl;
import cn.edu.jxnu.x3104.team3.db.HibernateUtil;
import cn.edu.jxnu.x3104.team3.service.CookBookOperateService;

public class CookBookOperateServiceImpl implements CookBookOperateService{
	List<String> current;

	@Override
	public JSONArray SearchCookbookByKeyword(String cookbook_keyword) {
		String[] sourceStrArray = cookbook_keyword.split(";");	
		CookbookDao cookbooDao = new CookbookDaoImpl();
		List<Cookbook> allcookbooks = new ArrayList<Cookbook>();
		List<Cookbook> cookbooks1 = new ArrayList<Cookbook>();
		List<Cookbook> cookbooks0 = cookbooDao.findByKeyword(sourceStrArray[0]);
		HibernateUtil.closeSession();
		if(sourceStrArray.length>=2){
			cookbooks1 = cookbooDao.findByKeyword(sourceStrArray[1]);
			for(Cookbook cookbook:cookbooks0){
				if(cookbook.getCookbook_keyword().contains(sourceStrArray[1])){
					allcookbooks.add(cookbook);
				}
			}
			for(Cookbook cookbook:cookbooks0){
				allcookbooks.add(cookbook);
			}
			for(Cookbook cookbook:cookbooks1){
				allcookbooks.add(cookbook);
			}
			for(int i=0;i<allcookbooks.size();i++){	
				if(allcookbooks.get(i)!=null){
					for(int j=i+1;j<allcookbooks.size();j++){
						if(allcookbooks.get(j).getCookbook_id().equals(allcookbooks.get(i).getCookbook_id())||
								allcookbooks.get(j).getCookbook_id()==allcookbooks.get(i).getCookbook_id()){
							allcookbooks.remove(j);
							j--;							
						}
					}
				}
			}
		}
		else{
			allcookbooks=cookbooks0;
		}
		JSONArray cookbookArray = new JSONArray();		
		int i = 0;
		HibernateUtil.closeSession();
		if(!allcookbooks.isEmpty()){
			for(Cookbook cookbook:allcookbooks){
				if(cookbook!=null){
					JSONObject json = JSONObject.parseObject(JSONObject
							.toJSONString(cookbook));
					cookbookArray.add(i++, json); 
				//	System.out.println(cookbook.getCookbook_id());
				}
			}
		}		
		return cookbookArray;
	}

	@Override
	public JSONArray BuildCookbookArray(String foodMaterial_id) {
		String[] sourceStrArray = foodMaterial_id.split(";");
		CookbookDao cookbooDao = new CookbookDaoImpl();
		List<Cookbook> allcookbooks = new ArrayList<Cookbook>();
		List<List<String>> keywords = new ArrayList<List<String>>();
		JSONArray cookbookArray = new JSONArray();	
		List<String> temp = new ArrayList<String>();
		int count ;
		for(int i = sourceStrArray.length;i > 0 ;i--){
			count = 0;
			combination(count,temp,sourceStrArray,i);
			keywords.add(current);
		}
		for(List<String> keyword:keywords){
			List<Cookbook> cookbooks = cookbooDao.findByKeyword(keyword);
			if(cookbooks!=null&&!cookbooks.isEmpty()){
				for(Cookbook cookbook:cookbooks){
					allcookbooks.add(cookbook);
				}
			}
		}
		for(int i=0;i<allcookbooks.size();i++){	
			if(allcookbooks.get(i)!=null){
				for(int j=i+1;j<allcookbooks.size();j++){
					if(allcookbooks.get(j).getCookbook_id().equals(allcookbooks.get(i).getCookbook_id())||
							allcookbooks.get(j).getCookbook_id()==allcookbooks.get(i).getCookbook_id()){
						allcookbooks.remove(j);
						j--;							
					}
				}
			}
		}
		HibernateUtil.closeSession();
		int i = 0;
		if(!allcookbooks.isEmpty()){
			for(Cookbook cookbook:allcookbooks){
				if(cookbook!=null){
					JSONObject json = JSONObject.parseObject(JSONObject
							.toJSONString(cookbook));
					cookbookArray.add(i++, json); 
					System.out.println(cookbook.getCookbook_id());
				}
			}
		}		
		return cookbookArray;
	}
	
	
	public void combination(int count, List<String> temp, String[] sourceStrArray,int n){
		if(n==0){
	          current = temp;
	          return;
	      }
	      if(count==sourceStrArray.length){
	          return;
	      }
	      temp.add(sourceStrArray[count]);
	      combination(count+1,temp,sourceStrArray,n-1);
	      combination(count+1,temp,sourceStrArray,n);
	  }
	
}
