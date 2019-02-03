package main.kotlin

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import main.kotlin.datas.Course
import main.kotlin.extensions.get404Error

val course1	= Course(1, "C", "Le master !")
val course2 = Course(2, "Java", "Trop de longues phrases, ça me fatigue")
val course3 = Course(3, "Kotlin", "Ca sent bon :)")

fun main(args: Array<String>) {
	val server = embeddedServer(Netty, 8080) {
		routing {
			get("/") {
				call.respondText("Welcome to OpenClassrooms brand new server !", ContentType.Text.Html)
			}
			get("/course/top") {
				call.respond(Gson().toJson(course3))
			}
			get("/course/{id}") {
				val id = call.parameters["id"]
				when(id) {
					"1" -> call.respond(Gson().toJson(course1))
					"2" -> call.respond(Gson().toJson(course2))
					"3" -> call.respond(Gson().toJson(course3))
					else -> call.respond(Course().get404Error(id))
				}
 			}
		}
	}
	server.start(wait = true)
}

