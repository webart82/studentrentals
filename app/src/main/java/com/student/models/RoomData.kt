package com.student.models

data class RoomData(
    var id: Int?,
    var apartment_title: String?,
    var location_name: String?,
    var number_of_reviews: Any?,
    var price: Int?,
    var price_per_month: Int?,
    var thumbNails: String?,
    var apartment_category: String?,
    var longitude: Double?,
    var latitude: Double?,
    var owners_info: OwnersInfoData,
    var infos: List<InfosData>,
    var extra_costs: List<ExtraCostDatas>

    )