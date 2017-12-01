package app.androidgrid.arraybot

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import app.androidgrid.app.chat_bot.ChatSuggestions
import app.androidgrid.app.chat_bot.CommonMessages
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by ankit on 1/12/17.
 */
class MainActivity : AppCompatActivity() {
    lateinit var list: MutableList<Chat>
    lateinit var sugList: MutableList<ChatSuggestion>
    lateinit var layoutManager: LinearLayoutManager
    lateinit var sugLayoutManager: LinearLayoutManager
    lateinit var adapter: ChatAdapter
    lateinit var sugAdapter: ChatSuggestionAdapter

    lateinit var mList: RecyclerView
    lateinit var mSugList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(contact_toolbar)

        setUpList()
        setUpCSugList()
        setUpSendBtn()
    }

    private fun setUpCSugList() {
        mSugList = contact_sug_list

        sugList = ArrayList()
        sugAdapter = ChatSuggestionAdapter(sugList, this)

        sugLayoutManager = LinearLayoutManager(this)
        sugLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mSugList.setHasFixedSize(true)
        mSugList.layoutManager = sugLayoutManager
        mSugList.adapter = sugAdapter

        getSuggestions()
    }

    private fun setUpSendBtn() {
        contact_send.setOnLongClickListener {
            sendMessage(contact_et.text.toString().trim(), false)
            true
        }
        contact_send.setOnClickListener {
            sendMessage(contact_et.text.toString().trim(), true)
        }
    }

    private fun setUpList() {
        mList = contact_list

        list = ArrayList()
        adapter = ChatAdapter(list, applicationContext, this)

        layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        //layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true

        mList.setHasFixedSize(true)
        mList.itemAnimator = DefaultItemAnimator()
        mList.layoutManager = layoutManager
        mList.adapter = adapter
    }

    private fun sendMessage(msg: String, isMe: Boolean) {
        //var msg = contact_et.text.toString()

        if (isMe) {
            list.add(Chat("as", msg, Date().toString(), true))
            adapter.notifyDataSetChanged()
            mList.smoothScrollToPosition(list.size - 1)

        } else {
            list.add(Chat("as", msg, Date().toString(), false))
            adapter.notifyDataSetChanged()
        }

        var common = CommonMessages.getReply(msg)


        if (common != "null") {
            val handler = Handler()
            handler.postDelayed({
                list.add(Chat("as", common, Date().toString(), false))
                adapter.notifyDataSetChanged()
                mList.smoothScrollToPosition(list.size - 1)
            }, 600)
        } else {
            adapter.notifyDataSetChanged()
            mList.smoothScrollToPosition(list.size - 1)
        }

    }

    private fun getSuggestions() {
        var i = 0
        while (i < ChatSuggestions.suggestions.size) {
            sugList.add(ChatSuggestion(ChatSuggestions.suggestions[i]))

            i++
        }

        sugAdapter.notifyDataSetChanged()
    }

    object ChatUtils {
        fun sendSeg(activity: MainActivity, msg: String) {
            activity.sendMessage(msg, true)
        }
    }

}