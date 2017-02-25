package cn.edu.jxnu.x3104.team3.serviceImpl;

import java.util.List;

import cn.edu.jxnu.x3104.team3.bean.Cookbook;
import cn.edu.jxnu.x3104.team3.dao.CookbookDao;
import cn.edu.jxnu.x3104.team3.dao.CookbookDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.RegisterDao;
import cn.edu.jxnu.x3104.team3.dao.RegisterDaoImpl;
import cn.edu.jxnu.x3104.team3.dao.UserDao;
import cn.edu.jxnu.x3104.team3.dao.UserDaoImpl;
import cn.edu.jxnu.x3104.team3.service.AdminGetInfoService;

public class AdminGetInfoServiceImpl implements AdminGetInfoService{
	UserDao userDao = new UserDaoImpl();
	CookbookDao cookbookDao = new CookbookDaoImpl();
	RegisterDao registerDao = new RegisterDaoImpl();

	@Override
	public int[] getCount() {
		int total=0;
		int[] count = new int[4];
		count[0] = userDao.getUserNun();
		count[1] = registerDao.findByType(0).getRegister_num();
		List<Cookbook> cookbooks = cookbookDao.getAllCookbook();
		count[2] = cookbooks.size();
		for(Cookbook cookbook:cookbooks){
			total = total + cookbook.getCookbook_download_time();
		}
		count[3] = total;
		return count;
	}

	@Override
	public String getRank() {
		String rank="";
		List<Cookbook> cookbooks = cookbookDao.getAllCookbook();
		int max = 0;	
		int num = 0;
		int count = 0;
		if(cookbooks.size()>=5){			
			for(int i=5;i>0;i--){
				num = 0;
				count = 0;
				max = 0;
				for(Cookbook cookbook:cookbooks){
					if(max<cookbook.getCookbook_click_time()){
						max = cookbook.getCookbook_click_time();
						num = count;				
					}
					count++;
				}
				String[] name = cookbooks.get(num).getCookbook_name().split(";");
				rank = rank + name[name.length-1]+","
						+cookbooks.get(num).getCookbook_click_time()+","
						+cookbooks.get(num).getCookbook_download_time()+","
						+cookbooks.get(num).getCookbook_collect_time()			
						+";";
				cookbooks.remove(num);
			}
		}		
		return rank;
	}


}
