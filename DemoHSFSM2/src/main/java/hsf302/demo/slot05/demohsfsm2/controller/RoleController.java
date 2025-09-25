package hsf302.demo.slot05.demohsfsm2.controller;

import hsf302.demo.slot05.demohsfsm2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;



}
