package OOP.Encapsulation

fun main(args : Array<String>) {
    //tạo object
    val bankAccount = BankAccount()
    // xem số dư ban đầu
    println("Ban đầu: ${bankAccount.getBalance()}")
    // nạp tiền
    bankAccount.deposit(500)
    // xem số dư sau khi nạp
    println("Sau khi nạp: ${bankAccount.getBalance()}")

}