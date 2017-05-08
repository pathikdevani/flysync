package com.design;

import com.pathik.flysync.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

public class MangerTextView extends TextView{

	public MangerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		setGravity(Gravity.CENTER_VERTICAL);
		setTextColor(Color.parseColor(context.getResources().getString(R.color.black_main)));
		setHeight((int)context.getResources().getDimension(R.dimen.setting_manager_height));
		
		setPadding((int) context.getResources().getDimension(R.dimen.setting_manager_padding), 0, 0, 0);
		setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		
	}

}
