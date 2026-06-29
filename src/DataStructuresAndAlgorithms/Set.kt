package DataStructuresAndAlgorithms

/*
*    Set: là tập hợp các phần tử không trùng nhau
*    - Có 2 loại set:
*       + setOf(): không sửa
*       + mutableSetOf(): có thể sửa
* */
fun main(){

    val numbers = setOf(1,2,3,2,1)
    println(numbers) // kết quả 1,2,3

    // mutableSetOf()
    val numbers2 = mutableSetOf(1,2)
    numbers2.add(3)
    numbers2.remove(1)
    numbers.contains(2) // kiểm tra xem một phần tử có tồn tại trong Collection hay không -> trả về true false
    numbers.size // lấy số lượng phần tử có trong Collection hoặc array
    for(item in numbers){
        println(item)
    }
}