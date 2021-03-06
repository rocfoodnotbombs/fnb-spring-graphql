package com.fnb.locations.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
open class OrgUser(
        @Id
        var id: Int? = null,
        val email: String,
        val userPassword: String,
        val count: Int,
        val permissionLevel: UserPermissionLevel
) {
        fun toLoggedInUser(): LoggedInUser = LoggedInUser(
                id = this.id ?: throw IllegalArgumentException("user not yet persisted"),
                email = this.email,
                permissionLevel = this.permissionLevel)
}
