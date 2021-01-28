package practice.event

import scala.annotation.tailrec

abstract class Simulation {
  type Action = () => Unit

  case class Event(time: Int, action: Action)

  private type Agenda = List[Event]
  private var agenda: Agenda = List()

  private var curTime = 0

  def currentTime: Int = curTime

  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(curTime + delay, () => block)
    agenda = insert(agenda, item)
  }

  private def insert(agenda: List[Event], item: Event): List[Event] = agenda match {
    case first :: rest if first.time <= item.time => first :: insert(rest, item)
    case _ => item :: agenda
  }

  @tailrec
  private def loop(): Unit = agenda match {
    case first :: rest =>
      agenda = rest
      curTime = first.time
      first.action()
      loop()
    case Nil =>
  }

  def run(): Unit = {
    afterDelay(0) {
      println("*** Simulation started, time = " + curTime + " ***")
    }
    loop()
  }
}

trait Parameters {
  def InverterDelay = 2

  def AndGateDelay = 3

  def OrGateDelay = 5
}

abstract class Gates extends Simulation {

  def InverterDelay: Int

  def AndGateDelay: Int

  def OrGateDelay: Int

  class Wire() {

    private var sigVal: Boolean = false
    private var actions: List[Action] = List()

    def getSignal: Boolean = sigVal

    def setSignal(value: Boolean): Unit =
      if (value != sigVal) {
        this.sigVal = value
        actions foreach (_ ())
      }

    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }
  }

  def probe(name: String, wire: Wire): Unit = {
    def probeAction(): Unit = {
      println(s"$name $currentTime value = ${wire.getSignal}")
    }

    wire addAction probeAction
  }

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction(): Unit = {
      val inputSignal = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSignal
      }
    }

    input addAction invertAction
  }

  def andGate(a: Wire, b: Wire, output: Wire): Unit = {
    def andAction(): Unit = {
      val aSig = a.getSignal
      val bSig = b.getSignal
      afterDelay(AndGateDelay) {
        output setSignal (aSig & bSig)
      }
    }

    a addAction andAction
    b addAction andAction
  }

  def orGate(a: Wire, b: Wire, output: Wire): Unit = {
    def orAction(): Unit = {
      val aSig = a.getSignal
      val bSig = b.getSignal
      afterDelay(OrGateDelay) {
        output setSignal (aSig | bSig)
      }
    }

    a addAction orAction
    b addAction orAction
  }

  def orGateAlt(a: Wire, b: Wire, output: Wire): Unit = {
    val notA, notB, notOut = new Wire
    inverter(a, notA)
    inverter(b, notB)
    andGate(notA, notB, notOut)
    inverter(notOut, output)
  }
}

abstract class Circuits extends Gates {

  def halfAdder(a: Wire, b: Wire, s: Wire, c: Wire): Unit = {
    val d, e = new Wire
    orGateAlt(a, b, d)
    andGate(a, b, c)
    inverter(c, e)
    andGate(d, e, s)
  }

  def fullAdder(a: Wire, b: Wire, cin: Wire, sum: Wire, cout: Wire): Unit = {
    val s, c1, c2 = new Wire
    halfAdder(b, cin, s, c1)
    halfAdder(a, s, sum, c2)
    orGate(c1, c2, cout)
  }

}

object DiscreteEventSimulation extends App {

  object TestSimulation extends Circuits with Parameters

  import TestSimulation._

  val in1, in2, sum, carry = new Wire
  halfAdder(in1, in2, sum, carry)
  probe("sum", sum)
  probe("carry", carry)

  in1 setSignal true
  run()
  in2 setSignal true
  run()
  in1 setSignal false
  run()
}
