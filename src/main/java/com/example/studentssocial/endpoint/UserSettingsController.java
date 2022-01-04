package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.UserSettingsDto;
import com.example.studentssocial.service.UserSettingsService;
import com.example.studentssocial.service.UserSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user-settings")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    @Autowired
    public UserSettingsController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @PostMapping
    public void postUserSubjects(@RequestBody UserSettingsDto userSettingsDto) {
        userSettingsService.saveUserSettings(userSettingsDto);
    }

    @GetMapping(value="/{id}")
    public UserSettingsDto getUserSettings(@PathVariable Long id){
        return userSettingsService.getUserSettings(id);
    }

    @GetMapping
    public UserSettingsDto getUserSettingsByEmail(@RequestParam String email){
        return userSettingsService.getUserSettingsByEmail(email);
    }

}
