package com.dlebedyuk.menuservice.service.impl;

import com.dlebedyuk.menuservice.model.BusinessLaunch;
import com.dlebedyuk.menuservice.repository.BusinessLaunchRepository;
import com.dlebedyuk.menuservice.service.BusinessLaunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BusinessLaunchServiceImpl implements BusinessLaunchService {

    private final BusinessLaunchRepository businessLaunchRepository;

    @Override
    public BusinessLaunch createBusinessLaunch(BusinessLaunch businessLaunch) {
        return businessLaunchRepository.save(businessLaunch);
    }
}
