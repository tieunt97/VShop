package com.example.tieu_nt.vshop.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.tieu_nt.vshop.R;

/**
 * Created by tieu_nt on 3/19/2018.
 */

public class ClearEdittext extends android.support.v7.widget.AppCompatEditText{
    private boolean visible = false;
    private int ALPHA = (int) (255 * .70f);
    private Drawable clearDrawable;

    public ClearEdittext(Context context) {
        super(context);
        cauhinh();
    }

    public ClearEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        cauhinh();
    }

    public ClearEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        cauhinh();
    }

    private void cauhinh(){
        if(visible){
            clearDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
            clearDrawable.setAlpha(ALPHA);
            setCompoundDrawablesWithIntrinsicBounds(null, null, clearDrawable, null);
        }else{
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(visible && MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= (getRight() - getCompoundDrawables()[2].getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter == 0 && start == 0){
            visible = false;
            cauhinh();
        }else{
            visible = true;
            cauhinh();
        }
    }
}
