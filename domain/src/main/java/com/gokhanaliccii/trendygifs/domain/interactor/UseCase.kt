package com.gokhanaliccii.trendygifs.domain.interactor

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by gokhan.alici on 24.02.2019
 */
sealed class UseCase {

    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun addDisposable(d: Disposable) = disposable.add(d)

    fun dispose() {
        disposable.dispose()
    }

    abstract class Single<I, O> : UseCase() {

        abstract fun build(input: I?): io.reactivex.Single<O>

        fun execute(input: I?, observer: DisposableSingleObserver<O>) {
            addDisposable(build(input).subscribeWith(observer))
        }
    }

    abstract class Completable<I> : UseCase() {

        abstract fun build(input: I?): io.reactivex.Completable

        fun execute(input: I?, observer: DisposableCompletableObserver) {
            addDisposable(build(input).subscribeWith(observer))
        }
    }

    object None
}