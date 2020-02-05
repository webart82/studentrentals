package com.student.models

data class mApartmentData(

    var apartmentType:String?,
    var longitude: Double?,
    var latitude: Double?,
    var paymentTerms:  String?,
    var amount: String?,
    var description: String?,
    var thumbNail: String?,
    var roomImages: Any,
    var rooms: List<RoomData>,
    var extraCosts: List<mCostsData>,
    var _id: String?,
    var apartmentName: String?

    )