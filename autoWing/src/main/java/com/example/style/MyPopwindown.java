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
 * �Զ���popupWindow 
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
	        // ����SelectPicPopupWindow��View  
	        setContentView(conentView);  
	        // ����SelectPicPopupWindow��������Ŀ�  
	        setWidth(w / 2 + 50);  
	        // ����SelectPicPopupWindow��������ĸ�  
	        setHeight(LayoutParams.WRAP_CONTENT);  
	        // ����SelectPicPopupWindow��������ɵ��   
	        setFocusable(true); //����PopupWindow�ɻ�ý���  
	        setTouchable(true); //����PopupWindow�ɴ���  
	       setOutsideTouchable(true); //���÷�PopupWindow����ɴ���  
	        // ˢ��״̬  
	        update();  
	        // ʵ����һ��ColorDrawable��ɫΪ��͸��  
	        ColorDrawable dw = new ColorDrawable(0000000000);  
	        // ��back���������ط�ʹ����ʧ,������������ܴ���OnDismisslistener �����������ؼ��仯�Ȳ���  
	        setBackgroundDrawable(dw);  
	        //this.setBackgroundDrawable(new BitmapDrawable());
	        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);  
	        // ����SelectPicPopupWindow�������嶯��Ч��  
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
	     * ��ʾpopupWindow 
	     *  
	     * @param parent 
	     */  
	    public void showPopupWindow(View parent) {  
	        if (!this.isShowing()) {  
	            // ��������ʽ��ʾpopupwindow  
	            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);  
	        } else {  
	            this.dismiss();  
	        }  
	    }  
}
