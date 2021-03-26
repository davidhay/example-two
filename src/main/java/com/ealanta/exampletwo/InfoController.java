package com.ealanta.exampletwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private GitInfo gitInfo;

    @RequestMapping(value = "/git", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String getGitSha(){
        return gitInfo.getGitSha();
    }
}
