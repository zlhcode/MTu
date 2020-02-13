package com.example.mtu.view;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mtu.spider.utils.UserAgentUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 自定义imageView控件
 */
public class SmartImageView extends androidx.appcompat.widget.AppCompatImageView {


    private static final String TAG = "smartImageView";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            setImageBitmap(bitmap);
        }
    };

    /**
     * 设置一个网络图片并显示到控件中
     *
     * @param image
     */
    public void setImageUrlAndShow(final String image) {
        Log.d(TAG, "setImageUrlAndShow: " + image);
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient httpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(image).addHeader("User-Agent", UserAgentUtils.getRandomUserAgent()).build();
                    Call call = httpClient.newCall(request);
                    Response response = call.execute();
                    if (response != null) {
                        InputStream is = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        Message message = Message.obtain();
                        message.what = 0;
                        message.obj = bitmap;
                        mHandler.sendMessage(message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public SmartImageView(Context context) {
        super(context);
    }

    public SmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
