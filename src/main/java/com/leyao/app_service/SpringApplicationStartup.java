package com.leyao.app_service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.leyao.app_service.dao.configuration.DimensionConfig;
import com.leyao.app_service.service.IDimensionConfigService;

public class SpringApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        IDimensionConfigService dimensionConfigService = event.getApplicationContext()
                .getBean(IDimensionConfigService.class);
        dimensionConfigService.initDimensionConfig();
        System.out.println(
                "DimensionConfig.rEventCategoryEnumList.size()=" + DimensionConfig.rEventCategoryEnumList.size());
    }
}