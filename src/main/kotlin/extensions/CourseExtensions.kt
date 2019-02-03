package main.kotlin.extensions
import main.kotlin.datas.Course

fun Course.get404Error(id:String?) = "{\"status\":\"404\", \"message\": \"Sorry ! No course found for id: $id\"}"