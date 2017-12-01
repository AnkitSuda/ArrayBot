package app.androidgrid.arraybot

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import app.androidgrid.arraybot.R
import app.androidgrid.arraybot.ChatSuggestion
import app.androidgrid.arraybot.MainActivity
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by ankit on 17/11/17.
 */

class ChatSuggestionAdapter(private val list: List<ChatSuggestion>, private val mainActivity: MainActivity) : RecyclerView.Adapter<ChatSuggestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_chat_suggestion_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestion = list[position]

        holder.textView!!.text = suggestion.sugMsg

        holder.textView!!.setOnClickListener { MainActivity.ChatUtils.sendSeg(mainActivity, suggestion.sugMsg) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView: TextView? = null

        init {
            textView = itemView.findViewById(R.id.s_chat_s_text)
        }
    }
}
