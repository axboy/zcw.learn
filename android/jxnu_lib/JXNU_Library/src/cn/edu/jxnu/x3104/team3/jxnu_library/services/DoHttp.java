package cn.edu.jxnu.x3104.team3.jxnu_library.services;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.Gnxw;
import cn.edu.jxnu.x3104.team3.jxnu_library.parameters.MyConstants;

public class DoHttp {
	
	//获得馆内新闻列表
	public ArrayList<Gnxw> get_gnxwList(){
		ArrayList<Gnxw> gnxw_list=new ArrayList<Gnxw>();
		String _title;		//标题
		String _url;		//地址
		String _date;	    //时间
		try{
/* 
 * 		//馆内新闻网页源码	示例
 *		<div class="classtext">
 *			<ul><li>
 *				<a href='Detail.asp?id=1327'  target='_blank'  title=提升文献资源建设  共创和谐美好明天提升文献资源建设  共创和谐美好明天</a>
 *				<img src='images/pic.gif' border=0>&nbsp;
 *				<a title="2014-12-5 11:13:05"><font color="333333">[ 2014-12-5 浏览：19 ]</font></a>
 *				</li>
 */
			Document doc=Jsoup.connect(MyConstants.HTTP_GNXW_URL).get();	//将馆内新闻页存进doc
			Elements div_element=doc.select("[class=classtext]");	
			Elements article_items=div_element.select("li");				//通过li分取数据
//			Log.d("article_items", article_items.toString());
			for(Element links:article_items){
				_url=links.select("a").attr("href");
				_url=MyConstants.HTTP_JXNU_LIB_HOME+_url;
//				Log.d("_url",_url);
				_title=links.select("a[target=_blank]").first().text();
//				Log.d("_title", _title.toString());
				_date=links.select("font[color=333333]").first().text();
//				Log.d("_date",_date.toString());
				gnxw_list.add(new Gnxw(_url,_title,_date)); 
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		return gnxw_list;
	}
}