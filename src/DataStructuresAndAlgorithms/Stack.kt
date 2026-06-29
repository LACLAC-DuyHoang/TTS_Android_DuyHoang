package DataStructuresAndAlgorithms

import java.util.Stack

//Stack và Queue dùng để lưu trữ nhiều phần tử. Tuy nhiên, cách thêm và lấy dữ liệu của chúng hoàn toàn khác nhau

/*
*       + Stack (ngăn xếp) : là cấu trúc dữ liệu hoạt động theo nguyên tắc:
*           LIFO (Last In, First Out) -> nghĩa là: Vào sau → Ra trước
*           VD: Bạn rửa bát. Đặt đĩa 1, Đặt đĩa 2, Đặt đĩa 3
*               Khi lấy ra: lấy đĩa 3, lấy đĩa 2, lấy đĩa 1
*       + Các thao tác phổ biến:
*           - push(): thêm phần tử. Đưa phần tử lên đỉnh Stack.
*           - pop(): lấy phần tử trên cùng
*           - peek(): xem phần tử trên cùng, chỉ xem không lấy ra
*           - isEmpty(): kiểm tra rỗng, true->false
* */
fun main(){
    // khởi tạo
    val stack = Stack<Int>()
    //push()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    println(stack)

    //pop()
    println(stack.pop())//lấy phần tử trên cùng ra khỏi stack
    println(stack)
    //peek()
    println(stack.peek())//xem phần tử trên cùng, chỉ xem chứ không lấy ra khỏi stack
    println(stack)
    
}
