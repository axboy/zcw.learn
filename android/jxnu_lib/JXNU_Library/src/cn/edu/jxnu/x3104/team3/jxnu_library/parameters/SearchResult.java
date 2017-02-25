package cn.edu.jxnu.x3104.team3.jxnu_library.parameters;

public class SearchResult {
	public String book_url;
	public String book_title;
	public String book_type;
	public String book_guan_quantity;
	public String book_ke_quantity;
	public String book_author;
	public String book_publish;	
	public SearchResult(String id,String title,String type,String author,String guan_quantity,String publisher,String ke_quantity){
		book_url=id;			//url
		book_title=title;	//标题
		book_type=type;		//类型
		book_guan_quantity=guan_quantity;//馆藏数量
		book_ke_quantity=ke_quantity;//可借数量
		book_author=author;//作者
		book_publish=publisher;
	}
}
