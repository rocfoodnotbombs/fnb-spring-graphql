package com.fnb.locations.graphql.location

import com.expediagroup.graphql.spring.operations.Mutation
import com.fnb.locations.customExceptions.NotLoggedInExceptionException
import com.fnb.locations.model.Location
import com.fnb.locations.security.MyGraphQLContext
import com.fnb.locations.service.impl.LocationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeleteLocationMutation
@Autowired constructor(private val locationService: LocationService) : Mutation {

    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun deleteLocation(graphQLContext: MyGraphQLContext, id: Int): Location {
        logger.debug("Request to delete location with ID $id")
        val loggedInUser = graphQLContext.loggedInUser
                ?: throw NotLoggedInExceptionException("Log in to add new location")
        return locationService.deleteLocation(loggedInUser, id)
    }
}