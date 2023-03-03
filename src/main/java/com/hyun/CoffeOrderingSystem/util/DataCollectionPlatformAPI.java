package com.hyun.CoffeOrderingSystem.util;

import java.math.BigDecimal;

public interface DataCollectionPlatformAPI {

    public void sendOrderData(Long member, Long menu, BigDecimal price);
}
