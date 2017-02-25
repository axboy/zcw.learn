package cn.edu.jxnu.x3104.team3.jxnu_library.ui;
import cn.edu.jxnu.x3104.team3.jxnu_library.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class Search_result_no_content extends Activity{
	TextView search_result_no_content_back_to_main;
	TextView search_result_no_content_title;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result_no_content);
		init();
		Intent intent=getIntent();
		String title=intent.getStringExtra("title");
		search_result_no_content_title.setText(title);
		search_result_no_content_back_to_main.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Search_result_no_content.this.finish();
				overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			}			
		});
	}
	private void init() {
		search_result_no_content_back_to_main = (TextView)findViewById(R.id.search_result_no_content_back_to_main);
		search_result_no_content_title = (TextView)findViewById(R.id.search_result_no_content_title);
		
	}
}