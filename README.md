# Coffee-Ordering-System
## 1. 서비스 설명
- 커피숍 주문 시스템을 구현해 봅시다.
- 커피 주문에 필요한 메뉴를 구성하고 조회가 가능해야 합니다.
- 커피 주문은 포인트로 가능합니다.
- 커피 주문내역을 통해 인기있는 메뉴를 추천합니다.

## 2. 요구사항
- 아래 API들을 구현합니다.
    1) 커피메뉴목록조회API
    2) 포인트 충전하기 API
    3) 커피 주문, 결제하기 API
    4) 인기메뉴 목록 조회 API
- 작성하신 어플리케이션이 다수의 서버에 다수의 인스턴스로 동작하더라도 기능에 문제가 없도록 설계되어야 합니다.
- 각 기능 및 제약사항에 대한 단위테스트를 반드시 작성합니다.
- 동시성 이슈를 고려하여 구현합니다.
- 데이터 일관성을 고려하여 구현합니다.

## 3. API 설명
[API 명세서 바로가기](https://www.notion.so/API-60360275f2e944559b81d79d1d6b5e55?pvs=4)
1) 커피 메뉴 목록 조회 API
    - 커피 정보(메뉴ID, 이름, 가격)을 조회하는 API를 작성합니다.
2) 포인트 충전 하기 API
    - 결제는 포인트로만 가능하며, 포인트를 충전하는 API를 작성합니다.
    - 사용자 식별값, 충전금액을 입력 받아 포인트를 충전합니다. (1원=1P)
3) 커피 주문/결제 하기 API
    - 사용자 식별값, 메뉴ID를 입력 받아 주문을 하고 결제를 진행합니다.
    - 결제는 포인트로만 가능하며, 충전한 포인트에서 주문금액을 차감합니다.
    - 주문 내역을 데이터 수집 플랫폼으로 실시간 전송하는 로직을 추가합니다.
    (Mock API 등을 사용하여 사용자 식별값, 메뉴ID, 결제금액을 전송합니다.)
4) 인기메뉴 목록 조회 API
    - 최근 7일간 인기있는 메뉴 3개를 조회하는 API 작성합니다.
    - 메뉴별 주문 횟수가 정확해야 합니다.

## 4. ERD
![ERD2](https://user-images.githubusercontent.com/28504937/222832437-5b54a990-c2a5-4c83-a78f-0266656fea54.png)

### DDL
```SQL
CREATE TABLE menus ( 
	menu_id BIGINT PRIMARY KEY AUTO_INCREMENT, 
	name varchar(50) NOT NULL, 
	price DECIMAL(10, 2) NOT NULL DEFAULT 0 
);

CREATE TABLE members ( 
	user_id BIGINT PRIMARY KEY AUTO_INCREMENT, 
	point DECIMAL(10, 2) NOT NULL DEFAULT 0
);

CREATE TABLE orders (
	`order_id` bigint NOT NULL AUTO_INCREMENT,
	`member_id` bigint NOT NULL,
	`menu_id` bigint NOT NULL,
	`order_date` date NOT NULL,
	`order_price` decimal(10,2) NOT NULL,
	PRIMARY KEY (`order_id`),
	FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
	FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`)
);
```

