package com.tr.trendyol.shoppingcart.controller;

import com.tr.trendyol.core.repository.CrudResource;
import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.repository.CampaignRepository;
import com.tr.trendyol.shoppingcart.service.CampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@RestController
@RequestMapping(CampaignController.URL)
public class CampaignController extends CrudResource<Campaign, Long, CampaignRepository, CampaignService> {

    protected static final String URL = "/api/v1/campaigns";

    @Override
    protected String getEntityName() {
        return "campaign";
    }

    @Override
    protected String getBaseUrl() {
        return CampaignController.URL;
    }

}
