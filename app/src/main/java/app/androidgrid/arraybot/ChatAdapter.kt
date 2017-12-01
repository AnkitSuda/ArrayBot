package app.androidgrid.arraybot

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

import org.w3c.dom.Text

import app.androidgrid.arraybot.R
import app.androidgrid.arraybot.Chat
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by ankit on 17/11/17.
 */

class ChatAdapter(private val list: List<Chat>, private val context: Context, private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var FadeIn: Animation? = null
    private var FadeOut: Animation? = null

    internal var isDate = false
    internal var isDate2 = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val me = LayoutInflater.from(parent.context).inflate(R.layout.single_chat_b_1, parent, false)
        val other = LayoutInflater.from(parent.context).inflate(R.layout.single_chat_b_2, parent, false)
        when (viewType) {
            0 -> return MeViewHolder(me)
            2 -> return OtherViewHolder(other)
        }
        return OtherViewHolder(other)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = list[position]

        FadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in_chat_date)
        FadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out_chat_date)

        when (holder.itemViewType) {
            0 -> {

                val meViewHolder = holder as MeViewHolder

                if (meViewHolder.textDate!!.visibility == View.VISIBLE) {
                    isDate = true
                } else {
                    isDate = false
                }

                meViewHolder.textView!!.text = chat.chatMessage
                meViewHolder.textDate!!.text = chat.chatTime

                meViewHolder.textView!!.setOnClickListener {
                    if (meViewHolder.textDate!!.visibility == View.VISIBLE) {
                        meViewHolder.textDate!!.startAnimation(FadeOut)
                        meViewHolder.textDate!!.visibility = View.GONE
                        meViewHolder.textView!!.setBackgroundResource(R.drawable.chat_b_1)
                        isDate = false
                    } else {
                        meViewHolder.textDate!!.startAnimation(FadeIn)
                        meViewHolder.textDate!!.visibility = View.VISIBLE
                        meViewHolder.textView!!.setBackgroundResource(R.drawable.chat_b_1_c)
                        isDate = true
                    }
                }
            }
            2 -> {
                val otherViewHolder = holder as OtherViewHolder

                if (otherViewHolder.textDate!!.visibility == View.VISIBLE) {
                    isDate = true
                } else {
                    isDate = false
                }

                otherViewHolder.textView!!.text = chat.chatMessage
                otherViewHolder.textDate!!.text = chat.chatTime

                otherViewHolder.textView!!.setOnClickListener {
                    if (otherViewHolder.textDate!!.visibility == View.VISIBLE) {
                        otherViewHolder.textDate!!.startAnimation(FadeOut)
                        otherViewHolder.textDate!!.visibility = View.GONE
                        otherViewHolder.textView!!.setBackgroundResource(R.drawable.chat_b_2)
                        isDate2 = false
                    } else {
                        otherViewHolder.textDate!!.startAnimation(FadeIn)
                        otherViewHolder.textDate!!.visibility = View.VISIBLE
                        otherViewHolder.textView!!.setBackgroundResource(R.drawable.chat_b_2_c)
                        isDate2 = true
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        val chat = list[position]

        return if (chat.isMe) {
            0
        } else {
            2
        }
        //return position % 2 * 2;
    }

    inner class MeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView: TextView? = null
        internal var textDate: TextView? = null

        init {
            textView = itemView.findViewById(R.id.s_chat_b_1_text)
            textDate = itemView.findViewById(R.id.s_chat_b_1_date)
        }
    }

    inner class OtherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView: TextView? = null
        internal var textDate: TextView? = null

        init {
            textView = itemView.findViewById(R.id.s_chat_b_2_text)
            textDate = itemView.findViewById(R.id.s_chat_b_2_date)
        }
    }
}
