package cn.edu.jxnu.x3104.team3.service;

public interface AdminGetInfoService {
	/**
	 * 获取主页所需信息
	 * @return 信息数组
	 */
	int[] getCount();
	/**
	 * 获取排行
	 * @return 排行信息
	 */
	String getRank();
}
