package Model

class Question(
    private val mQid: Int,
    val mQuestion: String,
    val mOptions: List<Answer>,
    var mMarkedAnswer: Answer?
) {

    private var mStatus: Int

    init {
        this.mStatus = NOT_ANSWERED
    }

    companion object {
        private
        val NOT_ANSWERED = 124
        val ANSWERED = 100
    }

    fun randomizeAnswers(): List<Answer> {
        return mOptions.shuffled()
    }


}