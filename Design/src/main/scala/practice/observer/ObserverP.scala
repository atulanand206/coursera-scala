package practice.observer

trait Subscriber {
  def handler(publisher: Publisher)
}

trait Publisher {
  private var subscribers: Set[Subscriber] = Set()

  def subscribe(subscriber: Subscriber): Unit =
    subscribers += subscriber

  def unsubscribe(subscriber: Subscriber): Unit =
    subscribers -= subscriber

  def publish(): Unit =
    subscribers.foreach(_.handler(this))
}

class BankAccount extends Publisher {
  private var balance = 0

  def currentBalance: Int = balance

  def deposit(amount: Int): Unit =
    if (amount > 0) {
      balance += amount
      publish()
    }

  def withdraw(amount: Int): Unit =
    if (0 < amount && amount <= balance) {
      balance -= amount
      publish()
    } else
      throw new Error("Insufficient Funds")
}

class Consolidator(observed: List[BankAccount]) extends Subscriber {
  observed.foreach(_.subscribe(this))

  private var total: Int = _
  compute()

  private def compute(): Unit =
    total = observed.map(_.currentBalance).sum

  override def handler(publisher: Publisher): Unit = compute()

  def totalBalance: Unit = println(total)
}

object ObserverP extends App {

  val a, b = new BankAccount
  val c = new Consolidator(List(a, b))
  c.totalBalance

  a deposit 20
  c.totalBalance

  b deposit 30
  c.totalBalance


}
