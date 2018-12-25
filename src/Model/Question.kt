package Model

class Question(private val mQid: Int, val mQuestion: String, val mOptions: List<Answer>) {

    private var mStatus: Int
    private lateinit var mMarkedAnswer: Answer

    init {
        this.mStatus = NOT_ANSWERED
    }

    companion object {
        private
        val NOT_ANSWERED = 124
        val ANSWERED = 100
    }


}