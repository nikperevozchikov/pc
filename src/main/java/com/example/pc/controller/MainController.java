package com.example.pc.controller;

import com.example.pc.domain.App;
import com.example.pc.domain.User;
import com.example.pc.repos.AppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    private AppRepo appRepo;

    //    @Value("${upload.path}")
//    private String uploadPath;
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<App> apps = appRepo.findAll();

        model.put("apps", apps);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String modelpc,
            @RequestParam String reason, Map<String, Object> model
    ) {
        App app = new App(modelpc, reason, user);

        appRepo.save(app);

        Iterable<App> apps = appRepo.findAll();

        model.put("apps", apps);

        return "main";
    }

//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model) {
//        Iterable<App> apps;
//
//        if (filter != null && !filter.isEmpty()) {
//            apps = appRepo.findByReason(filter);
//        } else {
//            apps = appRepo.findAll();
//        }
//
//        model.put("apps", apps);
//
//        return "main";
//    }
//
//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<App> messages;
//
//        if (filter != null && !filter.isEmpty()) {
//            messages = appRepo.findByTag(filter);
//        } else {
//            messages = appRepo.findAll();
//        }
//
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//
//        return "main";
//    }
//

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("main-delete")
    public String deleteApp(@PathVariable App app) {
        appRepo.delete(app);
        return "redirect:/main";
    }

    @GetMapping("/user-apps/{user}")
    public String userApps(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) App app
    ) {
        Set<App> apps = user.getApps();
        model.addAttribute("apps", apps);
        model.addAttribute("app", app);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "main";
    }

    @PostMapping("/user-apps/{user}")
    public String updateApp(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") App app,
            @RequestParam("modelpc") String modelpc,
            @RequestParam("reason") String reason

    ) {
        if (app.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(modelpc)) {
                app.setModelpc(modelpc);
            }

            if (!StringUtils.isEmpty(reason)) {
                app.setReason(reason);
            }

            appRepo.save(app);
        }

        return "redirect:/user-apps/" + user;
    }
}
