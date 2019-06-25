package main.kotlin

import main.kotlin.CellState.ALIVE
import main.kotlin.CellState.DEAD

class Cell(neighbours: MutableList<Cell>) {
    private val neighbors: MutableList<Cell> = neighbours
    private var cellState: CellState = DEAD

    fun makeAlive() = setState(ALIVE)
    fun makeDead() = setState(DEAD)
    fun isAlive(): Boolean = cellState === ALIVE
    fun isDead(): Boolean = cellState === DEAD

    private fun setState(state: CellState) {
        cellState = state
    }

    fun addNeighbour(neighbour: Cell) {
        if (neighbors.size < 8) neighbors.add(neighbour)
    }

    fun changeState() {
        val count = neighbors.count { it.isAlive() }

        when {
            isAlive() && count < 2 || count > 3 -> makeDead()
            isDead() && count == 3 -> makeAlive()
        }
    }

}