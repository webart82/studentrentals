package com.student.models

data class ExtraCostDatas(
    var name: String?,
    var amount: Int?,
    var method: String?,
    var terms_and_conditions: List<TermsDatas>
)