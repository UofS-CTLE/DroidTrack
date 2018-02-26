package edu.scranton.ctleweb.droidtrack.projtrack

import edu.scranton.ctleweb.droidtrack.ProjtrackService
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

object AllProjectsContent {

    /**
     * An array of sample (Project) items.
     */
    val ITEMS: MutableList<ProjectItem> = ArrayList()
    val CLIENTS: MutableList<ClientItem> = ArrayList()
    val TYPES: MutableList<TypeItem> = ArrayList()
    val DEPTS: MutableList<DepartmentItem> = ArrayList()

    /**
     * A Project item representing a piece of content.
     */
    class ProjectItem(val id: String, val title: String, val description: String,
                      val completed: Boolean, val cli: Int, val type: Int,
                      val date: String, val hours: String, val consultants: List<UserItem>) : Serializable {

        val projtype: TypeItem = AllProjectsContent.TYPES[type]
        val client: ClientItem = AllProjectsContent.CLIENTS[cli]

        override fun toString(): String {
            return this.title
        }
    }

    class ClientItem(private val first_name: String, val last_name: String, val email: String,
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

    class UserItem(private val username: String) : Serializable {

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
