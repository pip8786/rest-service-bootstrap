package com.pip.restservicebootstrap.rest;

import com.pip.restservicebootstrap.dao.DataAccess;
import com.pip.restservicebootstrap.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by phsabo on 1/26/14.
 */
@Path("/User")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUser(@Context HttpServletRequest request) {
		return DataAccess.getInstance().getAllUsers();
	}
}