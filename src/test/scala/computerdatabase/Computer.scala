package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Computer extends Simulation {

  val httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
    .inferHtmlResources()
    .acceptHeader("text/css,*/*;q=0.1")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9,ru;q=0.8,uk;q=0.7")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36")

  val scn = scenario("ComputerDataBase")
    .exec(
      http("Get Computers List")
        .get("/computers")
    )

    .exec(
      http("Do Search")
        .get("/computers?f=ACE")
    )

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}