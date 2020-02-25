package com.student.Utils

object Constants {
      val BASE_IP:String = "https://rcerosapi.herokuapp.com/"
      val BASE_URL:String = "http://"+ BASE_IP + ":8080/api/v1/"
      var IMAGE_BASE_URL: String = "http://"+ BASE_IP+":8080/"
      val REMOTE_BASE_URL: String = "https://rcerosapi.herokuapp.com/api/v1/"
      var PARCEL_BUNDLE:String = "PARCEL_BUNDLE"
      var PARCEL_KEY: String = "PARCEL_KEY"
      var SINGLE_IMAGE: Int = 1
      var SELECT:Char = 'P'
      var PICK_IMAGE_MULTIPLE: Int =1
      var TAG = javaClass.simpleName
}

