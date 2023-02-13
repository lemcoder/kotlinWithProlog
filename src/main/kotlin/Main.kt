import it.unibo.tuprolog.dsl.solve.prolog
import it.unibo.tuprolog.solve.Solution

fun main() {
    for (sol in nQueens(8))
        if (sol is Solution.Yes)
            println(sol.substitution.values)
}

fun nQueens(n: Int) = prolog {
    staticKb(
        rule {
            "no_attack"(("X1" and "Y1"), ("X2" and "Y2")) `if` (
                    ("X1" `=!=` "X2") and
                            ("Y1" `=!=` "Y2") and
                            (("Y2" - "Y1") `=!=` ("X2" - "X1")) and
                            (("Y2" - "Y1") `=!=` ("X1" - "X2"))
                    )

        },
        fact { "no_attack_all"(`_`, emptyList) },
        rule {
            "no_attack_all"("C", consOf("H", "Hs")) `if` (
                    "no_attack"("C", "H") and
                            "no_attack_all"("C", "Hs")
                    )
        },
        fact { "solution"(`_`, emptyList) },
        rule {
            "solution"("N", consOf(("X" and "Y"), "Cs")) `if` (
                    "solution"("N", "Cs") and
                            between(1, "N", "Y") and
                            "no_attack_all"(("X" and "Y"), "Cs")
                    )
        }
    )
    return@prolog solve("solution"(n, (1..n).map { it and "Y$it" }))
}