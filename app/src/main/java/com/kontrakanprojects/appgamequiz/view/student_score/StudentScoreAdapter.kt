package com.kontrakanprojects.appgamequiz.view.student_score

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kontrakanprojects.appgamequiz.data.dto.StudentWithScore
import com.kontrakanprojects.appgamequiz.databinding.ItemListStudentScoreBinding

class StudentScoreAdapter : RecyclerView.Adapter<StudentScoreAdapter.ViewHolder>() {
    private var listStudentScore= ArrayList<StudentWithScore>()

    private val TAG = StudentScoreAdapter::class.simpleName

    private var onItemClickCallBack: OnItemClickCallBack? = null

    interface OnItemClickCallBack {
        fun onItemClicked(StudentWithScore: StudentWithScore)
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    fun setData(data: List<StudentWithScore>?) {
        if (data == null) return
        listStudentScore.clear()
        listStudentScore.addAll(data)
        notifyDataSetChanged()

        Log.d(TAG, "setData: $listStudentScore")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListStudentScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStudentScore[position])
    }

    override fun getItemCount() = listStudentScore.size

    inner class ViewHolder(private val binding: ItemListStudentScoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: StudentWithScore) {
            with(binding) {
                tvStudentName.text = data.nama
                if(data.studentScore == null){
                    tvResultScore.text = "-"
                }else{
                    tvResultScore.text = (data.studentScore?.nilai ?: 0).toString() +"/100"
                }

            }
            itemView.setOnClickListener { onItemClickCallBack?.onItemClicked(data) }
        }

    }

}