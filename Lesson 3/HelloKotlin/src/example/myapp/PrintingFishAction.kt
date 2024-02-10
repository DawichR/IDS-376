package example.myapp

class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}