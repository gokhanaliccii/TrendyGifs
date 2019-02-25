package com.gokhanaliccii.trendygifs.domain.mapper

/**
 * Created by gokhan.alici on 24.02.2019
 */
interface EntityMapper<I, O> {

    fun mapTo(input: I): O
}