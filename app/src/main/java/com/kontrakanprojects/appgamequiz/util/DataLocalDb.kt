package com.kontrakanprojects.appgamequiz.util

import android.content.res.Resources
import android.graphics.BitmapFactory
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.entity.QuestionEntity
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question

object DataLocalDb{

    fun insertQuestions() {
        val question = QuestionEntity(
            1,
            "Temasuk bagian tumbuhan dan terletak dibawah tanah",
            3
        )
        val question2 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question3 = QuestionEntity(
            3,
            "Sekelompok akar yang tebal dan panjang. Contoh tumbuhannya adalah mangga dan rambutan.",
            4
        )
        val question4 = QuestionEntity(
            4,
            "Bagian tumbuhan yang fungsinya menyangga tumbuhan dan tempat tumbuhnya daun, buah dan bunga",
            1
        )
        val question5 = QuestionEntity(
            5,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question6 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question7 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question8 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question9 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question10 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question11 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question12 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question13 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question14 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question15 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question16 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question17 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question18 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )
        val question19 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )

        val question20 = QuestionEntity(
            2,
            "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
            1
        )

    }

    fun insertOptions(){

    }

    fun getArrQuestions(res: Resources): ArrayList<Question>{
        var listQuestions = ArrayList<Question>(
            listOf(
                Question(
                    text = "Temasuk bagian tumbuhan dan terletak dibawah tanah",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.ufo),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_serabut),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),3),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),4)
                        )
                    )
                ),
                Question(
                    text = "Sekelompok akar yang panjang dan halus. Contoh tumbuhannya adalah rumput dan padi",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_tunggang),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_serabut),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),3),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),4)
                        )
                    )
                ),Question(
                    text = "Sekelompok akar yang tebal dan panjang. Contoh tumbuhannya adalah mangga dan rambutan",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_tunggang),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_serabut),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.penguin),3),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),4)
                        )
                    )
                ),Question(
                    text = "Bagian tumbuhan yang fungsinya menyangga tumbuhan dan tempat tumbuhnya daun, buah dan bunga",
                    answerKey = 3,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_tunggang),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_serabut),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),3)
                        )
                    )
                ),
                Question(
                    text = "Bagian tumbuhan yang fungsinya menyangga tumbuhan dan tempat tumbuhnya daun, buah dan bunga",
                    answerKey = 3,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_tunggang),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_serabut),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),3)
                        )
                    )
                ),
                Question( //5
                    text = "Contoh tumbuhan yang menyimpan cadangan makanan di bagian batang",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kelapa_sawit),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_tanaman_q5),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.pohon_jati_mahoni),3)
                        )
                    )
                )
                ,Question(
                    text = "Hewan yang hanya mampu hidup di air,memunyai sisik, dan memunyai Sirip ekor",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.ikan),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.gajah),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.komodo),3)
                        )
                    )
                ),
                Question(
                    text = "Hewan Liar yang hidup di kutub",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_kutub),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_langka),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_ternak),3)
                        )
                    )
                ),
                Question(
                    text = "Hewan yang hidup di darat, dijadikan sebagai hewan peliharaan",
                    answerKey = 3,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_kutub),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_langka),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_pet),3)
                        )
                    )
                ),
                Question(
                    text = "Hewan yang dapat dijadikan sebagai hewan ternak",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_kutub),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_ternak),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_pet),3)
                        )
                    )
                ),
                Question( //10
                    text = "Hewan yang hidup di gurun pasir, mempunyai punuk, kaki yang panjang, bulu mata panjang, dan telapak kaki lebar",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.penguin),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.unta),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.gajah),3)
                        )
                    )
                ),
                Question(
                    text = "Hewan yang hidup di dua alam",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_2alam),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_ternak),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_pet),3)
                        )
                    )
                ),
                Question(
                    text = "Termasuk hewan unggas, memiliki bulu berlapis minyak, memunyai selaut kaki, serta memiliki paruh",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.bebek),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.unta),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.gajah),3)
                        )
                    )
                ),
                Question(
                    text = "Hewan-hewan yang hampir punah di Indonesia",
                    answerKey = 3,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_2alam),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_ternak),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_hewan_langka),3)
                        )
                    )
                ),
                Question(
                    text = "Tumbuhan yang dimanfaatkan untuk diambil kayu yang digunakan untuk perabot",
                    answerKey = 3,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.gajah),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.penguin),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.pohon_jati_mahoni),3)
                        )
                    )
                ),
                Question( //15
                    text = "Tumbuhan yang digunakan sebagai bahan dasar pembuat minyak goreng, margarin atau minyak cat ",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kelapa_sawit),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kumpulan_tanaman_q5),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.pohon_jati_mahoni),3)
                        )
                    )
                ),
                Question(
                    text = "Tanaman yang di rawat dengan baik",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.tanaman_layu),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.pohon_subur),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.ufo),3)
                        )
                    )
                ),
                Question(
                    text = "Bagian Tubuh hewan yang melindungi dari serangan cuaca adalah",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.hidung_kucing),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.bulu_kucing),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.kaki_kucing),3)
                        )
                    )
                ),
                Question(
                    text = "Tanaman kentang menyimpang cadangan makanan di ...",
                    answerKey = 3,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_tunggang),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.akar_serabut),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),3)
                        )
                    )
                ),
                Question(
                    text = "Hewan yang mampu beradaptasi dalam kondisi dingin",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.penguin),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.gajah),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.komodo),3)
                        )
                    )
                ),

            )
        )
        listQuestions.add(
            Question(
                text = "Organ Reproduksi pada tumbuhan",
                answerKey = 1,
                options = ArrayList<Option>(
                    listOf(
                        Option(BitmapFactory.decodeResource(res, R.drawable.bunga_mawar),1),
                        Option(BitmapFactory.decodeResource(res, R.drawable.gajah),2),
                        Option(BitmapFactory.decodeResource(res, R.drawable.batang_pohon),3)
                    )
                )
            )
        )
        return listQuestions
    }

}