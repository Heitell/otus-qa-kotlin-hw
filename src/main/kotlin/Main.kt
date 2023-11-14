fun main(args: Array<String>) {
    TestRunnerImpl().runTest(Steps()) { Test().runTest() }
}

interface TestRunner { fun <T> runTest(steps: T, test: () -> Unit) }

class TestRunnerImpl : TestRunner {

    override fun <T> runTest(steps: T, test: () -> Unit) {
        steps?.let {
            it::class.java.methods.forEach { method ->
                if (method.name.contains("before")) {
                    println("Starting method: ${method.name}")
                    method.invoke(it)
                }
            }

            println("Starting Test")
            test()

            it::class.java.methods.forEach { method ->
                if (method.name.contains("after")) {
                    println("Starting method: ${method.name}")
                    method.invoke(it)
                }
            }
        }
    }

}

class Test() {
    fun runTest() {
    }
}

class Steps() {
    fun before() {
    }

    fun beforeAll() {
    }

    fun afterAll() {
    }

    fun after123() {
    }
}