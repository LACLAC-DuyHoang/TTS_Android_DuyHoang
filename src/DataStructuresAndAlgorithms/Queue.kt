package DataStructuresAndAlgorithms

import java.util.LinkedList
import java.util.Queue

/*
*   Queue (Hàng đợi): hoạt động theo nguyên tắc FIFO (First In, First Out) -> Vào trước → Ra trước
*   VD: xếp hàng mua vé thì người đến  trước được phục vụ trước
*    + Các thao tác phổ biến:
*       - push(): thêm phần tử. Đưa phần tử lên đỉnh Stack.
*       - pop(): lấy phần tử trên cùng
*       - peek(): xem phần tử trên cùng, chỉ xem không lấy ra
*       - isEmpty(): kiểm tra rỗng, true->false
*
* */
fun main() {

    val queue: Queue<Int> = LinkedList()
    queue.offer(10)
    queue.offer(20)
    queue.offer(30)
    println(queue)
    //lấy phần tử trên cùng
    println(queue.poll())
    println(queue)
    // xem phần tử trên cùng, chỉ xem không lấy ra
    println(queue.peek())
    println(queue)


}