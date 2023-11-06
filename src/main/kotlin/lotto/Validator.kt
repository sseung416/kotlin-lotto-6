package lotto

fun <T> T.requireAndReturn(value: Boolean, message: String, containsErrorMessage: Boolean = true): T {
    require(value) { if (containsErrorMessage) message.toErrorMessage() else message }
    return this
}

fun String.toValidInt(): Int = requireAndReturn(this.isInt(), "문자는 입력할 수 없습니다.").toInt()

fun Int.validPositiveNumber(): Int = requireAndReturn(this.isPositiveNumber(), "음수나 0 값은 입력할 수 없습니다.")

fun String.isInt(containsSign: Boolean = false): Boolean {
    if (isBlank()) return false
    if (!containsSign && this[0] in listOf('-', '+')) return false
    return toIntOrNull() != null
}

fun Int.isPositiveNumber(): Boolean = this > 0

fun Int.isInRange(start: Int, end: Int): Boolean = this in start..end

private fun String.toErrorMessage(): String = "[ERROR] $this"
