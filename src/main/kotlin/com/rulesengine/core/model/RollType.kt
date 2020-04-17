package com.rulesengine.core.model

import java.lang.RuntimeException
import kotlin.random.Random

enum class RollType(val max: Int) {
    D2(2), D3(3), D6(6), D6x2(12), D6x3(18), D6x4(24);

    enum class ReRoll {
        No, One, All
    }

    private fun roll(): Int {
        return Random.nextInt(1, this.max + 1)
    }

    fun roll(times: Int, success: Int, reRoll: ReRoll = ReRoll.No): Int {
        return complexRoll(times, success, reRoll).success
    }

    data class ComplexCondition(var success: Int, var criticalSuccess: Int, var criticalFail: Int)

    fun complexRoll(times: Int,
                    success: Int,
                    reRoll: ReRoll,
                    criticalSuccess: (Int) -> Boolean = { false },
                    criticalFail: (Int) -> Boolean = { false })
            : ComplexCondition {
        if (success == 1 || success !in 1..this.max) {
            throw RuntimeException("Warhammer does not allow this case")
        }

        val results = Array(times) { roll() }
        val superSuccessCount = results.asSequence().filter { criticalSuccess.invoke(it) }.count()
        val successCount = results.asSequence().filter { it >= success }.count() - superSuccessCount
        val complexCondition = ComplexCondition(successCount, superSuccessCount, 0)
        when (reRoll) {
            ReRoll.All -> {
                reRolling(results, complexCondition, criticalSuccess, criticalFail, { it < success }, { it < success }, times)
            }
            ReRoll.One -> {
                reRolling(results, complexCondition, criticalSuccess, criticalFail, { it < success }, { it == 1 }, times)
            }
            else -> {
                complexCondition.criticalFail = results.filter { criticalFail.invoke(it) }.count()
            }
        }
        return complexCondition
    }

    private fun reRolling(results: Array<Int>,
                          complexCondition: ComplexCondition,
                          criticalSuccess: (Int) -> Boolean,
                          criticalFail: (Int) -> Boolean,
                          fail: (Int) -> Boolean,
                          reRollCondition: (Int) -> Boolean,
                          times: Int) {
        var fails = results.asSequence().filter { fail.invoke(it) }.count()
        val reRolling = results.asSequence().filter { reRollCondition.invoke(it) }.count()
        val resultsReRolled = Array(reRolling) { roll() }
        complexCondition.criticalSuccess += resultsReRolled.asSequence().filter { criticalSuccess.invoke(it) }.count()
        complexCondition.criticalFail = resultsReRolled.asSequence().filter { criticalFail.invoke(it) }.count()
        fails = fails - reRolling + resultsReRolled.asSequence().filter { fail.invoke(it) }.count() - complexCondition.criticalFail
        complexCondition.success = times - fails - complexCondition.criticalSuccess - complexCondition.criticalFail
    }
}
