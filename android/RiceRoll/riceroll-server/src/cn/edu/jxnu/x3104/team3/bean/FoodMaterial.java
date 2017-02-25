package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * 食材类
 * */
public class FoodMaterial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085807953448862220L;
	/**
	 * 食材id
	 */
	private String food_material_id ;
	/**
	 * 食材名称
	 */
	private String food_material_name ;
	/**
	 * 食材图片链接
	 */
	private String food_material_photo_link ;
	
	/**
	 * 食材蛋白质含量
	 */
	private int food_material_protein ;
	/**
	 * 食材碳水化合物含量
	 */
	private int food_material_sugar ;
	/**
	 * 食材脂肪含量
	 */
	private int food_material_fat ;
	/**
	 * 食材膳食纤维含量
	 */
	private int food_material_dietary_fiber ;
	/**
	 * 食材维生素C含量
	 */
	private int food_material_vc ;
	/**
	 * 食材钙含量
	 */
	private int food_material_calcium ;
	/**
	 * 食材钾含量
	 */
	private int food_material_muriate ;
	/**
	 * 食材钠含量
	 */
	private int food_material_natrium ;
	/**
	 * 食材磷含量
	 */
	private int food_material_phosphorus ;
	/**
	 * 食材卡路里
	 */
	private int food_material_calories ;
	
	public String getFood_material_id() {
		return food_material_id;
	}
	public void setFood_material_id(String food_material_id) {
		this.food_material_id = food_material_id;
	}
	public String getFood_material_name() {
		return food_material_name;
	}
	public void setFood_material_name(String food_material_name) {
		this.food_material_name = food_material_name;
	}
	public String getFood_material_photo_link() {
		return food_material_photo_link;
	}
	public void setFood_material_photo_link(String food_material_photo_link) {
		this.food_material_photo_link = food_material_photo_link;
	}
	public int getFood_material_protein() {
		return food_material_protein;
	}
	public void setFood_material_protein(int food_material_protein) {
		this.food_material_protein = food_material_protein;
	}
	public int getFood_material_sugar() {
		return food_material_sugar;
	}
	public void setFood_material_sugar(int food_material_sugar) {
		this.food_material_sugar = food_material_sugar;
	}
	public int getFood_material_fat() {
		return food_material_fat;
	}
	public void setFood_material_fat(int food_material_fat) {
		this.food_material_fat = food_material_fat;
	}
	public int getFood_material_dietary_fiber() {
		return food_material_dietary_fiber;
	}
	public void setFood_material_dietary_fiber(int food_material_dietary_fiber) {
		this.food_material_dietary_fiber = food_material_dietary_fiber;
	}
	public int getFood_material_vc() {
		return food_material_vc;
	}
	public void setFood_material_vc(int food_material_vc) {
		this.food_material_vc = food_material_vc;
	}
	public int getFood_material_calcium() {
		return food_material_calcium;
	}
	public void setFood_material_calcium(int food_material_calcium) {
		this.food_material_calcium = food_material_calcium;
	}
	public int getFood_material_muriate() {
		return food_material_muriate;
	}
	public void setFood_material_muriate(int food_material_muriate) {
		this.food_material_muriate = food_material_muriate;
	}
	public int getFood_material_natrium() {
		return food_material_natrium;
	}
	public void setFood_material_natrium(int food_material_natrium) {
		this.food_material_natrium = food_material_natrium;
	}
	public int getFood_material_phosphorus() {
		return food_material_phosphorus;
	}
	public void setFood_material_phosphorus(int food_material_phosphorus) {
		this.food_material_phosphorus = food_material_phosphorus;
	}
	public int getFood_material_calories() {
		return food_material_calories;
	}
	public void setFood_material_calories(int food_material_calories) {
		this.food_material_calories = food_material_calories;
	}

}
