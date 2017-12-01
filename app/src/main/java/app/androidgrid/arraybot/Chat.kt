package app.androidgrid.arraybot

/**
 * Created by ankit on 17/11/17.
 */

class Chat {
    var chatId: String = ""
    var chatMessage: String = ""
    var chatTime: String = ""
    var isMe: Boolean = false

    constructor() {}

    constructor(chatId: String, chatMessage: String, chatTime: String, isMe: Boolean) {
        this.chatId = chatId
        this.chatMessage = chatMessage
        this.chatTime = chatTime
        this.isMe = isMe
    }
}
