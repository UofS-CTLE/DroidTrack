package edu.scranton.ctleweb.droidtrack.projtrack

import java.io.Serializable

object MyProjectsContent {

    /**
     * An array of sample (Project) items.
     */
    val ITEMS: MutableList<ProjectItem> = ArrayList()

    /**
     * A Project item representing a piece of content.
     */
    class ProjectItem(val id: String, val title: String, val description: String,
                      val completed: Boolean, var client: Int, var type: Int,
                      val date: String, val hours: String,
                      val consultants: List<Content.UserItem>) : Serializable {

        override fun toString(): String {
            return this.title
        }
    }
}
