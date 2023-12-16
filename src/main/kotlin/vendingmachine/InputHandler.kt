package vendingmachine

import camp.nextstep.edu.missionutils.Console.readLine

class InputHandler {

    private fun checkVendingSum(): Int{
        val vendingSum = readLine().toInt()
        require(vendingSum > 0) { "[ERROR] 유효하지 않은 금액입니다. 다시 입력해 주세요." }
        return vendingSum
    }

    fun readVendingSum(): Int{

        while (true){
            println("자판기가 보유하고 있는 금액을 입력해 주세요.")
            try {
                return checkVendingSum()
            } catch (e: NumberFormatException) {
                println("[ERROR] 유효하지 않은 금액입니다. 다시 입력해 주세요.")
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }


}