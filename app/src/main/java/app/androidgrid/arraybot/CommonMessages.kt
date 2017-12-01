package app.androidgrid.app.chat_bot

import android.support.v7.app.AppCompatActivity
import java.util.regex.Pattern

/**
 * Created by ankit on 17/11/17.
 */

object CommonMessages {

    val messages = arrayOf("hello", "hi", "how are you", "what's going on", "who are you", "hey", "i want to chat with ankit")

    val replies = arrayOf("Hi", "Hello", "I'm fine", "Everything is going well", "I'm a Bot", "Hey Buddy!", "He will reply to your messages as soon as read your messages")

    fun getReply(msg: String): String {
        var fMsg = msg.toLowerCase().trim()

        for (i in messages.indices) {
            //if (fMsg.matches(messages[i].toRegex())) {
                var p = Pattern.compile(messages[i])
                var m = p.matcher(fMsg)
            if (m.find()) {
                return replies[i]
            }
            //}
        }

        return "null"
    }

}
