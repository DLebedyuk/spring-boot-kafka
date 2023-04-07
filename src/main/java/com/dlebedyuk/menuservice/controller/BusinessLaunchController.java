package com.dlebedyuk.menuservice.controller;

import com.dlebedyuk.menuservice.model.BusinessLaunch;
import com.dlebedyuk.menuservice.model.Dish;
import com.dlebedyuk.menuservice.service.BusinessLaunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BusinessLaunchController {

    private final BusinessLaunchService businessLaunchService;

    @PostMapping("/business-launches")
    public BusinessLaunch createBusinessLaunch(@RequestBody BusinessLaunch businessLaunch) {
        return businessLaunchService.createBusinessLaunch(businessLaunch);
    }
}
