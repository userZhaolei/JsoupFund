package com.my.jsoupFun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class MainActivity extends AppCompatActivity {
    String URL = "https://cn.morningstar.com/quickrank/default.aspx";
    String TAG = "MainActivity";
    public ArrayList<FundData> list = new ArrayList<>();
    public boolean isDownFlag = true;
    boolean isNext = true;
    private FundDataAdapter rvAdapter;
    private LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        initRecycler();
    }

    private void initRecycler() {

        loading = findViewById(R.id.ll_loadding);

        RecyclerView rvList = findViewById(R.id.rvList);
        rvAdapter = new FundDataAdapter(R.layout.layout_item, list);
        rvList.setAdapter(rvAdapter);
//        rv.setLayoutManager(new GridLayoutManager(this,4));
        rvList.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void checkPermissions() {
        //检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        } else {
            getDataThread();
        }
    }

    //获取数据
    private void getDataThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                getData();
            }
        }.start();
    }

    private void getData() {
        try {
            Document doc = Jsoup.connect(URL).get();
            Elements elementsItem = doc.select("tr[class=gridItem]");
            Elements elements = doc.select("tr[class=gridAlternateItem]");
            for (Element element : elementsItem) {
                getFund(element);
            }

            for (Element element : elements) {
                getFund(element);
            }

            //排序
            Collections.sort(list, new Comparator<FundData>() {
                @Override
                public int compare(FundData o1, FundData o2) {
                    return o1.id.compareTo(o2.id);
                }
            });

            Log.d(TAG, list.size() + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFund(Element element) {
        FundData fundData = new FundData();
        String fundid = element.select("span").text();
        //基金名称和代码和基金类型
        String fundType = element.select("td[class=msDataText]").text();

        //净值日期 单位净值 净值日变动 今年以来回报
        String fundValue = element.select("td[class=msDataNumeric]").text();
        Elements selectImg = element.select("img[src]");
        for (Element eleImg : selectImg) {
            String url = eleImg.attr("src");//获取到src的值
            fundData.imageUrl.add(url);
        }

        String[] fundTypeStr = fundType.split(" ");
        fundData.id = Integer.parseInt(fundid.trim());
        fundData.fundCode = fundTypeStr[0];
        fundData.fundName = fundTypeStr[1];
        fundData.fundType = fundTypeStr[2];

        String[] fundValueStr = fundValue.split(" ");
        fundData.fundDate = fundValueStr[0];
        fundData.fundunitValue = fundValueStr[1];
        fundData.fundChange = fundValueStr[2];
        fundData.fundReturnValue = fundValueStr[3];

        // Log.d(TAG, fundid + "------" + fundValue + "----" + fundType + " ------" );
        list.add(fundData);
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                super.run();
                loading.setVisibility(View.GONE);
                rvAdapter.setNewData(list);
            }
        });

        //处理星级
       /* for (int i = 0; i < list.size(); i++) {

            FundData fundData1 = list.get(i);
            ArrayList<String> imageList = (ArrayList<String>) fundData1.imageUrl;
            for (int j = 0; j < imageList.size(); j++) {
                getStart(imageList.get(j), fundData1);
            }
        }*/


    }

    public static String DATAPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "tessdata/";
    int count = 0;

    private void getStart(String url, final FundData fundData1) {
        count++;

        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(DATAPATH, "start.gif")//
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, e.toString());

                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Log.d(TAG, response.getAbsolutePath());
                        String imageToBase64 = StartBase64.imageToBase64(response.getAbsolutePath());
                        if (imageToBase64.equals(StartBase64.star0)) {
                            setStartCount(0, fundData1);
                        } else if (imageToBase64.equals(StartBase64.star1)) {
                            setStartCount(1, fundData1);
                        } else if (imageToBase64.equals(StartBase64.star2)) {
                            setStartCount(2, fundData1);

                        } else if (imageToBase64.equals(StartBase64.star3)) {

                            setStartCount(3, fundData1);
                        } else if (imageToBase64.equals(StartBase64.star4)) {
                            setStartCount(4, fundData1);

                        } else if (imageToBase64.equals(StartBase64.star5)) {
                            setStartCount(5, fundData1);

                        }
                    }
                });
    }

    private void setStartCount(int i, FundData fundData1) {
        if (count == 1) {
            fundData1.threeYearStar = i;
        } else {
            fundData1.fiveYearStar = i;
            count = 0;
            Log.d(TAG, fundData1.toString());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    getDataThread();
                } else {
                    //用户拒绝授权
                }
                break;
        }
    }


}
