package com.ealanta.exampletwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Clock;

@Controller
@RequestMapping("/api")
public class TimeController {

    @Autowired
    private Clock clock;

    @ResponseBody
    @RequestMapping(path="/time", method = RequestMethod.GET)
    public Time getTime() {
        return new Time(clock.instant());
    }
}
