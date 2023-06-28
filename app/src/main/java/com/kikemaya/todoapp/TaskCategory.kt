package com.kikemaya.todoapp

sealed class TaskCategory(var isSelected: Boolean = true) {
    object Personal : TaskCategory() {
        override fun toString(): String {
            return "Personal"
        }
    }

    object Business : TaskCategory() {
        override fun toString(): String {
            return "Business"
        }
    }

    object Other : TaskCategory() {
        override fun toString(): String {
            return "Other"
        }
    }
}