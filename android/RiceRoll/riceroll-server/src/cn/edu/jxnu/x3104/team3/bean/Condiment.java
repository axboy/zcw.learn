package cn.edu.jxnu.x3104.team3.bean;

import java.io.Serializable;

/**
 * 调料类
 * */
public class Condiment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1428145603512023618L;
	/**
	 * 调味品id
	 * */
	private String  condiment_id;
	/**
	 * 调味品名称
	 * */
	private String  condiment_name;
	/**
	 * 调味品图片链接
	 * */
	private String  condiment_photo_link;
	/**
	 * 调味品蛋白质含量
	 * */
	private int  condiment_protein;
	/**
	 * 调味品碳水化合物含量
	 * */
	private int  condiment_sugar;
	/**
	 * 调味品脂肪含量
	 * */
	private int  condiment_fat;
	/**
	 * 调味品膳食纤维含量
	 * */
	private int  condiment_dietary_fiber;
	/**
	 * 调味品维生素C含量
	 * */
	private int  condiment_vc;
	/**
	 * 调味品钙含量
	 * */
	private int  condiment_calcium;
	/**
	 * 调味品钾含量
	 * */
	private int  condiment_muriate;
	/**
	 * 调味品钠含量
	 * */
	private int  condiment_natrium;
	/**
	 * 调味品磷含量
	 * */
	private int  condiment_phosphorus;
	/**
	 * 调味品卡路里
	 * */
	private int  condiment_calories;
	public String getCondiment_id() {
		return condiment_id;
	}
	public void setCondiment_id(String condiment_id) {
		this.condiment_id = condiment_id;
	}
	public String getCondiment_name() {
		return condiment_name;
	}
	public void setCondiment_name(String condiment_name) {
		this.condiment_name = condiment_name;
	}
	public String getCondiment_photo_link() {
		return condiment_photo_link;
	}
	public void setCondiment_photo_link(String condiment_photo_link) {
		this.condiment_photo_link = condiment_photo_link;
	}
	public int getCondiment_protein() {
		return condiment_protein;
	}
	public void setCondiment_protein(int condiment_protein) {
		this.condiment_protein = condiment_protein;
	}
	public int getCondiment_sugar() {
		return condiment_sugar;
	}
	public void setCondiment_sugar(int condiment_sugar) {
		this.condiment_sugar = condiment_sugar;
	}
	public int getCondiment_fat() {
		return condiment_fat;
	}
	public void setCondiment_fat(int condiment_fat) {
		this.condiment_fat = condiment_fat;
	}
	public int getCondiment_dietary_fiber() {
		return condiment_dietary_fiber;
	}
	public void setCondiment_dietary_fiber(int condiment_dietary_fiber) {
		this.condiment_dietary_fiber = condiment_dietary_fiber;
	}
	public int getCondiment_vc() {
		return condiment_vc;
	}
	public void setCondiment_vc(int condiment_vc) {
		this.condiment_vc = condiment_vc;
	}
	public int getCondiment_calcium() {
		return condiment_calcium;
	}
	public void setCondiment_calcium(int condiment_calcium) {
		this.condiment_calcium = condiment_calcium;
	}
	public int getCondiment_muriate() {
		return condiment_muriate;
	}
	public void setCondiment_muriate(int condiment_muriate) {
		this.condiment_muriate = condiment_muriate;
	}
	public int getCondiment_natrium() {
		return condiment_natrium;
	}
	public void setCondiment_natrium(int condiment_natrium) {
		this.condiment_natrium = condiment_natrium;
	}
	public int getCondiment_phosphorus() {
		return condiment_phosphorus;
	}
	public void setCondiment_phosphorus(int condiment_phosphorus) {
		this.condiment_phosphorus = condiment_phosphorus;
	}
	public int getCondiment_calories() {
		return condiment_calories;
	}
	public void setCondiment_calories(int condiment_calories) {
		this.condiment_calories = condiment_calories;
	}
}
