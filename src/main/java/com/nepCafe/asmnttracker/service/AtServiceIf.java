package com.nepCafe.asmnttracker.service;

import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.models.*;

public interface AtServiceIf {

    ATBody updateAssignment(Long id, ATBody request);

    ATBody saveAssignment(ATBody request) throws KException;

    ATBody findAssignment(Long id) throws KException;

    ATResponse findAssignments(SearchCriteria searchCriteria);

    ATBody deleteAssignment(long id);

    AtCount getCount(String status, String postedBy, String assignedTo, String searchTerm, String expiresFrom,
                     String expiresTo);

    Comment saveComment(Long id, Comment comment);

    Comment findComment(long commentId);

    Comments findComments(long assignmentId);

}
