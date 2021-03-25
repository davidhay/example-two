package com.ealanta.exampletwo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/info")
public class InfoController {

    @Value("${info.git.sha}")
    private String gitSha;

    @RequestMapping(value = "/git", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    String getGitSha(){
        return gitSha;
    }
}
