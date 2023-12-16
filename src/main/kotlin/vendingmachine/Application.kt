package vendingmachine
import camp.nextstep.edu.missionutils.Console.readLine
fun main() {

    val inputHandler = InputHandler()
    val vendingSum = inputHandler.readVendingSum()

    println("\n자판기가 보유한 동전")
    generateCoin(vendingSum)

    println("\n상품명과 가격, 수량을 입력해 주세요.")
    val inputs = readLine().replace("[", "").replace("]", "")

    val menus = mutableMapOf<String, Int>()
    val quantity = mutableMapOf<String, Int>()
    inputs.split(';').map {
        val (menu, price, qty) = it.split(',')
        menus[menu] = price.toInt()
        quantity[menu] = qty.toInt()

    }


    println("\n투입 금액을 입력해 주세요.")
    val inputPrice = readLine().toInt()


    val remainAmount = purchaseItem(inputPrice, menus, quantity)
    calculateRemainedAmount(remainAmount, vendingSum)


}
