package org.jetbrains.research.kthelper.collection

class Subset<T : Any>(val data: T?) {
    var parent = this
        internal set
    var rank = 0
        internal set

    fun isRoot() = parent == this

    fun getRoot(): Subset<T> = if (!isRoot()) {
        val ancestor = parent.getRoot()
        parent = ancestor
        ancestor
    } else this

    override fun hashCode() = System.identityHashCode(this)
    override fun equals(other: Any?) = this === other
    override fun toString() = "Subset $data"
}

class DisjointSet<T : Any>(private val children: MutableSet<Subset<T>> = mutableSetOf()) : MutableSet<Subset<T>> by children {
    fun find(element: Subset<T>) = element.getRoot()
    fun findUnsafe(element: Subset<T>?) = element?.getRoot()

    private fun Subset<T>.merge(other: Subset<T>): Subset<T> = when {
        this == other -> this
        this.rank < other.rank -> {
            this.parent = other
            other
        }
        this.rank > other.rank -> {
            other.parent = this
            this
        }
        else -> {
            other.parent = this
            ++this.rank
            this
        }
    }

    fun join(lhv: Subset<T>, rhv: Subset<T>): Subset<T> {
        val lhvRoot = find(lhv)
        val rhvRoot = find(rhv)

        return lhvRoot.merge(rhvRoot)
    }

    fun joinUnsafe(lhv: Subset<T>?, rhv: Subset<T>?): Subset<T>? {
        val lhvRoot = findUnsafe(lhv)
        val rhvRoot = findUnsafe(rhv)

        if (lhvRoot == null) return null
        if (rhvRoot == null) return null

        return lhvRoot.merge(rhvRoot)
    }

    fun emplace(element: T?): Subset<T> {
        val wrapped = Subset(element)
        add(wrapped)
        return wrapped
    }
}