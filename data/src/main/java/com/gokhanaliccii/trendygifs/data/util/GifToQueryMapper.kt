package com.gokhanaliccii.trendygifs.data.util

import com.google.gson.annotations.SerializedName
import java.util.LinkedHashMap

/**
 * Created by gokhan.alici on 24.02.2019
 */

fun <T : Any> toQueryMap(t: T): Map<String, String> {
    val clazz = t::class.java
    val fieldMap = LinkedHashMap<String, String>()

    val declaredFields = clazz.getDeclaredFields()

    for (field in declaredFields) {
        if (field == null) continue

        if (!field.isAccessible) {
            field.isAccessible = true
        }

        var name = field.name
        if (field.isAnnotationPresent(SerializedName::class.java)) {
            name = field.getAnnotation(SerializedName::class.java).value
        }

        try {
            val o = field.get(t)
            fieldMap[name] = o.toString()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

    return fieldMap
}