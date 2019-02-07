package com.ezmall.ui.custom

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ezmall.R
import java.util.ArrayList

class TimeLineAdapter(private val context: Context, private val rowDataList: ArrayList<TimelineRow>) :
    RecyclerView.Adapter<TimeLineAdapter.VH>() {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(rowDataList, position, rowDataList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_order_detail_status, parent, false)
        return VH(itemView, context)
    }

    override fun getItemCount(): Int {
        return rowDataList.size
    }

    inner class VH(itemView: View, mContext: Context) : RecyclerView.ViewHolder(itemView) {

        private val rowTitle = itemView.findViewById(R.id.crowTitle) as TextView
        private val rowDescription = itemView.findViewById(R.id.crowDesc) as TextView
        private val rowImage = itemView.findViewById(R.id.crowImg) as ImageView
        private val rowUpperLine = itemView.findViewById(R.id.crowUpperLine) as View
        private val rowLowerLine = itemView.findViewById(R.id.crowLowerLine) as View

        val scale = mContext.resources.displayMetrics.density

        fun bind(timelineRow: List<TimelineRow>, position: Int, timelineRow1: TimelineRow) {

            //Log.e("TAG BIND", size.toString())

            if (!(position != 0 || position != timelineRow.size - 1)) {

                rowUpperLine.visibility = View.INVISIBLE
                rowLowerLine.visibility = View.INVISIBLE

            } else if (position == 0) {
                val pixels = (timelineRow1.bellowLineSize * scale + 0.5f)

                rowUpperLine.visibility = View.INVISIBLE
                rowLowerLine.setBackgroundColor(timelineRow1.bellowLineColor)
                rowLowerLine.layoutParams.width = pixels.toInt()

            } else if (position == timelineRow.size - 1) {

                val pixels = (timelineRow[position - 1].bellowLineSize * scale + 0.5f)
                rowLowerLine.visibility = View.INVISIBLE
                rowUpperLine.setBackgroundColor(timelineRow[position - 1].bellowLineColor)
                rowUpperLine.layoutParams.width = pixels.toInt()

            } else {

                val pixels = (timelineRow1.bellowLineSize * scale + 0.5f)
                val pixels2 = (timelineRow[position - 1].bellowLineSize * scale + 0.5f)

                rowLowerLine.setBackgroundColor(timelineRow1.bellowLineColor)
                rowUpperLine.setBackgroundColor(timelineRow[position - 1].bellowLineColor)
                rowLowerLine.layoutParams.width = pixels.toInt()
                rowUpperLine.layoutParams.width = pixels2.toInt()

            }



            if (timelineRow1.title == null)
                rowTitle.visibility = View.GONE
            else {
                rowTitle.text = timelineRow1.title
                if (timelineRow1.titleColor != 0)
                    rowTitle.setTextColor(timelineRow1.titleColor)
            }

            if (timelineRow1.description == null)
                rowDescription.visibility = View.GONE
            else {
                rowDescription.text = timelineRow1.description
                if (timelineRow1.descriptionColor != 0)
                    rowDescription.setTextColor(timelineRow1.descriptionColor)
            }

            if (timelineRow1.image != null) {
                rowImage.setImageBitmap(timelineRow1.image)
            }

            val pixels = (timelineRow1.imageSize * scale + 0.5f)
            rowImage.layoutParams.width = pixels.toInt()
            rowImage.layoutParams.height = pixels.toInt()

            val backgroundView = itemView.findViewById(R.id.crowBackground) as View
            if (timelineRow1.backgroundColor == 0)
                backgroundView.background = null
            else {
                if (timelineRow1.backgroundSize == -1) {
                    backgroundView.layoutParams.width = pixels.toInt()
                    backgroundView.layoutParams.height = pixels.toInt()
                } else {
                    val backgroundPixels = (timelineRow1.backgroundSize * scale + 0.5f)
                    backgroundView.layoutParams.width = backgroundPixels.toInt()
                    backgroundView.layoutParams.height = backgroundPixels.toInt()
                }
                val background = backgroundView.background as GradientDrawable
                background.setColor(timelineRow1.backgroundColor)
            }


            val marginParams = rowImage.layoutParams as ViewGroup.MarginLayoutParams
            marginParams.setMargins(0, pixels.toInt() / 2 * -1, 0, pixels.toInt() / 2 * -1)

        }


    }
}