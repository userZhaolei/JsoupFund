package com.my.jsoupFun;

import android.text.TextUtils;
import android.util.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * zhaolei
 * 时间:2020-03-19
 */
public class StartBase64 {

    public static final String star0 = "data:image/gif;base64,R0lGODlhQgARAPcAAP//////zP//mf//Zv//M///AP/M///MzP/Mmf/MZv/MM//MAP+Z//+ZzP+Zmf+ZZv+ZM/+ZAP9m//9mzP9mmf9mZv9mM/9mAP8z//8zzP8zmf8zZv8zM/8zAP8A//8AzP8Amf8AZv8AM/8AAMz//8z/zMz/mcz/Zsz/M8z/AMzM/8zMzMzMmczMZszMM8zMAMyZ/8yZzMyZmcyZZsyZM8yZAMxm/8xmzMxmmcxmZsxmM8xmAMwz/8wzzMwzmcwzZswzM8wzAMwA/8wAzMwAmcwAZswAM8wAAJn//5n/zJn/mZn/Zpn/M5n/AJnM/5nMzJnMmZnMZpnMM5nMAJmZ/5mZzJmZmZmZZpmZM5mZAJlm/5lmzJlmmZlmZplmM5lmAJkz/5kzzJkzmZkzZpkzM5kzAJkA/5kAzJkAmZkAZpkAM5kAAGb//2b/zGb/mWb/Zmb/M2b/AGbM/2bMzGbMmWbMZmbMM2bMAGaZ/2aZzGaZmWaZZmaZM2aZAGZm/2ZmzGZmmWZmZmZmM2ZmAGYz/2YzzGYzmWYzZmYzM2YzAGYA/2YAzGYAmWYAZmYAM2YAADP//zP/zDP/mTP/ZjP/MzP/ADPM/zPMzDPMmTPMZjPMMzPMADOZ/zOZzDOZmTOZZjOZMzOZADNm/zNmzDNmmTNmZjNmMzNmADMz/zMzzDMzmTMzZjMzMzMzADMA/zMAzDMAmTMAZjMAMzMAAAD//wD/zAD/mQD/ZgD/MwD/AADM/wDMzADMmQDMZgDMMwDMAACZ/wCZzACZmQCZZgCZMwCZAABm/wBmzABmmQBmZgBmMwBmAAAz/wAzzAAzmQAzZgAzMwAzAAAA/wAAzAAAmQAAZgAAMwAAAPn5+ff39+/v7+fn59/f39fX18/Pz8fHx8DAwLi4uLCwsKCgoP///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAOQALAAAAABCABEAAAj/ALEJHEiwoMGDCBMqXMiwocOHECNKnJiQnEWL3y6SM4jxIseOGkOCFKkxY0iBGrWNy+aRoEWVLDcWfLmS5EWYNmnGbFlym7ec5L75tPhR6M+cRoEm1YjyYkaTNp8qDToVKkmpTLOFE/dNKDluXcWFy6aVq1ew38SS3dp129ewY8u2fZs2LtuzcLOh7NYtpLejFvn6BUxOsMa/Gg1fRHxRsUXGTbdB9RpSslO3lSdj1mgZ4+aLnYNujty3rzdtIreVJnc69erWlV+jjl2Y9WyZGLOB6/ZNm9Xcu3v/Dqqbt2+R34oLR678OFFsFsOZzJZWpHSL1MVZn1495HVy2bdjJu+OOxu3kN12gj+fWL159O7ZN44P/znQ+/jz62dKsb9/6P8FSFFAADs=";

    public static final String star1 = "data:image/gif;base64,R0lGODlhQgARAPcAAP//////zP//mf//Zv//M///AP/M///MzP/Mmf/MZv/MM//MAP+Z//+ZzP+Zmf+ZZv+ZM/+ZAP9m//9mzP9mmf9mZv9mM/9mAP8z//8zzP8zmf8zZv8zM/8zAP8A//8AzP8Amf8AZv8AM/8AAMz//8z/zMz/mcz/Zsz/M8z/AMzM/8zMzMzMmczMZszMM8zMAMyZ/8yZzMyZmcyZZsyZM8yZAMxm/8xmzMxmmcxmZsxmM8xmAMwz/8wzzMwzmcwzZswzM8wzAMwA/8wAzMwAmcwAZswAM8wAAJn//5n/zJn/mZn/Zpn/M5n/AJnM/5nMzJnMmZnMZpnMM5nMAJmZ/5mZzJmZmZmZZpmZM5mZAJlm/5lmzJlmmZlmZplmM5lmAJkz/5kzzJkzmZkzZpkzM5kzAJkA/5kAzJkAmZkAZpkAM5kAAGb//2b/zGb/mWb/Zmb/M2b/AGbM/2bMzGbMmWbMZmbMM2bMAGaZ/2aZzGaZmWaZZmaZM2aZAGZm/2ZmzGZmmWZmZmZmM2ZmAGYz/2YzzGYzmWYzZmYzM2YzAGYA/2YAzGYAmWYAZmYAM2YAADP//zP/zDP/mTP/ZjP/MzP/ADPM/zPMzDPMmTPMZjPMMzPMADOZ/zOZzDOZmTOZZjOZMzOZADNm/zNmzDNmmTNmZjNmMzNmADMz/zMzzDMzmTMzZjMzMzMzADMA/zMAzDMAmTMAZjMAMzMAAAD//wD/zAD/mQD/ZgD/MwD/AADM/wDMzADMmQDMZgDMMwDMAACZ/wCZzACZmQCZZgCZMwCZAABm/wBmzABmmQBmZgBmMwBmAAAz/wAzzAAzmQAzZgAzMwAzAAAA/wAAzAAAmQAAZgAAMwAAAPf39+/v7+7u7ufn59/f393d3dfX18/Pz8fHx8DAwLu7u7i4uLCwsKqqqqCgoIiIiHd3d1VVVREREf///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAOsALAAAAABCABEAAAj/ALEJHEiwoEGD6xIqXMiwocOHECNKnEixosWLGDM2PDcRXMePEj1K7HZNW8Rs5rCdTLlSJUSULiGeu2YlIrht32zi1JkT4s2eEFldS2dznciHHo86TFpUaUJtga5JnTo1kDZs48iBu7mO21Zy4wRm3bqt69ewWLVy9QoOrFi1Zdm6VWhFHdVr6moq9OaN4Teg6/j6BSx44d+FhRUeZriCqroVDbcd5cpQskLKCy0nxKxQs9GyDRtT7Ra5b99v2UoHXoda9enUlU2zhs0Q3TVW5YRyZAgOWzhv4LI5NeobuPCGvX8HH57c+PB1rNA9Rceq4TiR2Npax66d4fWE2cltJgfffaE2cQzLmVSIjRtDbzHXtX8ffz7i+u7va9zPv7///wAGyF9AADs=";

    public static final String star2 = "data:image/gif;base64,R0lGODlhQgARAPcAAP//////zP//mf//Zv//M///AP/M///MzP/Mmf/MZv/MM//MAP+Z//+ZzP+Zmf+ZZv+ZM/+ZAP9m//9mzP9mmf9mZv9mM/9mAP8z//8zzP8zmf8zZv8zM/8zAP8A//8AzP8Amf8AZv8AM/8AAMz//8z/zMz/mcz/Zsz/M8z/AMzM/8zMzMzMmczMZszMM8zMAMyZ/8yZzMyZmcyZZsyZM8yZAMxm/8xmzMxmmcxmZsxmM8xmAMwz/8wzzMwzmcwzZswzM8wzAMwA/8wAzMwAmcwAZswAM8wAAJn//5n/zJn/mZn/Zpn/M5n/AJnM/5nMzJnMmZnMZpnMM5nMAJmZ/5mZzJmZmZmZZpmZM5mZAJlm/5lmzJlmmZlmZplmM5lmAJkz/5kzzJkzmZkzZpkzM5kzAJkA/5kAzJkAmZkAZpkAM5kAAGb//2b/zGb/mWb/Zmb/M2b/AGbM/2bMzGbMmWbMZmbMM2bMAGaZ/2aZzGaZmWaZZmaZM2aZAGZm/2ZmzGZmmWZmZmZmM2ZmAGYz/2YzzGYzmWYzZmYzM2YzAGYA/2YAzGYAmWYAZmYAM2YAADP//zP/zDP/mTP/ZjP/MzP/ADPM/zPMzDPMmTPMZjPMMzPMADOZ/zOZzDOZmTOZZjOZMzOZADNm/zNmzDNmmTNmZjNmMzNmADMz/zMzzDMzmTMzZjMzMzMzADMA/zMAzDMAmTMAZjMAMzMAAAD//wD/zAD/mQD/ZgD/MwD/AADM/wDMzADMmQDMZgDMMwDMAACZ/wCZzACZmQCZZgCZMwCZAABm/wBmzABmmQBmZgBmMwBmAAAz/wAzzAAzmQAzZgAzMwAzAAAA/wAAzAAAmQAAZgAAMwAAAPf39+/v7+7u7ufn59/f393d3dfX18/Pz8fHx8DAwLu7u7i4uLCwsKqqqqCgoIiIiHd3d1VVVREREf///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAOsALAAAAABCABEAAAj/ANcJHEiwoMGDCBMqXMiwocOHECNKnOjwHEOLC8Ex1JiRYbdr2hR+DJkwmzlsCk2iLHly4blrVhS+jJkQ3LZvCm3irHlzIatr6RT+DFpzHUeEGo8eTGpQW6BrUKNGDaTNqdSrVLGNIwfO5jpuXcmNw6aVq1ew4MSS3dp129ewYwdaUXdVHU2Bc+veXefNW8FvOwX2/RuYr1+CgA2ukKpuhWLGjgtuO+pVMmW3lgdWLrhYarfHng1u8+v3WzbRpNeZRs1X9WmD6K6xKvcTI8HYs2sbBIctnDdw2ZQK5O0buHCjvX8HP8gKnUBt6FgZbP48usFxHLGlvZ59e0HsArWTJmsqrmA5ks/LEzxPEBu3gt5Whn9PMH57+gPtU9zPv7///wAGyFBAADs=";

    public static final String star3 = "data:image/gif;base64,R0lGODlhQgARAPcAAP//////zP//mf//Zv//M///AP/M///MzP/Mmf/MZv/MM//MAP+Z//+ZzP+Zmf+ZZv+ZM/+ZAP9m//9mzP9mmf9mZv9mM/9mAP8z//8zzP8zmf8zZv8zM/8zAP8A//8AzP8Amf8AZv8AM/8AAMz//8z/zMz/mcz/Zsz/M8z/AMzM/8zMzMzMmczMZszMM8zMAMyZ/8yZzMyZmcyZZsyZM8yZAMxm/8xmzMxmmcxmZsxmM8xmAMwz/8wzzMwzmcwzZswzM8wzAMwA/8wAzMwAmcwAZswAM8wAAJn//5n/zJn/mZn/Zpn/M5n/AJnM/5nMzJnMmZnMZpnMM5nMAJmZ/5mZzJmZmZmZZpmZM5mZAJlm/5lmzJlmmZlmZplmM5lmAJkz/5kzzJkzmZkzZpkzM5kzAJkA/5kAzJkAmZkAZpkAM5kAAGb//2b/zGb/mWb/Zmb/M2b/AGbM/2bMzGbMmWbMZmbMM2bMAGaZ/2aZzGaZmWaZZmaZM2aZAGZm/2ZmzGZmmWZmZmZmM2ZmAGYz/2YzzGYzmWYzZmYzM2YzAGYA/2YAzGYAmWYAZmYAM2YAADP//zP/zDP/mTP/ZjP/MzP/ADPM/zPMzDPMmTPMZjPMMzPMADOZ/zOZzDOZmTOZZjOZMzOZADNm/zNmzDNmmTNmZjNmMzNmADMz/zMzzDMzmTMzZjMzMzMzADMA/zMAzDMAmTMAZjMAMzMAAAD//wD/zAD/mQD/ZgD/MwD/AADM/wDMzADMmQDMZgDMMwDMAACZ/wCZzACZmQCZZgCZMwCZAABm/wBmzABmmQBmZgBmMwBmAAAz/wAzzAAzmQAzZgAzMwAzAAAA/wAAzAAAmQAAZgAAMwAAAPf39+/v7+7u7ufn59/f393d3dfX18/Pz8fHx8DAwLu7u7i4uLCwsKqqqqCgoIiIiHd3d1VVVREREf///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAOsALAAAAABCABEAAAj/ANcJHEiwoMGDCBMqXMiwocOHECNKnOjwHEOLCzEqBMeQ48Ju17QpBCkyIUmF2cxhQ6ky4zUrCs+9jDkzIbht3zbiXMjqWjqFPX8mDLpxnUeEHI8O1BbomtOnTwNpYwq1qlSqVaNqwzaOHLib67h9JTcOG1evYMWCI7tSoBV1VdXBHPg27ly3cKHKJejNW8FvOQf2/RuY4Aq9KwwefqouccHFThsb3HYUbEHKAy0/rtpNMWfPUDtf9uv3W7bJpNeZPojuGqtyPTUObP06tkHasK/JFggOWzhv4LIp5e0buPCDrNAJ1IaOlcHky5s/V76OufOC4zxiW2swu8Dt5AxqJhNXsFzJ5eTNrjRPcHz58+uwcSvore33+XztU9zPv7///wAGqFBAADs=";

    public static final String star4 = "data:image/gif;base64,R0lGODlhQgARAPcAAP//////zP//mf//Zv//M///AP/M///MzP/Mmf/MZv/MM//MAP+Z//+ZzP+Zmf+ZZv+ZM/+ZAP9m//9mzP9mmf9mZv9mM/9mAP8z//8zzP8zmf8zZv8zM/8zAP8A//8AzP8Amf8AZv8AM/8AAMz//8z/zMz/mcz/Zsz/M8z/AMzM/8zMzMzMmczMZszMM8zMAMyZ/8yZzMyZmcyZZsyZM8yZAMxm/8xmzMxmmcxmZsxmM8xmAMwz/8wzzMwzmcwzZswzM8wzAMwA/8wAzMwAmcwAZswAM8wAAJn//5n/zJn/mZn/Zpn/M5n/AJnM/5nMzJnMmZnMZpnMM5nMAJmZ/5mZzJmZmZmZZpmZM5mZAJlm/5lmzJlmmZlmZplmM5lmAJkz/5kzzJkzmZkzZpkzM5kzAJkA/5kAzJkAmZkAZpkAM5kAAGb//2b/zGb/mWb/Zmb/M2b/AGbM/2bMzGbMmWbMZmbMM2bMAGaZ/2aZzGaZmWaZZmaZM2aZAGZm/2ZmzGZmmWZmZmZmM2ZmAGYz/2YzzGYzmWYzZmYzM2YzAGYA/2YAzGYAmWYAZmYAM2YAADP//zP/zDP/mTP/ZjP/MzP/ADPM/zPMzDPMmTPMZjPMMzPMADOZ/zOZzDOZmTOZZjOZMzOZADNm/zNmzDNmmTNmZjNmMzNmADMz/zMzzDMzmTMzZjMzMzMzADMA/zMAzDMAmTMAZjMAMzMAAAD//wD/zAD/mQD/ZgD/MwD/AADM/wDMzADMmQDMZgDMMwDMAACZ/wCZzACZmQCZZgCZMwCZAABm/wBmzABmmQBmZgBmMwBmAAAz/wAzzAAzmQAzZgAzMwAzAAAA/wAAzAAAmQAAZgAAMwAAAPf39+/v7+7u7ufn59/f393d3dfX18/Pz8fHx8DAwLu7u7i4uLCwsKqqqqCgoIiIiHd3d1VVVREREf///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAOsALAAAAABCABEAAAj/ALEJHEiwoMGDCBMqXMiQ4LqHECNKnEixosWLGDNq3Mjx4bmMHzGGvDjSIriM3a5pu5hypcWWLFVezGYOm8hrVkji1JnT4rmdJrd9w8jqWrqLRY9aTIrU6MWTJyVqC3StqlWrgbRNvco161auWLVSBVs1K7Zx5MCB27aOm1py42w+tKKOq7qec+tevRuRrl286/zuBezNm8RvQyWu2Lti4mKr6horZuyYssRtUdetpfjYarfKVz9PDg3a88Rthg1/y0YR3TVW5YqWfOgatuyJtWNfm70u922J4LCF8wYuW+aIrNA91IaO1cTky5s/V76OuXOJ0KtLlzguKjZw5CZqJhMnsZzL5eQjmo84vvz56ukhroeIjZtEb3I76t/Pv7///wAGyF9AADs=";

    public static final String star5 = "data:image/gif;base64,R0lGODlhQgARAPcAAP//////zP//mf//Zv//M///AP/M///MzP/Mmf/MZv/MM//MAP+Z//+ZzP+Zmf+ZZv+ZM/+ZAP9m//9mzP9mmf9mZv9mM/9mAP8z//8zzP8zmf8zZv8zM/8zAP8A//8AzP8Amf8AZv8AM/8AAMz//8z/zMz/mcz/Zsz/M8z/AMzM/8zMzMzMmczMZszMM8zMAMyZ/8yZzMyZmcyZZsyZM8yZAMxm/8xmzMxmmcxmZsxmM8xmAMwz/8wzzMwzmcwzZswzM8wzAMwA/8wAzMwAmcwAZswAM8wAAJn//5n/zJn/mZn/Zpn/M5n/AJnM/5nMzJnMmZnMZpnMM5nMAJmZ/5mZzJmZmZmZZpmZM5mZAJlm/5lmzJlmmZlmZplmM5lmAJkz/5kzzJkzmZkzZpkzM5kzAJkA/5kAzJkAmZkAZpkAM5kAAGb//2b/zGb/mWb/Zmb/M2b/AGbM/2bMzGbMmWbMZmbMM2bMAGaZ/2aZzGaZmWaZZmaZM2aZAGZm/2ZmzGZmmWZmZmZmM2ZmAGYz/2YzzGYzmWYzZmYzM2YzAGYA/2YAzGYAmWYAZmYAM2YAADP//zP/zDP/mTP/ZjP/MzP/ADPM/zPMzDPMmTPMZjPMMzPMADOZ/zOZzDOZmTOZZjOZMzOZADNm/zNmzDNmmTNmZjNmMzNmADMz/zMzzDMzmTMzZjMzMzMzADMA/zMAzDMAmTMAZjMAMzMAAAD//wD/zAD/mQD/ZgD/MwD/AADM/wDMzADMmQDMZgDMMwDMAACZ/wCZzACZmQCZZgCZMwCZAABm/wBmzABmmQBmZgBmMwBmAAAz/wAzzAAzmQAzZgAzMwAzAAAA/wAAzAAAmQAAZgAAMwAAAPn5+ff39+7u7t3d3bu7u6qqqoiIiHd3d1VVVREREf///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAOIALAAAAABCABEAAAj/ALEJHEiwoMGDCBMqXMiwocOHECNKnJhQnEWL3i4avJjxokePHT9+DCmSo0iBHrdd02ZxoziVLEtahClz5sqaL296RMnxmpWWBTH6xOltaM2iP48a1YjNI6tr4HA+jVpzqlSoV6lq1BbomtevXwNp4wq2rFiyZcOO7ZrW61m2bcWitBKubLikFunaxStOL9i7Hv1+BXxRsFfCPFf8XSFS8WDGHx0fhuxR8rVwlC9axszUouVr2xqXDR15tGiwpCubBmrx2zVW3Z6SbP069rXZ4lzDli1St23cvnmzFsfqm0Vt31iJLH48+XLj4pAr/8g8unPq0KV31sbtY7eYx7tnJhsv7rtH7t7BR+/u0fxF9O1j8sS5k6JC+ieb4mdqH+H++v0FSFFAADs=";


    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
}
