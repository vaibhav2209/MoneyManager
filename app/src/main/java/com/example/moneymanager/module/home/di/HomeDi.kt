package com.example.moneymanager.module.home.di

import com.example.moneymanager.module.home.data.remote.FirestoreService
import com.example.moneymanager.module.home.data.repository.ITransactionRepositoryImpl
import com.example.moneymanager.module.home.domain.repository.ITransactionRepository
import com.example.moneymanager.module.home.domain.use_cases.AddTransactionUseCase
import com.example.moneymanager.module.home.domain.use_cases.RecentTransactionUseCase
import com.example.moneymanager.module.home.domain.use_cases.MonthlyTransactionUseCase
import com.example.moneymanager.module.home.domain.use_cases.TransactionUseCases
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeDi {

    @Provides
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirestoreService(db : FirebaseFirestore) : FirestoreService = FirestoreService(db)

    @Provides
    fun provideTransactionRepo(firestoreService: FirestoreService) : ITransactionRepository =
        ITransactionRepositoryImpl(firestoreService) as ITransactionRepository

    @Provides
    fun provideTransactionUseCase(repo: ITransactionRepository) : TransactionUseCases =
        TransactionUseCases(
            ucRecentTransactionUseCase = RecentTransactionUseCase(repo),
            ucAddTransactionUseCase = AddTransactionUseCase(repo),
            ucMonthlyTransactionUseCase = MonthlyTransactionUseCase(repo)
        )
}