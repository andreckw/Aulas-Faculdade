fun main() {
    val valor = 7
    if (parOuImpar(valor)) {
        println("Morra a todos $valor")
    } else {
        println("Nao morra a todos $valor")
    }

    criarPote()
    criarPote(tipo = "Uva")
    criarPote(quantidade = 15)
    criarPote(tipo = "Uva", quantidade = 15)

    exemploLambda()
    exemploLista()
    exemploMapa()
}

fun parOuImpar(valor: Int): Boolean {
    return valor % 2 == 0
}

fun criarPote(tipo: String = "Morango", quantidade: Int = 20) {

    println("$quantidade Pote de $tipo")
}

fun exemploLambda() {
    val dobro: (Int) -> Int = { valor -> valor * 2 }
    val quadrado: (Int) -> Int = { it * it }
    val mensagem: () -> String = { "Buceta" }

    println("Dobro pika: ${dobro(5)}")
    println("Quadrado pika: ${quadrado(5)}")
    println("Mensagem pika: ${mensagem()}")
}

fun exemploLista() {

    val nomes: List<String> = listOf("A", "B", "C")
    val frutas: MutableList<String> = mutableListOf("A", "Z")

    frutas.add("B")
    frutas.remove("Z")

    println(frutas.size)
    for (nome in nomes) {
        println(nome)
    }
    frutas.forEach { println(it) }
}


fun exemploMapa() {
    
    val capitais: Map<String, String> = mapOf(
        "BR" to "Brasilia",
        "PT" to "Lisboa"
    )

    val telefones: MutableMap<String, String> = mutableMapOf(
        "Alice" to "2222-2222"
    )

    println(capitais["BR"])
    println(telefones.getOrDefault("Bob", "Não encontrado"))

}