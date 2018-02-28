package edu.scranton.ctleweb.droidtrack.projtrack

import java.io.Serializable

object AllProjectsContent {

    /**
     * An array of sample (Project) items.
     */
    val ITEMS: MutableList<ProjectItem> = ArrayList()

    /**
     * A Project item representing a piece of content.
     */
    class ProjectItem(val id: String, val title: String, val description: String,
                      val completed: Boolean, val client: Int, val type: Int,
                      val date: String, val hours: String,
                      val users: List<Content.UserItem>) : Serializable {

        override fun toString(): String {
            return this.title
        }
    }
}
