package cn.lalaki.demo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.sourceforge.pinyin4j.BasePinyinHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.lalaki.demo19999.R;

/**
 * Created on 2024-06-02
 *
 * @author lalaki (i@lalaki.cn)
 * @since 测试类
 * 在 assets 目录下，我放置了一个电子书文件用于测试
 */
public class MainActivity extends AppCompatActivity implements TextWatcher, Runnable {
    private TextView textView;
    private static final String TAG = BasePinyinHelper.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = findViewById(R.id.textView);
        ((EditText) findViewById(R.id.editText)).addTextChangedListener(this);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(this);
        service.shutdownNow();
        BasePinyinHelper.toHanyuPinyinStringArray(this, getString(R.string.msg), new BasePinyinHelper() {
            @Override
            protected void onPinyinStringArray(List<String[]> pinyinStringArrayList) {
                for (String[] pinyin : pinyinStringArrayList) {
                    textView.append(String.join("", pinyin));
                }
            }
        });
    }

    @Override
    public void run() {
        try (InputStream inputStream = getAssets().open("dzs.txt")) {
            try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                try (BufferedReader br = new BufferedReader(reader)) {
                    long startTime = System.currentTimeMillis();
                    String str;
                    while ((str = br.readLine()) != null) {
                        for (char ch : str.toCharArray()) {
                            String[] pinyin = BasePinyinHelper.toHanyuPinyinStringArray(MainActivity.this, ch);
                            Log.d(TAG, String.join(",", pinyin));
                        }
                    }
                    Log.d(TAG, String.format("Time consumed: %d", System.currentTimeMillis() - startTime));
                }
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    private final ArrayList<String[]> pinyinList = new ArrayList<>();

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        pinyinList.clear();
        for (char i : s.toString().toCharArray()) {
            pinyinList.add(BasePinyinHelper.toHanyuPinyinStringArray(this, i));
        }
        if (!pinyinList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String[] pinyinArr : pinyinList) {
                if (pinyinArr == null) {
                    continue;
                }
                for (String pinyin : pinyinArr) {
                    sb.append(pinyin).append(",");
                }
            }
            textView.setText(sb.toString());
        } else {
            textView.setText("");
        }
    }
}