package com.gdaniel.glpayroll.admin.employeestatus.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

//Comment: This controller will handle all the requests related to employee status management in the admin panel. It will include endpoints for creating, updating, retrieving, and deleting employee statuses. The actual implementation of these endpoints will be added later as we develop the service layer and repository layer for employee status management.
// The @RestController annotation indicates that this class will handle RESTful web requests, and the @RequestMapping annotation specifies the base URL for all endpoints in this controller. The @AllArgsConstructor annotation from Lombok will generate a constructor with parameters for all fields, which can be useful for dependency injection when we add service components later on.

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('admin')")
@RequestMapping("api/admin/employeestatus")
public class EmployeeStatusController {

}
