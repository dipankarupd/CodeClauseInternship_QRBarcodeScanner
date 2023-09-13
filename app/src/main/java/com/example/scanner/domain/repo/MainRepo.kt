package com.example.scanner.domain.repo

import kotlinx.coroutines.flow.Flow

interface MainRepo {

    fun startScan(): Flow<String?>
}