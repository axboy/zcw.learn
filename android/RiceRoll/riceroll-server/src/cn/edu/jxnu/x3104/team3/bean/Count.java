package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * 
 *计数类，用于生成编号
 */
public class Count implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1835347649461680657L;
	/**
	 * 统计类型
	 */
	private int count_type;
	/**
	 * 菜谱总数
	 */
	private int count_cookbook;
	/**
	 * 调味品总数
	 */
	private int count_condiment;
	/**
	 * 反馈总数
	 */
	private int count_feedback;
	/**
	 * 食材总数
	 */
	private int count_food_material;
	/**
	 * 每日推荐数
	 */
	private int count_recommend;
	/**
	 * 每日推荐日期
	 */
	private String count_recommend_date;
	
	
	public String getCount_recommend_date() {
		return count_recommend_date;
	}

	public void setCount_recommend_date(String count_recommend_date) {
		this.count_recommend_date = count_recommend_date;
	}

	public int getCount_recommend() {
		return count_recommend;
	}
	
	public void setCount_recommend(int count_recommend) {
		this.count_recommend = count_recommend;
	}
		
	public int getCount_type() {
		return count_type;
	}
	public void setCount_type(int count_type) {
		this.count_type = count_type;
	}
	public int getCount_cookbook() {
		return count_cookbook;
	}
	public void setCount_cookbook(int count_cookbook) {
		this.count_cookbook = count_cookbook;
	}
	public int getCount_condiment() {
		return count_condiment;
	}
	public void setCount_condiment(int count_condiment) {
		this.count_condiment = count_condiment;
	}
	public int getCount_feedback() {
		return count_feedback;
	}
	public void setCount_feedback(int count_feedback) {
		this.count_feedback = count_feedback;
	}
	public int getCount_food_material() {
		return count_food_material;
	}
	public void setCount_food_material(int count_food_material) {
		this.count_food_material = count_food_material;
	}
	public int getCount_the_chain() {
		return count_the_chain;
	}
	public void setCount_the_chain(int count_the_chain) {
		this.count_the_chain = count_the_chain;
	}
	/**
	 * 外链总数
	 */
	private int count_the_chain;
}
