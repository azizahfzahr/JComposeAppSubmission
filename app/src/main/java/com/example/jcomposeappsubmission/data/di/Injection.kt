package com.example.jcomposeappsubmission.data.di

import com.example.jcomposeappsubmission.data.repository.KulinerRepository

object Injection {
    fun provideRepository(): KulinerRepository{
        return KulinerRepository.getInstance()
    }
}