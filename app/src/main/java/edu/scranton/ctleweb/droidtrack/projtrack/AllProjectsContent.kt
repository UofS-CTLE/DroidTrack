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

    /**
     * A Project item representing a piece of content.
     */
    class ProjectItem(val id: String, val title: String, val description: String,
                      val completed: Boolean, val cli: Int, val type: Int,
                      val date: String, val hours: String, val consultants: List<Content.UserItem>) : Serializable {

        val projtype: Content.TypeItem = Content.TYPES[type]
        val client: Content.ClientItem = Content.CLIENTS[cli]

        override fun toString(): String {
            return this.title
        }
    }
}
