import java.lang.Math.{cos, sin, toRadians}

import de.h2b.scala.lib.simgraf._
import de.h2b.scala.lib.simgraf.shapes._


object Sandbox extends App {
  lazy val _360_DEGREES = 360
  lazy val circlesRadius = 22
  lazy val polarRadius = 400 // radius from (0,0) in polar coordinates

  override def main(args: Array[String]) = {
    //debug
    drawing
  }

  private def debug = {
    println(step(0))
    println(step(45))
    println(step(90))
  }

  private def drawing = {
    val screen = initScreen()

    val nCircles = 16
    val angleStep = (_360_DEGREES / nCircles)
    for (n <- 1 to nCircles) {
      val circlePos = step(n * angleStep)
      val circle = Circle(circlePos, circlesRadius)

      screen.activeColor = Color.Green
      screen.fill(circle)

      screen.activeColor = Color.Black
      screen.draw(Text(circlePos, n.toString))
    }

    //draw line separation
    val lineStart = step((nCircles/2 - 1) * angleStep + _360_DEGREES / (2 * nCircles))
    val lineEnd = step(-angleStep/2 - 1)
    screen.activeColor = Color.Black
    screen.draw(Line(lineEnd, lineStart))
  }

  private def step(degrees: Double) = {
    val xRadians = cos(toRadians(degrees))
    val yRadians = sin(toRadians(degrees))

    Point(polarRadius * xRadians, polarRadius * yRadians)
  }

  def initScreen() = {
    // world is used to define the coordinates, you need the negative axis part if
    // if want to place stuff at (0,0) and expect it to be the center of the screen
    val screen = World(Point(-1000, -500), Point(1000, 500))(Pixel(0, 0), 2000, 1000, "Circles")

    screen.clear(Color.White)

    screen
  }
}
