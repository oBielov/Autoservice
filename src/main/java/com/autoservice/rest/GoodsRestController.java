package com.autoservice.rest;

import com.autoservice.model.Goods;
import com.autoservice.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v01/goods")
@Tag(name = "Товары", description = "Работа с базой товаров")
public class GoodsRestController {

    @Autowired
    GoodsService goodsService;

    @Operation(summary = "Создание нового товара в базе" )
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goods> saveGoods(@RequestBody Goods goods){
        if(goods == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.goodsService.create(goods);
        return new ResponseEntity<>(goods, HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение существующего товара" )
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goods> updateGoods(@RequestBody Goods goods){
        if(goods == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.goodsService.update(goods);
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

}
