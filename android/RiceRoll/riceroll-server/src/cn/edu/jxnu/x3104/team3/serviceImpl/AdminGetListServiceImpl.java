package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Condiment;
import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.bean.FoodMaterial;
import cn.edu.jxnu.x3104.team3.dao.CookbookDao;
import cn.edu.jxnu.x3104.team3.dao.CookbookDaoImpl;
import cn.edu.jxnu.x3104.team3.service.AdminGetListService;

public class AdminGetListServiceImpl implements AdminGetListService{

	@Override
	public List<Condiment> getCondiments(int page) {
		return null;
	}

	@Override
	public List<FoodMaterial> getFoodMaterials(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cookbook> getCookbooks(String page,String select,String word) {
		int currentPage;
		if(page==null||page.equals("")){
			currentPage = 1;
		}else{
			currentPage = Integer.parseInt(page);
		}		
		String sql ;
		if(word==null||word.equals("")){
			sql = "from Cookbook order by cookbook_id asc";
		}else{
			sql = "from Cookbook where "+select+"='%"+word+"%'";
		}
		CookbookDao cookbookDao = new CookbookDaoImpl();	
		List<Cookbook> cookbooks = cookbookDao.findByPage(currentPage, sql);
		return cookbooks;
	}

}
