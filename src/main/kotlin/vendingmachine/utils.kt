package vendingmachine
import camp.nextstep.edu.missionutils.Randoms
import camp.nextstep.edu.missionutils.Console.readLine

val coins = mutableMapOf(500 to 0, 100 to 0, 50 to 0, 10 to 0)

fun increaseNumber(key: Int){
    coins[key] = (coins[key] ?: 0) + 1
}

fun generateCoin(vendingSum: Int){


    val numbers = mutableListOf(500,100,50,10)

    var remainingAmount = vendingSum
    while (remainingAmount > 0){
        val coinValue = Randoms.pickNumberInList(numbers)
        if (remainingAmount-coinValue >= 0){
            remainingAmount -= coinValue
            increaseNumber(coinValue)
        }

    }

    coins.forEach { (key, value) ->
        println("${key}원 - ${value}개")
    }




}

fun calculateRemainedAmount(amount: Int, vendingSum: Int){

    println("\n투입 금액: ${amount}원")
    println("잔돈")

    when{
        amount > vendingSum -> {
            coins.forEach { (key, value) ->
                if (value!=0) println("${key}원 - ${value}개")
            }
        }
        else -> {

            var remainAmount = amount
            val coinCount = mutableMapOf<Int, Int>()

            for (coin in coins.keys) {
                val requiredCount = (remainAmount / coin) * coin
                val availableCount = coins[coin]?.times(coin)

                if (requiredCount < availableCount!!){
                    remainAmount -=requiredCount
                    coinCount[coin] = requiredCount / coin
                } else {
                    remainAmount -= availableCount
                    coinCount[coin] = availableCount / coin
                }

            }

            coinCount.forEach { (key, value) ->
                if (value!=0) println("${key}원 - ${value}개")
            }


        }
    }


}

fun purchaseItem(inputPrice: Int, menus: MutableMap<String, Int>, quantities: MutableMap<String, Int>): Int{

    var remainAmount = inputPrice
    val minValue = menus.values.min()


    while (remainAmount > 0){

        println("\n투입 금액: ${remainAmount}원")
        println("구매할 상품명을 입력해 주세요.")
        val item = readLine()

        val calculation = remainAmount - menus[item]!!
        val allValuesAreZero = quantities.values.all { it == 0 }
        when {
            calculation < 0 -> break
            calculation < minValue -> {
                remainAmount -= menus[item]!!
                break
            }
            allValuesAreZero -> {
                remainAmount -= menus[item]!!
                break
            }
            calculation >= 0 -> {
                quantities[item] = (quantities[item] ?: 0) - 1
                remainAmount -= menus[item]!!
            }

        }

    }

    return remainAmount

}