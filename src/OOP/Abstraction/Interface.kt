package OOP.Abstraction

fun main(){
    val list = listOf( //listOf() là hàm tạo một danh sách (List).
        Bird(),
        Plane(),
    )
    for(item in list){
        item.fly()
    }
}

interface Flyable{
    fun fly()
}

class Bird : Flyable{
    override fun fly() {
        println("Bay bằng cánh")
    }
}
class Plane : Flyable{
    override fun fly() {
        println("Bay bằng động cơ")
    }
}