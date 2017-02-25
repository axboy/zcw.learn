package cn.edu.jxnu.x3104.riceroll.bean;

/**调料类*/
public class Condiment {
	/**调料编号**/
	private String condiment_id;
	
	/**调料名字**/
	private String condiment_name;
	
	/**调料图片链接地址**/
	private String condiment_photo_link;
	
	/**蛋白质**/
	private int condiment_protein;
	
	/**糖**/
	private int condiment_sugar;
	
	/**脂肪**/
	private int condiment_fat;
	
	/**膳食纤维**/
	private int condiment_dietary_fiber;
	
	/**VC**/
	private int condiment_vc;
	
	/**钙**/
	private int condiment_calcium;
	
	/**氯**/
	private int condiment_muriate;
	
	/**钠**/
	private int condiment_natrium;
	
	/**磷**/
	private int condiment_phosphorus;
	
	/**卡路里**/
	private int condiment_calories;

	/**
	 * Condiment构造函数
	 * 
	 * @param condiment_id   调料编号
	 * @param condiment_name   调料名字
	 * @param condiment_photo_link   调料图片链接地址
	 * @param condiment_protein   蛋白质
	 * @param condiment_sugar   糖
	 * @param condiment_fat   脂肪
	 * @param condiment_dietary_fiber   膳食纤维
	 * @param condiment_vc   VC
	 * @param condiment_calcium   钙
	 * @param condiment_muriate   氯
	 * @param condiment_natrium   钠
	 * @param condiment_phosphorus   磷
	 * @param condiment_calories   卡路里
	 */
	public Condiment(String condiment_id, String condiment_name,
			String condiment_photo_link, int condiment_protein,
			int condiment_sugar, int condiment_fat,
			int condiment_dietary_fiber, int condiment_vc,
			int condiment_calcium, int condiment_muriate,
			int condiment_natrium, int condiment_phosphorus,
			int condiment_calories) {
		super();
		this.condiment_id = condiment_id;
		this.condiment_name = condiment_name;
		this.condiment_photo_link = condiment_photo_link;
		this.condiment_protein = condiment_protein;
		this.condiment_sugar = condiment_sugar;
		this.condiment_fat = condiment_fat;
		this.condiment_dietary_fiber = condiment_dietary_fiber;
		this.condiment_vc = condiment_vc;
		this.condiment_calcium = condiment_calcium;
		this.condiment_muriate = condiment_muriate;
		this.condiment_natrium = condiment_natrium;
		this.condiment_phosphorus = condiment_phosphorus;
		this.condiment_calories = condiment_calories;
	}	
	
	//set、get方法
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
	public int getcondiment_phosphorus() {
		return condiment_phosphorus;
	}
	public void setcondiment_phosphorus(int condiment_phosphorus) {
		this.condiment_phosphorus = condiment_phosphorus;
	}
	public int getCondiment_calories() {
		return condiment_calories;
	}
	public void setCondiment_calories(int condiment_calories) {
		this.condiment_calories = condiment_calories;
	}
}
