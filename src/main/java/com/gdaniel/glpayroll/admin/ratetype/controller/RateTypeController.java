package com.gdaniel.glpayroll.admin.ratetype.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('admin')")
@RequestMapping("api/admin/ratetype")
public class RateTypeController {

}
