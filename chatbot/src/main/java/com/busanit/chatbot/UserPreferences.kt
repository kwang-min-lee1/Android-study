package com.busanit.chatbot

import android.content.Context
import android.content.SharedPreferences


// 사용자가 가입할 때 입력한 정보를 저장 하고 불러 오는 방법을 추가
object UserPreferences {

    private const val PREFS_NAME = "user_prefs"
    private const val KEY_GENDER = "gender"
    private const val KEY_HEIGHT = "height"
    private const val KEY_WEIGHT = "weight"
    private const val KEY_AGE = "age"
    private const val KEY_BMR = "bmr"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserInfo(context: Context, gender: String, height: Int, weight: Int, age: Int, bmr: Int) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_GENDER, gender)
        editor.putInt(KEY_HEIGHT, height)
        editor.putInt(KEY_WEIGHT, weight)
        editor.putInt(KEY_AGE, age)
        editor.putInt(KEY_BMR, bmr)
        editor.apply()
    }

    fun getUserInfo(context: Context): Map<String, Any> {
        val prefs = getPreferences(context)
        return mapOf(
            "gender" to prefs.getString(KEY_GENDER, "N/A")!!,
            "height" to prefs.getInt(KEY_HEIGHT, 0),
            "weight" to prefs.getInt(KEY_WEIGHT, 0),
            "age" to prefs.getInt(KEY_AGE, 0),
            "bmr" to prefs.getInt(KEY_BMR, 0)
        )
    }

    // 사용자 정보를 기반으로 미리 정의된 프롬프트를 생성하는 함수를 추가
     fun createPrompt1(userInfo: Map<String, Any>): String {
        val gender = userInfo["gender"] as String
        val height = userInfo["height"] as Int
        val weight = userInfo["weight"] as Int
        val age = userInfo["age"] as Int
        val bmr = userInfo["bmr"] as Int

        return "Prompt 1: 남자이고, 키는 $height cm에 몸무게는 $weight kg이고 나이는 $age 세고, 기초대사량이 $bmr 인 사람이 주 4회 30분 정도 산보정도의 세기로 달리기를 하면서 다이어트를 하려는데 하루에 얼마나 달려야 할까?"
    }

     fun createPrompt2(userInfo: Map<String, Any>): String {
        // 필요한 경우 다른 내용을 작성
        return "Prompt 2: 예시 문구입니다."
    }

     fun createPrompt3(userInfo: Map<String, Any>): String {
        // 필요한 경우 다른 내용을 작성
        return "Prompt 3: 예시 문구입니다."
    }

     fun createPrompt4(userInfo: Map<String, Any>): String {
        // 필요한 경우 다른 내용을 작성
        return "Prompt 4: 예시 문구입니다."
    }
}