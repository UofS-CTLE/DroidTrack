package edu.scranton.ctleweb.droidtrack.projtrack

import java.io.Serializable
import java.util.*

object MyProjectsContent {

    /**
     * An array of sample (Project) items.
     */
    val ITEMS: MutableList<ProjectItem> = ArrayList()

    /**
     * A map of sample (Project) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, ProjectItem> = HashMap()

    private val COUNT = 25

    private fun addItem(item: ProjectItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    /**
     * A Project item representing a piece of content.
     */
    class ProjectItem(val id: String, val title: String, val description: String,
                      val completed: Boolean, val client: ClientItem, val projtype: TypeItem,
                      val date: String, val hours: String, val consultants: List<UserItem>) : Serializable {

        override fun toString(): String {
            return this.title
        }
    }

    class ClientItem(val first_name: String, val last_name: String, val email: String,
                     val department: DepartmentItem) : Serializable {

        override fun toString(): String {
            return this.first_name + " " + this.last_name
        }
    }

    class TypeItem(val name: String) : Serializable {

        override fun toString(): String {
            return this.name
        }
    }

    class UserItem(val username: String) : Serializable {

        override fun toString(): String {
            return this.username
        }
    }

    class DepartmentItem(val name: String) : Serializable {

        override fun toString(): String {
            return this.name
        }
    }

    class SemesterItem(val name: String) : Serializable {

        override fun toString(): String {
            return this.name
        }
    }
}
