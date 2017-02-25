package cn.edu.jxnu.x3104.team3.test;


import java.util.List;

import org.junit.Test;


import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDao;
import cn.edu.jxnu.x3104.team3.dao.FoodMaterialDaoImpl;

public class Food_Material_Test {

	@Test
	public void testSave() {
		FoodMaterialDao foodMaterialDao = new FoodMaterialDaoImpl();
		try{
			//FoodMaterial foodMaterial = new FoodMaterial();
			//foodMaterial.setFood_material_id("00000003");
			//foodMaterial.setFood_material_name("葱;葱花");
			List<FoodMaterial> foodMaterials=foodMaterialDao.getAllFoodMaterial();
			for(FoodMaterial foodMaterial:foodMaterials){
				System.out.println(foodMaterial.getFood_material_id());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
