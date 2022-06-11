package com.uznewmax.viewpagerwithtablayout.utils

interface ResourceStore {
    companion object {
        val taxi = listOf(
            Tariff(name = "Ekonom"),
            Tariff(name = "Comfort"),
            Tariff(name = "Mini")
        )
        val business = listOf(
            Tariff(name = "Business"),
            Tariff(name = "Business"),
            Tariff(name = "Business\nplus")
        )
        val delivery = listOf(
            Tariff(name = "Delivery"),
            Tariff(name = "Delivery\nplus"),
            Tariff(name = "Cargo")
        )
        val itemList = listOf(
            taxi, business, delivery
        )
    }

}