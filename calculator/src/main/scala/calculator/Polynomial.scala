package calculator

object Polynomial extends PolynomialInterface {
  def computeDelta(a: Signal[Double], b: Signal[Double],
                   c: Signal[Double]): Signal[Double] = {
    Signal(b() * b() - 4 * a() * c())
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
                       c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {

    def denominator: Signal[Double] = Signal(2 * a())

    def numeratorLeft: Signal[Double] = Signal(-1 * b())

    def rootDelta: Signal[Double] = Signal(math.sqrt(delta()))

    Signal {
      if (delta().doubleValue() < 0.0)
        Set()
      else if (delta().doubleValue() == 0.0)
        Set(numeratorLeft() / denominator())
      else
        Set((numeratorLeft() + math.sqrt(delta())) / denominator(), (numeratorLeft() - math.sqrt(delta())) / denominator())
    }
  }
}
