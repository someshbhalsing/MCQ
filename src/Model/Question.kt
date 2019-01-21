package Model

class Question(
    private val mQid: Int,
    val mQuestion: String,
    var mOptions: List<Answer>,
    var mMarkedAnswer: Answer?
) {

    var mStatus: Int

    init {
        this.mStatus = NOT_ANSWERED
        this.mOptions = randomizeAnswers()
    }

    companion object {
        const val NOT_ANSWERED: Int = 124
        const val ANSWERED: Int = 100
    }

    private fun randomizeAnswers(): List<Answer> {
        return mOptions.shuffled()
    }


}