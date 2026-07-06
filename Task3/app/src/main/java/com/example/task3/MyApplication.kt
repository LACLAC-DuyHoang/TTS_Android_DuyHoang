package com.example.task3

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // hilt nói rằng đây là app của tôi. hãy quản lý dêpndency từ đây

class MyApplication : Application()