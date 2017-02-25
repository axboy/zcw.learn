/*package cn.edu.jxnu.x3104.team3.test;

import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.bean.Count;
import cn.edu.jxnu.x3104.team3.dao.CookbookDao;
import cn.edu.jxnu.x3104.team3.dao.CookbookDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.CountDao;
import cn.edu.jxnu.x3104.team3.dao.CountDaoImpl;

public class StolenDataForCookbook {
	CookbookDao cookbookDao = new CookbookDaoImpl();
	CountDao countDao = new CountDaoImpl();
	Cookbook cookbook = new Cookbook();
	Count count ;
	public StolenDataForCookbook(){
		count = countDao.findByType(0);	
		cookbook.setCookbook_id(String.format("%010d", (count.getCount_food_material()+1)));
		count.setCount_cookbook(count.getCount_cookbook()+1);
		cookbook.setCookbook_photo_link("0");
		cookbook.setCookbook_click_time(0);
		cookbook.setCookbook_download_time(0);
		cookbook.setCookbook_collect_time(0);
		cookbook.setCookbook_food_material_id("0");
		cookbook.setCookbook_condiment_id("0");
	}

}
*/