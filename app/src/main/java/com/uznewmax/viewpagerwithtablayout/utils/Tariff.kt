package com.uznewmax.viewpagerwithtablayout.utils

data class Tariff(
    val alias: String? = null,
    val base_fare: Int? = null,
    val car_image: String? = null,
    val conditioner_enabled_by_default: Boolean? = null,
    val description: String? = null,
    val eta: Int? = null,
    val extra_description: String? = null,
    val id: Int? = null,
    val is_default: Boolean? = null,
    val is_surge_price: Boolean? = null,
    val name: String,
    val payment_type_ids: List<Int>? = null,
    val pin_busy: String? = null,
    val pin_default: String? = null,
    val sort: Int? = null,
    val state: String?=null,
    val surge_price: Int? = null,
    val total_price: Int? = null
)