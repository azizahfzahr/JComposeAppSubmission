package com.example.jcomposeappsubmission.data.repository

import com.example.jcomposeappsubmission.data.model.FakeDataKuliner
import com.example.jcomposeappsubmission.data.model.Kuliner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class KulinerRepository {
    private val kulinerData = mutableListOf<Kuliner>()

    init {
        if (kulinerData.isEmpty()) {
            FakeDataKuliner.listKuliner.forEach {
                kulinerData.add(
                    Kuliner(
                        id = it.id,
                        name = it.name,
                        img = it.img,
                        desc = it.desc,
                        location = it.location,
                    )
                )
            }
        }
    }

    fun getDetailKulinerById(idKuliner: String): Kuliner {
        return kulinerData.first {
            it.id == idKuliner
        }
    }

    fun getAllListKuliner(): Flow<List<Kuliner>> {
        return flowOf(kulinerData)
    }


    companion object {
        @Volatile
        private var instance: KulinerRepository? = null

        fun getInstance() : KulinerRepository =
            instance ?: synchronized(this){
                KulinerRepository().apply {
                    instance = this
                }
            }
    }
}