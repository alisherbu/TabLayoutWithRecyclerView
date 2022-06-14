package com.uznewmax.viewpagerwithtablayout.utils

interface ResourceStore {
    companion object {
        val taxi = listOf(
            Tariff(name = "Ekonom", eta = 2, total_price = 16_000),
            Tariff(name = "Comfort", eta = 1, total_price = 15_000),
            Tariff(name = "Mini", eta = 3, total_price = 14_000)
        )
        val business = listOf(
            Tariff(name = "Business", eta = 5, total_price = 26_000),
            Tariff(name = "Business +", eta = 3, total_price = 36_000),
        )
        val delivery = listOf(
            Tariff(name = "Delivery", eta = 7, total_price = 12_000),
            Tariff(name = "Delivery +", eta = 3, total_price = 19_000),
            Tariff(name = "Cargo", eta = 1, total_price = 11_000),
            Tariff(name = "Cargo +", eta = 10, total_price = 100_000),
        )
        val itemList = listOf(
            taxi, business, delivery
        )
    }

}