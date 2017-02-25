package cn.edu.jxnu.x3104.team3.test;


import org.junit.Test;
import cn.edu.jxnu.x3104.team3.service.CookBookOperateService;
import cn.edu.jxnu.x3104.team3.serviceImpl.CookBookOperateServiceImpl;

public class Criteria_Sql_Test {

	@Test
	public void testFindByKeywordStringArray() {
//		fail("Not yet implemented");
		CookBookOperateService cookBookOperateService = new CookBookOperateServiceImpl();
		String temp = "00000001;00000004";
		cookBookOperateService.BuildCookbookArray(temp);
	}

}
