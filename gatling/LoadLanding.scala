package asw.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LoadLanding extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
		.inferHtmlResources()
		.acceptHeader("text/html, */*; q=0.01")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("es-ES,es;q=0.8,en;q=0.6")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "*/*",
		"Origin" -> "http://localhost:8090")

	val headers_1 = Map("X-Requested-With" -> "XMLHttpRequest")



	val scn = scenario("LoadLanding")
		.exec(http("request_0")
			.patch("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/")
			.headers(headers_1),
            http("request_2")
			.patch("/")
			.headers(headers_0),
            http("request_3")
			.get("/")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_4")
			.patch("/")
			.headers(headers_0)
			.resources(http("request_5")
			.get("/")
			.headers(headers_1)))
		.pause(2)
		.exec(http("request_6")
			.get("/")
			.headers(headers_1)
			.resources(http("request_7")
			.patch("/")
			.headers(headers_0)))

	setUp(
		scn.inject(
			nothingFor(4 seconds)
			, atOnceUsers(10)
			, rampUsers(50) over(6 seconds) //300 in total
		).protocols(httpProtocol))
}