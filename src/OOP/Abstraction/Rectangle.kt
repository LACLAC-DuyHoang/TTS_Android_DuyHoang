package OOP.Abstraction

class Rectangle (
    private var width: Double,
    private var height: Double
): Shape() {
    override fun Area(): Double {
        return width * height
    }
}