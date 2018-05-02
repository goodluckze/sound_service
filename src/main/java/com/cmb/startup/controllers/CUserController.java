package com.cmb.startup.controllers;


import com.cmb.startup.SoundContent;
import com.cmb.startup.service.CUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CUserController {

    @Autowired
    private CUserService cUserService;

    @RequestMapping("/content")
    public SoundContent getContent(@RequestParam(value = "sound_id",required = false) String soundId){
        return cUserService.getSoundContent(soundId);
    }
}
