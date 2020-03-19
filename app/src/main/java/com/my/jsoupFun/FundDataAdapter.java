package com.my.jsoupFun;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * zhaolei
 * 时间:2020-03-19
 */
public class FundDataAdapter extends BaseQuickAdapter<FundData, BaseViewHolder> {


    public FundDataAdapter(int layoutResId, @Nullable List<FundData> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, FundData item) {
        helper.setText(R.id.tv_code_id, item.id + "代码:");

        helper.setText(R.id.tv_name, item.fundName);

        helper.setText(R.id.tv_code, item.fundCode);

        helper.setText(R.id.tv_type, item.fundType);

        helper.setText(R.id.tv_unitValue, "单位净值:" + item.fundunitValue);
        helper.setText(R.id.tv_returnValue, "年回报:" + item.fundReturnValue);
        helper.setText(R.id.tv_change, "日变动:" + item.fundChange);

        helper.setText(R.id.tv_date, "净值日期:" + item.fundDate);

        ImageView threeImage = helper.getView(R.id.img_three);
        ImageView fiveImage = helper.getView(R.id.img_five);


        Glide.with(mContext)
                .load(item.imageUrl.get(0))
                .into(threeImage);


        Glide.with(mContext)
                .load(item.imageUrl.get(1))
                .into(fiveImage);


    }
}
