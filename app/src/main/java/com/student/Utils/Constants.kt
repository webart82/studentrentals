package com.student.Utils

object Constants {
      val BASE_IP:String = "192.168.8.103"
      val BASE_URL:String = "http://"+ BASE_IP + ":8080/api/v1/"
      var IMAGE_BASE_URL: String = "http://1"+ BASE_IP+":8080/"
      var PARCEL_BUNDLE:String = "PARCEL_BUNDLE"
      var PARCEL_KEY: String = "PARCEL_KEY"
      var SINGLE_IMAGE: Int = 1
      var SELECT:Char = 'P'
      var PICK_IMAGE_MULTIPLE: Int =1
      var TAG = javaClass.simpleName
}

