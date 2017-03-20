package com.example.autowing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.example.library.NavigationTabBar;

/**
 * 设置界面
 * @author android_wbin
 *
 */
public class IndexSetActivity extends Activity{
	Button btn_workindex;
	Button btn_modelset;
	Button btn_lightset;
	Button btn_state;
	Button btn_offmap;
	Button btn_roucle;
	//侧面菜单
	//ViewPager viewPager;
	static NavigationTabBar ntbSample;
	LocalActivityManager romotemanager = null;
    private static final int REQUEST_SELECT_PID = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indexset);
		btn_workindex = (Button)findViewById(R.id.textViewworkindex);
		btn_workindex.setOnClickListener(workindexset);
		btn_modelset = (Button)findViewById(R.id.textViewmodelset);
		btn_modelset.setOnClickListener(modelset);
		btn_lightset = (Button)findViewById(R.id.textViewlightset);
		btn_lightset.setOnClickListener(ligntset);
		btn_offmap = (Button)findViewById(R.id.textViewmaprec);
		btn_offmap.setOnClickListener(offmapmanger);
		btn_roucle = (Button)findViewById(R.id.textViewmapcle);
		btn_roucle.setOnClickListener(rouclemanger);
//		btn_state = (Button)findViewById(R.id.textViewstate);
//		btn_state.setOnClickListener(imustate);
		
	
	}


	private OnClickListener workindexset = new OnClickListener() {
		@Override
		public void onClick(View v) {
			MainActivity.ntbSample.setViewPager(MainActivity.viewPager, 3);
		}};
		private OnClickListener modelset = new OnClickListener() {
			@Override
			public void onClick(View v) {
				//MainActivity.viewPager.setCurrentItem(4);
				MainActivity.ntbSample.setViewPager(MainActivity.viewPager, 4);
				//
				
			}};
			private OnClickListener ligntset = new OnClickListener() {
				@Override
				public void onClick(View v) {
					MainActivity.ntbSample.setViewPager(MainActivity.viewPager, 5);
				
				}};
				private OnClickListener offmapmanger = new OnClickListener() {
					@Override
					public void onClick(View v) {
						MainActivity.ntbSample.setViewPager(MainActivity.viewPager, 6);
					
					}};
					private OnClickListener rouclemanger = new OnClickListener() {
						@Override
						public void onClick(View v) {
							
						
						}};
					
}
