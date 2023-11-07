package lotto.model

import lotto.model.seller.Money
import lotto.model.seller.toMoney
import lotto.requireAndReturn
import lotto.toValidInt
import lotto.validPositiveNumber

class Payment private constructor(lottoPrice: Money, val cost: Money) {

    val purchase: Int = cost.value / lottoPrice.value

    companion object {
        private const val INVALID_MONEY_ERROR = "1000원 단위의 금액만 입력할 수 있습니다."

        fun from(input: String, lottoPrice: Int): Payment {
            val price = lottoPrice.toMoney()
            val cost = input.toValidInt()
                .validPositiveNumber()
                .validLottoPrice(price)
                .toMoney()
            return Payment(price, cost)
        }

        private fun Int.validLottoPrice(price: Money): Int =
            requireAndReturn(this % price.value == 0, INVALID_MONEY_ERROR)
    }
}