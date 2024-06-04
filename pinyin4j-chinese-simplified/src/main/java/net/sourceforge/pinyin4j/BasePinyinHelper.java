/**
 * This file is part of pinyin4j (http://sourceforge.net/projects/pinyin4j/) and distributed under
 * GNU GENERAL PUBLIC LICENSE (GPL).
 *
 * <p>pinyin4j is free software; you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * <p>pinyin4j is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with pinyin4j.
 */
package net.sourceforge.pinyin4j;
import android.content.Context;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created on yyyy-MM-dd
 *
 * <p>A class provides several utility functions to convert Chinese characters (both Simplified and
 * Tranditional) into various Chinese Romanization representations
 *
 * @author Li Min (xmlerlimin@gmail.com)
 * @author lalaki (i@lalaki.cn)
 * @since 中文字符转汉语拼音的工具类 <a href="https://sourceforge.net/projects/pinyin4j/">pinyin4j</a>
 */
public abstract class BasePinyinHelper extends java.util.Properties {
    /***
     * 中文字符转汉语拼音
     * @param context android context
     * @param ch 中文字符char类型
     * @return 返回拼音数组，String类型，不会为null
     */
    public static String[] toHanyuPinyinStringArray(Context context, char ch) {
        String[] pinyinArr = new String[0];
        String record = PinyinHelperWrapper.with(context).getProperty(Integer.toHexString(ch));
        if (record != null) {
            pinyinArr = record.split("&");
        }
        return pinyinArr;
    }
    /***
     * 中文字符转汉语拼音
     * @param context android context
     * @param chs 中文字符String类型
     * @param callback 回调
     */
    public static void toHanyuPinyinStringArray(Context context, String chs, BasePinyinHelper callback) {
        if (callback != null && chs != null && chs.trim().length() > 0) {
            ExecutorService pool = PinyinHelperWrapper.with(context).mPool;
            pool.execute(() -> {
                final ArrayList<String[]> pinyinList = new ArrayList<>();
                for (char ch : chs.toCharArray()) {
                    pinyinList.add(toHanyuPinyinStringArray(context, ch));
                }
                new Handler(context.getMainLooper()).post(() -> callback.onPinyinStringArray(pinyinList));
            });
        }
    }
    public BasePinyinHelper(Context context) {
        try {
            this.load(context.getAssets().open("map/zdic.txt"));
        } catch (Throwable ignored) {
        }
    }
    protected BasePinyinHelper() {
    }
    protected abstract void onPinyinStringArray(List<String[]> pinyinStringArrayList);
    private static final class PinyinHelperWrapper extends BasePinyinHelper {
        private static volatile BasePinyinHelper.PinyinHelperWrapper sInstance = null;
        private final ExecutorService mPool = Executors.newFixedThreadPool(4);
        private PinyinHelperWrapper(Context context) {
            super(context);
        }
        /***
         * 传递 android context 到工具类
         * @param context android context
         * @return 返回工具类的子类实例
         */
        public static BasePinyinHelper.PinyinHelperWrapper with(Context context) {
            if (sInstance == null) {
                synchronized (BasePinyinHelper.PinyinHelperWrapper.class) {
                    if (sInstance == null) {
                        sInstance = new BasePinyinHelper.PinyinHelperWrapper(context);
                    }
                }
            }
            return sInstance;
        }
        protected void onPinyinStringArray(List<String[]> val1) {
        }
    }
}