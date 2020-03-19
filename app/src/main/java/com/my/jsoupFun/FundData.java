package com.my.jsoupFun;

import java.util.ArrayList;
import java.util.List;

/**
 * zhaolei
 * 时间:2020-03-19
 */
public class FundData implements Comparable<FundData> {
    public Integer id;
    public String fundCode;//代码
    public String fundName;//名字
    public String fundType;//类型
    public String fundDate;//日期
    public String fundunitValue;//单位净值
    public String fundChange;//变动
    public String fundReturnValue;//回报

    public int threeYearStar; //三年
    public int fiveYearStar;//五年


    public List<String> imageUrl = new ArrayList<>();


    @Override
    public int compareTo(FundData o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "FundData{" +
                "id=" + id +
                ", fundCode='" + fundCode + '\'' +
                ", fundName='" + fundName + '\'' +
                ", fundType='" + fundType + '\'' +
                ", fundDate='" + fundDate + '\'' +
                ", fundunitValue='" + fundunitValue + '\'' +
                ", fundChange='" + fundChange + '\'' +
                ", fundReturnValue='" + fundReturnValue + '\'' +
                ", threeYearStar=" + threeYearStar +
                ", fiveYearStar=" + fiveYearStar +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
