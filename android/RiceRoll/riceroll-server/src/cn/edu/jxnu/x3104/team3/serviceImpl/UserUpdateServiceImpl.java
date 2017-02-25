package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.edu.jxnu.x3104.team3.bean.Condiment;
import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;
import cn.edu.jxnu.x3104.team3.dao.CondimentDao;
import cn.edu.jxnu.x3104.team3.dao.CondimentDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDao;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDaoImpl;
import cn.edu.jxnu.x3104.team3.service.UserUpdateService;

public class UserUpdateServiceImpl implements UserUpdateService{

	@Override
	public JSONArray FoodMaterialUpdate() {
		FoodMaterialDao foodMaterialDao = new FoodMaterialDaoImpl();
		List<FoodMaterial> foodMaterials = foodMaterialDao.getAllFoodMaterial();
		JSONArray foodMaterialArray = new JSONArray();
		int i = 0;
		if(!foodMaterials.isEmpty()){
			for(FoodMaterial foodMaterial:foodMaterials){
				JSONObject json = JSONObject.parseObject(JSONObject
						.toJSONString(foodMaterial));
				foodMaterialArray.add(i++, json); 
			}
		}		
		return foodMaterialArray;
	}

	@Override
	public JSONArray CondimentUpdate() {
		CondimentDao condimentDao = new CondimentDaoImpl();
		List<Condiment> condiments = condimentDao.getAllCondiment();
		JSONArray condimentArray = new JSONArray();
		int i = 0;
		if(!condiments.isEmpty()){
			for(Condiment condiment:condiments){
				JSONObject json = JSONObject.parseObject(JSONObject
						.toJSONString(condiment));
				condimentArray.add(i++, json); 
			}
		}		
		return condimentArray;
	}


}
