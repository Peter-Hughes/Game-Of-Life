package main.kotlin

import org.junit.Test


class CellTest {
    private val neighbors: MutableList<Cell> = mutableListOf()
    private var cell: Cell = Cell(mutableListOf())

    init {
        (0..8).forEach { _ -> neighbors.add(Cell(mutableListOf())) }
        cell = Cell(mutableListOf())
    }

    @Test
    fun `Cell can be made alive`() {
        cell.makeAlive()
        assert(cell.isAlive())
    }

    @Test
    fun `Cell can be made dead`() {
        cell.makeAlive()
        assert(cell.isAlive())
        cell.makeDead()
        assert(cell.isDead())
    }

    @Test
    fun `Cell dies when there is less than 2 neighbours alive`() {
        cell.makeAlive()
        neighbors[0].makeAlive()
        neighbors.forEach(cell::addNeighbour)

        cell.changeState()

        assert(cell.isDead())
    }

    @Test
    fun `Cell dies when there is more than 3 neighbours alive`() {
        cell.makeAlive()
        (0..3).forEach { neighbors[it].makeAlive() }
        neighbors.forEach(cell::addNeighbour)

        cell.changeState()

        assert(cell.isDead())
    }

    @Test
    fun `Cell lives when there is exactly 2 neighbours alive`() {
        cell.makeAlive()
        (0..1).forEach { neighbors[it].makeAlive() }
        neighbors.forEach(cell::addNeighbour)

        cell.changeState()

        assert(cell.isAlive())
    }

    @Test
    fun `Cell lives when there is exactly 3 neighbours alive`() {
        cell.makeAlive()
        (0..2).forEach { neighbors[it].makeAlive() }
        neighbors.forEach(cell::addNeighbour)

        cell.changeState()

        assert(cell.isAlive())
    }

    @Test
    fun `Cell becomes alive when there is exactly 3 neighbours alive`() {
        (0..2).forEach { neighbors[it].makeAlive() }
        neighbors.forEach(cell::addNeighbour)

        cell.changeState()

        assert(cell.isAlive())
    }
}