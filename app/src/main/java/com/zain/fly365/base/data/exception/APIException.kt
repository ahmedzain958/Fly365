package com.zain.fly365.base.data.exception


data class APIException(
    var code: Int,
    var errors: Errors?,
    override var message: String?,
    var status: Int,
    var title: String,
    var trace: String,
    var type: String
) : RuntimeException()