package View

import Model.Question
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.awt.Color
import java.awt.Font
import javax.swing.*

class QuestionPanel(private val mQuestionList: List<Question>) : JPanel() {
    private val mQuestionLabel: JLabel
    private val opt1: JRadioButton
    private val opt2: JRadioButton
    private val opt3: JRadioButton
    private val opt4: JRadioButton
    private var currentQuestion: Int

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        background = Color.white
        val grp = ButtonGroup()
        opt1 = JRadioButton()
        opt2 = JRadioButton()
        opt3 = JRadioButton()
        opt4 = JRadioButton()
        opt1.background = Color.white
        opt2.background = Color.white
        opt3.background = Color.white
        opt4.background = Color.white
        opt1.font = Font("Calibri", Font.PLAIN, 18)
        opt2.font = Font("Calibri", Font.PLAIN, 18)
        opt3.font = Font("Calibri", Font.PLAIN, 18)
        opt4.font = Font("Calibri", Font.PLAIN, 18)
        grp.add(opt1)
        grp.add(opt2)
        grp.add(opt3)
        grp.add(opt4)

        currentQuestion = 0

        mQuestionLabel = JLabel()
        mQuestionLabel.font = Font("Serif", Font.BOLD, 28)
        mQuestionLabel.alignmentX = 0.1F
        add(mQuestionLabel)
        add(opt1)
        add(opt2)
        add(opt3)
        add(opt4)

        seek(currentQuestion)

    }

    private fun display(question: Question) {
        mQuestionLabel.text = "Q " + (currentQuestion + 1) + ".  " + question.mQuestion
        val answerList = question.randomizeAnswers()
        opt1.text = answerList[0].mOption
        opt2.text = answerList[1].mOption
        opt3.text = answerList[2].mOption
        opt4.text = answerList[3].mOption
        if (question.mMarkedAnswer == null) {
            updateUI()
            return
        }
        when (question.mMarkedAnswer) {
            answerList[0] -> opt1.isSelected = true
            answerList[1] -> opt2.isSelected = true
            answerList[2] -> opt3.isSelected = true
            answerList[3] -> opt4.isSelected = true
        }
        updateUI()
    }

    fun seek(i: Int) {
        currentQuestion = i
        display(mQuestionList[i])
    }

    fun update(): Question {
        throw NotImplementedException()
        TODO("INSERT UPDATE METHOD WHICH CAN SAVE THE MARKED ANSWER TO THE EXISTING LIST OF QUESTIONS AND RETURN THE UPDATED QUESTION SO THAT CHANGES CAN BE MADE TO SAVE THE CURRENT INSTANCE")
        return mQuestionList[currentQuestion]
    }

    fun next() {
        if (currentQuestion != mQuestionList.size - 1) {
            currentQuestion += 1
            seek(currentQuestion)
        }
    }

    fun prev() {
        if (currentQuestion != 0) {
            currentQuestion -= 1
            seek(currentQuestion)
        }
    }
}