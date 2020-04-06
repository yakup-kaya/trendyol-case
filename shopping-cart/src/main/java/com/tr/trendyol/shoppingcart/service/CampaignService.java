package com.tr.trendyol.shoppingcart.service;

import com.tr.trendyol.core.service.BaseService;
import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.repository.CampaignRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@Service
@Transactional
public class CampaignService extends BaseService<Campaign, Long, CampaignRepository> {

    public CampaignService(CampaignRepository rep) {
        super(rep);
    }
}
