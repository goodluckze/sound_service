package com.cmb.startup.service;


import com.cmb.startup.BUserInfo;
import com.cmb.startup.SoundContent;
import com.cmb.startup.dao.BUserInfoDao;
import com.cmb.startup.dao.SoundContentDao;
import com.cmb.startup.model.ContentModel;
import com.cmb.startup.model.SoundRegistry;
import com.cmb.startup.service.context.ContextBuilder;
import com.cmb.startup.service.encode.*;
import com.cmb.startup.service.encode.Buffer.BufferData;
import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class BUserService implements Encoder.Callback,SinGenerator.Callback{

    private String mCodeBook;

    //
    private final static int DEFAULT_GEN_DURATION = 100;

    private Buffer mBuffer;

    @Autowired
    private BUserInfoDao bUserInfoDao;

    @Autowired
    private SoundContentDao soundContentDao;

    public String buildSound(SoundRegistry registry){
        //
        SoundContent content = new SoundContent();
        content.setbUserId(registry.getbUserId());
        content.setbUserName(getBUserNameById(registry.getbUserId()).getRealName());
        content.setType(registry.getContentType());
        content.setContent(registry.getContent());
        content.setStatus("A");

        // 将商户内容写入数据库取得id
        Long keyid = soundContentDao.add(content);
        // 生成音频事件号
        String evntid = EventIDBuilder.getEventId(keyid);
        // 检查事件号是否重复，并重新生成
        evntid = eventAvailible(evntid,keyid);
        // 更新到事件
        soundContentDao.updateEventId(keyid,evntid);
        // 事件id转化为音频

        return evntid;
    }

    public String eventAvailible(String eventId,Long keyid){
        Object obj = soundContentDao.findByEventId(eventId);
        while (obj !=null){
            Log.verbose("重复事件号："+keyid);
            eventId = EventIDBuilder.getEventId(keyid);
            eventAvailible(eventId,keyid);
        }
        return eventId;

    }
    public BUserInfo getBUserNameById(long id){
        return bUserInfoDao.findBUserById(id);
    }

/*
    public void buildSoud(ContentModel model){
        //
//        private List<Integer> mCodes = new ArrayList<Integer>();
        // 频率数组

        List<Integer> frameList = ContextBuilder.build(model);


        mBuffer = new Buffer(Common.DEFAULT_BUFFER_COUNT, Common.DEFAULT_BUFFER_SIZE);

        Encoder encoder = new Encoder(this,
                Common.DEFAULT_SAMPLE_RATE,
                Common.DEFAULT_BUFFER_SIZE,
                Common.DEFAULT_BUFFER_COUNT);


        encoder.encode(frameList,DEFAULT_GEN_DURATION);

        // 音频byte[]
        if(mBuffer.getFull().mData.length>0)
            getFile(mBuffer.getFull().mData,"/","1ddddd.pcm");
    }

*/
    @Override
    public void freeEncodeBuffer(BufferData buffer) {
        if (null != buffer) {
            mBuffer.putFull(buffer);
        }
    }

    @Override
    public BufferData getEncodeBuffer() {
        return mBuffer.getEmpty();
    }


    @Override
    public BufferData getGenBuffer() {
        return getEncodeBuffer();
    }

    @Override
    public void freeGenBuffer(BufferData buffer) {
        freeEncodeBuffer(buffer);
    }



    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
