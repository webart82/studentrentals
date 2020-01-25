package com.student.Models

data class ExtraCostDatas(
    var name: String?,
    var amount: Int?,
    var method: String?,
    var terms_and_conditions: List<TermsDatas>
)