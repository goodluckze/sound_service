package com.cmb.startup.service;

import com.cmb.startup.SoundContent;
import com.cmb.startup.dao.SoundContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CUserService {

    @Autowired
    private SoundContentDao soundContentDao;

    public SoundContent getSoundContent(String soundId){
        return soundContentDao.findByEventId(soundId);
    }
}
