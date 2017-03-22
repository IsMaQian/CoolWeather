package com.example.style;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class AnimationUtil {
    private static final String TAG = AnimationUtil.class.getSimpleName();
 
    /**
     * �ӿؼ�����λ���ƶ����ؼ��ĵײ�
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.ZORDER_TOP, 0.0f,
                Animation.ZORDER_TOP, 0.0f, Animation.ZORDER_TOP,
                0.0f, Animation.ZORDER_TOP, 1.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }
 
    /**
     * �ӿؼ��ĵײ��ƶ����ؼ�����λ��
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        return mHiddenAction;
    }
}