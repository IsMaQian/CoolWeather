package com.example.style;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.autowing.R;

/**
 * 自定义popupWindow 
 * @author XZJC_006
 *
 */
public class MyPopwindown extends PopupWindow{
	 	private View conentView;  

	    public MyPopwindown(final Activity context) {  
	        LayoutInflater inflater = (LayoutInflater) context  
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	        conentView = inflater.inflate(R.layout.popmenu, null);  
	        int h = context.getWindowManager().getDefaultDisplay().getHeight();  
	        int w = context.getWindowManager().getDefaultDisplay().getWidth();  
	        // 设置SelectPicPopupWindow的View  
	        setContentView(conentView);  
	        // 设置SelectPicPopupWindow弹出窗体的宽  
	        setWidth(w / 2 + 50);  
	        // 设置SelectPicPopupWindow弹出窗体的高  
	        setHeight(LayoutParams.WRAP_CONTENT);  
	        // 设置SelectPicPopupWindow弹出窗体可点击   
	        setFocusable(true); //设置PopupWindow可获得焦点  
	        setTouchable(true); //设置PopupWindow可触摸  
	       setOutsideTouchable(true); //设置非PopupWindow区域可触摸  
	        // 刷新状态  
	        update();  
	        // 实例化一个ColorDrawable颜色为半透明  
	        ColorDrawable dw = new ColorDrawable(0000000000);  
	        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作  
	        setBackgroundDrawable(dw);  
	        //this.setBackgroundDrawable(new BitmapDrawable());
	        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);  
	        // 设置SelectPicPopupWindow弹出窗体动画效果  
	        setAnimationStyle(android.R.style.Animation_Dialog);  

	        LinearLayout radiusLayout = (LinearLayout) conentView  
	                .findViewById(R.id.linear_radius);  
	        LinearLayout clearwaysLayout = (LinearLayout) conentView  
	                .findViewById(R.id.linear_clearways);  
	        LinearLayout saveschLayout = (LinearLayout) conentView  
	                .findViewById(R.id.linear_savescheme);  
	        LinearLayout taskselectLayout = (LinearLayout) conentView  
	                .findViewById(R.id.linear_taskselection);  
//	        radiusLayout.setOnClickListener(new OnClickListener() {  
//	  
//	            @Override  
//	            public void onClick(View arg0) {  
//	            	Popwindown.this.dismiss();  
//	            }  
//	        });  
//	  
//	        clearwaysLayout.setOnClickListener(new OnClickListener() {  
//	  
//	            @Override  
//	            public void onClick(View v) {  
//	            	Popwindown.this.dismiss();  
//	            }  
//	        });  
	    }  
	  
	    /** 
	     * 显示popupWindow 
	     *  
	     * @param parent 
	     */  
	    public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            // 以下拉方式显示popupwindow  
	            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);  
	        } else {  
	            this.dismiss();  
	        }  
	    }  
}
