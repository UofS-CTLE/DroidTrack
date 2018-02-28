package edu.scranton.ctleweb.droidtrack.projtrack

import java.io.Serializable

object Content {

    val CLIENTS: MutableList<ClientItem> = ArrayList()
    val TYPES: MutableList<TypeItem> = ArrayList()
    val DEPTS: MutableList<DepartmentItem> = ArrayList()

    class ClientItem(private val first_name: String, val last_name: String, val email: String,
                     department: Int) : Serializable {

        val dept: DepartmentItem = DEPTS[department]

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