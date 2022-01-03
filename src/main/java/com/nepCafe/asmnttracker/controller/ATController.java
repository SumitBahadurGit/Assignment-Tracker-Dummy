package com.nepCafe.asmnttracker.controller;

import java.util.Date;
import java.util.Set;

import com.nepCafe.asmnttracker.models.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.service.AtService;
import com.nepCafe.asmnttracker.service.UploadService;

@RestController
@RequestMapping("/v1/assignments")
public class ATController {

    private AtService service;
    private UploadService uploadService;

    ATController(AtService service, UploadService uploadService) {
        this.service = service;
        this.uploadService = uploadService;
    }

    @GetMapping("/{id}/find")
    public ATBody findAssignment(@PathVariable(required = true) long id) throws KException {
        return service.findAssignment(id);
    }

    @GetMapping("/{commentId}/comment/find")
    public Comment findComment(@PathVariable(required = true) long commentId) {
        return service.findComment(commentId);
    }

    @GetMapping("/{assignmentId}/comments/find")
    public Comments findComments(@PathVariable(required = true) long assignmentId) {
        return service.findComments(assignmentId);
    }

    @PostMapping("/find")
    public ATResponse findAssignments(@RequestBody SearchCriteria searchCriteria) {
        return service.findAssignments(searchCriteria);
    }

    @PostMapping("/save")
    public ATBody saveAssignment(@RequestBody ATBody request) throws KException {

        return service.saveAssignment(request);
    }

    ;

    @PostMapping("/{atId}/comments/save")
    public Comment saveComment(@PathVariable(required = true) long atId, @RequestBody Comment request) {
        return service.saveComment(atId, request);
    }

    ;

    @PostMapping("/{id}/update")
    public ATBody updateAssignment(@PathVariable(required = true) long id, @RequestBody ATBody request) {
        return service.updateAssignment(id, request);
    }

    @DeleteMapping("/{id}/delete")
    public ATBody deleteAssignment(@PathVariable(required = true) long id) {
        return service.deleteAssignment(id);
    }


    @GetMapping("/count")
    public AtCount getCount(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String postedBy,
            @RequestParam(required = false) String assignedTo,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String expiresFrom,
            @RequestParam(required = false) String expiresTo) {
        return service.getCount(status, postedBy, assignedTo, searchTerm, expiresFrom, expiresTo);
    }

}
