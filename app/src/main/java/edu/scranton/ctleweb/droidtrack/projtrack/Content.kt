package edu.scranton.ctleweb.droidtrack.projtrack

import java.io.Serializable

object Content {

    var TOKEN: String = ""
    val CLIENTS: MutableList<ClientItem> = ArrayList()
    val TYPES: MutableList<TypeItem> = ArrayList()
    val DEPTS: MutableList<DepartmentItem> = ArrayList()
    val USERS: MutableList<UserItem> = ArrayList()

    class ClientItem(val id: Int, val first_name: String, val last_name: String,
                     val email: String, department: Int) : Serializable {

        override fun toString(): String {
            return this.first_name + " " + this.last_name
        }
    }

    class TypeItem(val id: Int, val name: String) : Serializable {

        override fun toString(): String {
            return this.name
        }
    }

    class UserItem(val id: Int, val username: String) : Serializable {

        override fun toString(): String {
            return this.username
        }
    }

    class DepartmentItem(val id: Int, val name: String) : Serializable {

        override fun toString(): String {
            return this.name
        }
    }

    class SemesterItem(val id: Int, val name: String) : Serializable {

        override fun toString(): String {
            return this.name
        }
    }
}