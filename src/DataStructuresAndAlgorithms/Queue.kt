package DataStructuresAndAlgorithms

import java.util.LinkedList
import java.util.Queue

/*
*   Queue (Hàng đợi): hoạt động theo nguyên tắc FIFO (First In, First Out) -> Vào trước → Ra trước
*   VD: xếp hàng mua vé thì người đến  trước được phục vụ trước
*   - các thao tác của Queue
*       + offer() : Thêm vào cuối Queue
*       + poll(): lấy ở đầu
*       + peek(): xem đầu hàng
*       + isEmpty(): Kiểm tra rỗng
* */
fun main() {
    //khởi tạo
    val queue: Queue<Int> = LinkedList()
    //thêm lần lượt vào cuối Queue
    queue.offer(10)
    queue.offer(20)
    queue.offer(30)
    println(queue)
    //poll()
    println(queue.poll()) //lấy giá trị đầu tiên trong queue
    println(queue)
    //xem đầu hàng, chỉ xem chứ không lấy
    println(queue.peek())
    println(queue)
}