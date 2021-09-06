package com.autoservice.service;

import com.autoservice.model.Goods;
import com.autoservice.repository.GoodsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public void create(Goods goods) {
        log.info("Goods service in create, {}", goods);
        this.goodsRepository.save(goods);
    }

    @Override
    public void update(Goods goods) {
        log.info("Goods service in update, {}", goods);
        this.goodsRepository.save(goods);
    }
}
