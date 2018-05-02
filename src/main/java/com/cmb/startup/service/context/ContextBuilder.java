package com.cmb.startup.service.context;


import com.cmb.startup.model.ContentModel;
import com.cmb.startup.service.encode.Common;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContextBuilder {

    private static List<Integer> mCodes = new ArrayList<>();;

    private final static String CODEBOOK = "12345";

    // 返回事件号
    public static List<Integer> build(ContentModel model){
        //处理业务逻辑

        //传入事件号，赋值频率数组
        convertTextToCodes("12345");
        return mCodes;
    }

    private static boolean convertTextToCodes(String text) {
        boolean ret = true;

        if ( text!=null && text.length() > 0) {
            mCodes.clear();
            mCodes.add(Common.START_TOKEN);
            int len = text.length();
            for (int i = 0; i < len; ++i) {
                char ch = text.charAt(i);
                int index = CODEBOOK.indexOf(ch);
                if (index > -1) {
                    mCodes.add(index + 1);
                } else {
                    ret = false;
//                    LogHelper.d(TAG, "invalidate char:" + ch);
                    break;
                }
            }
            if (ret) {
                mCodes.add(Common.STOP_TOKEN);
            }
        } else {
            ret = false;
        }

        return ret;
    }
}
