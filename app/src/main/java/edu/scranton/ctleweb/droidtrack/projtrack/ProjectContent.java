package edu.scranton.ctleweb.droidtrack.projtrack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectContent {

    /**
     * An array of sample (Project) items.
     */
    public static final List<ProjectItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (Project) items, by ID.
     */
    public static final Map<String, ProjectItem> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 25;

    private static void addItem(ProjectItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A Project item representing a piece of content.
     */
    public static class ProjectItem {
        public final String id;
        public final String title;
        public final String description;
        public final boolean completed;
        public final ClientItem client;
        public final TypeItem projtype;
        public final String date;
        public final List<UserItem> consultants;

        public ProjectItem(String id, String title, String description,
                           boolean completed, ClientItem client, TypeItem projtype,
                           String date, List<UserItem> consultants) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.completed = completed;
            this.client = client;
            this.projtype = projtype;
            this.date = date;
            this.consultants = consultants;
        }

        @Override
        public String toString() {
            return this.title;
        }
    }

    public static class ClientItem {

        public final String first_name;
        public final String last_name;
        public final String email;
        public final DepartmentItem department;

        public ClientItem(String first_name, String last_name, String email,
                          DepartmentItem department) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.department = department;

        }

        @Override
        public String toString() {
            return (this.first_name + " " + this.last_name);
        }
    }

    public static class TypeItem {

        public final String name;

        public TypeItem(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static class UserItem {

        public final String username;

        public UserItem(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return this.username;
        }
    }

    public static class DepartmentItem {

        public final String name;

        public DepartmentItem(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static class SemesterItem {

        public final String name;

        public SemesterItem(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
