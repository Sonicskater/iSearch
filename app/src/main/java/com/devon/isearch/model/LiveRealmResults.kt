package com.devon.isearch.model

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

// source: https://github.com/realm/realm-java/blob/b7b33ceb3e9e6782accd7f608e8956ea646903a0/examples/architectureComponentsExample/src/main/java/io/realm/examples/arch/livemodel/LiveRealmResults.java
// converted to kotlin by AndroidStudio
class LiveRealmResults<T : RealmModel?> @MainThread constructor(results: RealmResults<T>) :
    LiveData<List<T>?>() {
    private val results: RealmResults<T>

    // The listener will notify the observers whenever a change occurs.
    // The results are modified in change. This could be expanded to also return the change set in a pair.
    private val listener =
        OrderedRealmCollectionChangeListener<RealmResults<T>> { results, changeSet ->
            this@LiveRealmResults.value = results
        }
    // We should start observing and stop observing, depending on whether we have observers.
    /**
     * Starts observing the RealmResults, if it is still valid.
     */
    override fun onActive() {
        super.onActive()
        if (results.isValid) { // invalidated results can no longer be observed.
            results.addChangeListener(listener)
        }
    }

    /**
     * Stops observing the RealmResults.
     */
    override fun onInactive() {
        super.onInactive()
        if (results.isValid) {
            results.removeChangeListener(listener)
        }
    }

    init {
        requireNotNull(results) { "Results cannot be null!" }
        require(results.isValid) { "The provided RealmResults is no longer valid, the Realm instance it belongs to is closed. It can no longer be observed for changes." }
        this.results = results
        if (results.isLoaded) {
            // we should not notify observers when results aren't ready yet (async query).
            // however, synchronous query should be set explicitly.
            // value = results

            // changed to work from background threads
            postValue(results)
        }
    }
}