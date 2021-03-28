package com.ealanta.exampletwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/info")
public class InfoController {

    private final GitInfo info;

    public InfoController(@Autowired GitInfo info){
        this.info = info;
    }

    @RequestMapping(value = "/git")
    @ResponseBody
    GitInfo getGitSha(){
        return info;
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    GitInfo getGitTest(){
        return new GitInfo("tada");
    }

}
