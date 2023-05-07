package com.kontrakanprojects.appgamequiz.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kontrakanprojects.appgamequiz.R
import com.kontrakanprojects.appgamequiz.data.model.Option
import com.kontrakanprojects.appgamequiz.data.model.Question

object DataQuiz {
    fun getQuestion(res: Resources): ArrayList<Question>{
        return ArrayList<Question>(
            listOf(
                Question(
                    text = "Rancangan berikut yang sesuai dengan diagram atau peta konsep tentang persamaan dan perbedaan antara kucing dan burung adalah!",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.q1_a),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.q1_b),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.q1_c),3),
                            Option(BitmapFactory.decodeResource(res, R.drawable.q1_d),4),
                        )
                    ),
                    competencyName = getCompetency(3.1)
                ),
                Question(
                    text = "Amati Kedua gambar berikut ini kemudian bandingkan ciri-ciri kedua hewan tersebut!",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.q2_a),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.q2_b),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.q2_c),3),
                            Option(BitmapFactory.decodeResource(res, R.drawable.q2_d),4),
                        )
                    ),
                    competencyName = getCompetency(3.1),
                    images = ArrayList<Bitmap>(
                        listOf(
                            BitmapFactory.decodeResource(res, R.drawable.landak),
                            BitmapFactory.decodeResource(res, R.drawable.cacin)
                        )
                    )
                ),
                Question(
                    text = "Perhatikan gambar diatas. Mengapa daun seledri berubah warna? Hubungkan dengan fungsi batang pada tumbuhan!",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                           Option(image = null,1,"A.\tBatang seledri mengangkut air berwarna sehingga seledri mengikuti warna dari air yang diserap"),
                           Option(image = null,2,"B.\tAkar seledri menyerap air berwarna sehingga seledri mengikuti warna dari air yang diserap"),
                           Option(image = null,3,"C.\tDaun seledri mengangkut air berwarna sehingga seledri mengikuti warna dari air yang diserap"),
                           Option(image = null,4,"D.\tAir berwarna mengalir menuju daun seledri")
                        )
                    ),
                    competencyName = getCompetency(3.1),
                    images = ArrayList<Bitmap>(
                        listOf(
                            BitmapFactory.decodeResource(res, R.drawable.percobaan_seledri)
                        )
                    )
                ),
                Question(
                    text = "Apa yang akan terjadi jika kita melakukan perilaku seperti gambar diatas?    ",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(image = null,1,"A.\tDapat merusak ekosistem laut sehingga banyak ikan yang mati dan membusuk dilaut"),
                            Option(image = null,2,"B.\tMerusak ekosistem daratan di sekitar laut"),
                            Option(image = null,3,"C.\tMempermudah nelayan menagkap ikan"),
                            Option(image = null,4,"D.\tDapat merusak kemampuan nelayan menangkap ikan")
                        )
                    ),
                    competencyName = getCompetency(3.8),
                    images = ArrayList<Bitmap>(
                        listOf(
                            BitmapFactory.decodeResource(res, R.drawable.illegal_fishing)
                        )

                    )
                ),
                //5
                Question(
                    text = "Sering kita jumpai hewan-hewan berkeliaran di lingkungan kita. Bahkan ada yang memilih merawat hewan sebagai hewan peliharaan.contohnya kucing, ikan dan sapi. \n" +
                            "Kucing adalah hewan yang hidup di darat yang tubuhnya ditutupi bulu yang lebat. \n" +
                            "Ikan merupakan hewan yang hidup di air yang dibagian tubuhnya terdapat sisik untuk melindungi tubuhnya. Ikan bisa dijadikan sebagai pangan dalam kehidupan sehari-hari.\n" +
                            "Sapi adalah hewan yang di darat yang memiliki kulit berbulu halus yang dapat dimanfaatkan sebagai bahan dasar pembuat tas\n" +
                            "Dari teks di atas hewan sangat bermanfaat bagi manusia, mengapa hewan memunyai menfaat yang besar bagi manusia? \n",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                            Option(image = null,1,"A.\tHewan dapat dijadikan sebagai sumber makanan bagi hewan lain"),
                            Option(image = null,2,"B.\tHewan dapat dijadikan sebagai teman bermain, sumber makanan, serta bahan dasar kerajinan dari kulit"),
                            Option(image = null,3,"C.\tHewan  dijadikan sebagai ajang pertarungan"),
                            Option(image = null,4,"D.\tHewan dapat membantu penyerbukan pada tumbuhan")
                        )
                    ),
                    competencyName = getCompetency(3.8)
                ),
                Question(
                    text = "Akar tunggang dan akar serabut sangat sering kita jumpai dalam kehidupan sehari-hari. Bagaimana Klasifikasi tumbuhan dibawah ini dilihat dari jenis akarnya?.\n" +
                            "Mangga, Bayam, Matoa, , Kangkung, Manggis, Padi, Rumput, Rambutan\n",
                    answerKey = 3,
                    points = 5,
                    options = ArrayList<Option>(
                        listOf(
                            Option(image = null,1,"A.\tAkar Tunggang \t: Matoa, Bayam, Kangkung, Padi\n" +
                                    "Akar Serabut \t: Mangga, Rumput, Rambutan, Manggis\n"),
                            Option(image = null,2,"B.\tAkar Tunggang \t: Matoa, Kangkung, Mangga, Manggis\n" +
                                    "Akar Serabut \t: Rumput, Rambutan, Bayam, Padi\n"),
                            Option(image = null,3,"C.\tAkar Tunggang \t: Matoa, Rambutan, Mangga, Manggis\n" +
                                    "Akar Serabut \t: Rumput, Bayam, Padi, Kangkung\n"),
                            Option(image = null,4,"D.\tAkar Tunggang \t: Matoa, Kangkung, Mangga, Padi\n" +
                                    "Akar Serabut \t: Rumput, Rambutan, Bayam, Manggis\n"),
                        )
                    ),
                    competencyName = getCompetency(3.8),
                    images = ArrayList<Bitmap>(
                        listOf(
                            BitmapFactory.decodeResource(res, R.drawable.tipe_akar)
                        )
                    )
                ),
                //7
                Question(
                    text = "Perhatikan pernyataan dibawah ini!\n" +
                            "1)\tSampah berserakan di sepanjang jalan\n" +
                            "2)\tSumber air bersih dan jernih\n" +
                            "3)\tBanyak tumbuhan hijau tumbuh dengan subur dan tertata rapi\n" +
                            "4)\tUdara kotor Karena banyak asap dan debu\n" +
                            "5)\tUdara terasa sejuk dan bersih\n" +
                            "6)\tTumbuhan tidak dapat tumbuh dengan subur\n" +
                            "7)\tUdara tercemar\n" +
                            "8)\tTidak ada sampah berserakan\n" +
                            "Klasifikasikan pernyataan diatas yang termasuk lingkungan yang terawatt dengan baik…\n",
                    answerKey = 2,
                    options = ArrayList<Option>(
                        listOf(
                           Option(image = null,1,"A.\t1-3-4-7"),
                           Option(image = null,2,"B.\t2-3-5-8"),
                           Option(image = null,3,"C.\t3-4-6-7"),
                           Option(image = null,4,"D.\t1-3-5-7")
                        )
                    ),
                    competencyName = getCompetency(3.8)
                ),
                Question(
                    text = "Lengkapilah pernyataan berikut dengan memilih jawaban yang benar!\n" +
                            "1)\tManfaat lingkungan sehat adalah terbebas dari………….udara\n" +
                            "2)\tMandi dengan menggunakan air kotor dapat menyebabkan penyakit……\n" +
                            "3)\tDaerah perkotaan lebih…………dari pada daerah pedesaan\n" +
                            "4)\tManusia membutuhkan lingkungan sehat untuk……..\n",
                    answerKey = 1,
                    options = ArrayList<Option>(
                        listOf(
                            Option(image = null,1,"A.\tPolusi – Kulit – gersang - tumbuh sehat  "),
                            Option(image = null,2,"B.\tKulit  – Polusi – gersang - tumbuh sehat  "),
                            Option(image = null,3,"C.\tTumbuh sehat  – Kulit – gersang - Polusi  "),
                            Option(image = null,4,"D.\tGersang – Kulit – Polusi - tumbuh sehat  ")
                        )
                    ),
                    competencyName = getCompetency(3.8)
                ),
                //09
                Question(
                    text = "Dampak pembuangan sampah ke sungai adalah…..",
                    answerKey = 2,
                    points = 5,
                    options = ArrayList<Option>(
                        listOf(
                            Option(image = null,1,"A.\tAliran sungai menjadi lancar"),
                            Option(image = null,2,"B.\tTerjadinya banjir"),
                            Option(image = null,3,"C.\tAir sungai menjadi bersih"),
                            Option(image = null,4,"D.\tIkan menjadi tumbuh subur")
                        )
                    ),
                    competencyName = getCompetency(3.8)
                ),
                ///10
                Question(
                    text = "Berikut ini hewan yang hampir punah di Indonesia, Kecuali",
                    answerKey = 2,
                    points = 5,
                    options = ArrayList<Option>(
                        listOf(
                            Option(BitmapFactory.decodeResource(res, R.drawable.kitten),1),
                            Option(BitmapFactory.decodeResource(res, R.drawable.badak_quiz),2),
                            Option(BitmapFactory.decodeResource(res, R.drawable.ikan_quiz),3),
                            Option(BitmapFactory.decodeResource(res, R.drawable.ayam),4),
                        )
                    ),
                    competencyName = getCompetency(3.8)
                ),
                //11
                Question(
                    text = "Hutan di Indonesia banyak yang gundul. Ini disebabkan oleh penebangan secara liar yang dilakukan oleh masyarakat Indonesia. Para pengusaha kayu menebang hutan untuk dijual kayunya ke luar negeri. Para petani menebang pohon untuk dijadikan ladang pertanian Masyarakat kota menebang pepohonan untuk dijadikan rumah peristirahatan. Akibatnya lereng-lereng dipegunungan menjadi gundul. \n" +
                            "Apa tindakan yang perlu dilakukan agar dapat mencegah bencana yang disebabkan oleh beberapa aktivitas diatas?\n",
                    answerKey = 1,
                    points = 8,
                    options = ArrayList<Option>(
                        listOf(
                            Option(image = null,1,"A.\tMenyerukan penanaman sejuta pohon dihutan dan lereng gunung yang gundul"),
                            Option(image = null,2,"B.\tMelarang pengusaha kayu menebang pohon tetapi tetap memperbolehkan para petani menebang pohon"),
                            Option(image = null,3,"C.\tMenghimbau kepada petani untuk tidak menebang pohon dan diganti dengan membakar lahan yang akan digunakan untuk lading"),
                            Option(image = null,4,"D.\tMenutup perusahaan yang memanfaatkan kayu")
                        )
                    ),
                    competencyName = getCompetency(3.8)
                ),
                //12
                Question(
                    text = "Memancing ikan merupakan salah satu cara pengambilan sumber daya alam yang dilakukan secara bijak pada lingkup hewan tanpa merugikan hewan lain yang hidup disekitarnya. Lalu bagaimana contoh pengambilan sumber daya alam tumbuhan yang baik tanpa merugikan tumbuhan dan makhluk hidup yang hidup disekitarnya?",
                    answerKey = 2,
                    points = 7,
                    options = ArrayList<Option>(
                        listOf(
                            Option(image = null,1,"A.\tMelakukan penebangan secara liar "),
                            Option(image = null,2,"B.\tMelakukan tebang pilih"),
                            Option(image = null,3,"C.\tMelakukan eksploitasi sumber daya alam"),
                            Option(image = null,4,"D.\tMembakar lahan ")
                        )
                    ),
                    competencyName = getCompetency(3.8)
                )
            )
        )
    }

    fun getCompetency(code: Double): String{
        when(code){
            3.1 -> return "3.1\tMenganalisis hubungan antara bentuk dan fungsi bagian tubuh pada hewan dan tumbuhan."
            3.8 -> return "3.8\tMenjelaskan pentingnya upaya keseimbangan dan pelestarian sumber daya alam di lingkungannya."
            4.1 -> return "4.1\tMenyajikan laporan hasil pengamatan tentang bentuk dan fungsi bagian tubuh hewan dan tumbuhan"
            else -> return "4.8\tMelakukan kegiatan upaya pelestarian sumber daya alam bersama orang-orang di lingkungannya."
        }
    }
}