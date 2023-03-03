package com.hyun.CoffeOrderingSystem.controller;

import com.hyun.CoffeOrderingSystem.dto.PointChargeReq;
import com.hyun.CoffeOrderingSystem.dto.Response;
import com.hyun.CoffeOrderingSystem.service.MenuService;
import com.hyun.CoffeOrderingSystem.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderingSystemController {

    private final MenuService menuService;
    private final PointService pointService;

    // 커피 메뉴 목록 조회
    @GetMapping("/menu")
    public ResponseEntity<?> getMenuList() {
        Response response = new Response("success", "메뉴 리스트 조회 성공", menuService.getMenuList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 포인트 충전
    @PostMapping("/poinst/charge")
    public ResponseEntity<?> chargePoint(@RequestBody PointChargeReq request) {
        pointService.PointCharge(request);
        Response response = new Response("success", "포인트 충전 성공", "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 커피 주문 및 결제


    // 인기메뉴 목록 조회
}
