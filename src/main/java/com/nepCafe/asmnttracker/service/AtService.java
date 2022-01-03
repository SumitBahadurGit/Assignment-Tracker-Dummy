package com.nepCafe.asmnttracker.service;

import com.nepCafe.asmnttracker.dao.AtDao;
import com.nepCafe.asmnttracker.dao.AttachmentDao;
import com.nepCafe.asmnttracker.dao.CommentsDao;
import com.nepCafe.asmnttracker.dao.UserDao;
import com.nepCafe.asmnttracker.dto.ATResponseDTO;
import com.nepCafe.asmnttracker.dto.CommentsDTO;
import com.nepCafe.asmnttracker.dto.UserDto;
import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.exceptions.KExceptionCodes;
import com.nepCafe.asmnttracker.http.RemoteDocService;
import com.nepCafe.asmnttracker.models.*;
import com.nepCafe.asmnttracker.util.DocConstants.DOCTYPES;
import com.nepCafe.asmnttracker.util.MailUtil;
import com.nepCafe.asmnttracker.util.MapperUtil;
import com.nepCafe.asmnttracker.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AtService implements  AtServiceIf {


	private AtDao dao;
	private AttachmentDao attachmentDao;
	private CommentsDao commentsDao;
	private UserDao userDao;
	private MailService mailService;
	private RemoteDocService remoteDocService;

	@Autowired
	public AtService( AtDao dao, AttachmentDao attachmentDao, CommentsDao commentsDao, UserDao userDao,
					 MailService mailService, RemoteDocService remoteDocService) {

		this.dao = dao;
		this.attachmentDao = attachmentDao;
		this.commentsDao = commentsDao;
		this.userDao = userDao;
		this.mailService = mailService;
		this.remoteDocService = remoteDocService;
	}

	@Override
	public Comment saveComment(Long id, Comment comment) {

		ATResponseDTO atResponseDTO = dao.find(id);
		Comment response = null;
		if (atResponseDTO != null) {
			CommentsDTO toSave = MapperUtil.comment2Dto.apply(comment);
			UserDto author = userDao.find(comment.getAuthor().getId());
			toSave.setPostedBy(author);
			toSave = commentsDao.save(toSave);
			response = MapperUtil.dto2Comment.apply(toSave);
		}

		if (response == null) {
			response = new Comment();
			response.setErrorMessage("Error saving comment");
		} else {

			if (atResponseDTO.getTechResource() != null) {
				ATBody atBody = MapperUtil.dto2atRespFunc.apply(atResponseDTO);
				Email email = MailUtil.handleNewComment(atBody, response);
				if (email != null) {
					try {
						mailService.sendSimpleMessage(email);
					} catch (KException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return response;
	}

	@Override
	public ATBody updateAssignment(Long id, ATBody request) {

		ATBody response = null;
		ATResponseDTO currentEntity = dao.find(id);
		ATBody lastAssignment = null;
		boolean isAssignUpdate = false;

		if (currentEntity != null) {

			lastAssignment = MapperUtil.dto2atRespFunc.apply(currentEntity);

			currentEntity = MapperUtil.map2DtoNotEmpty(currentEntity, request);

			if (request.getUpdatedBy() != null) {

				Long currentUpdatedById = currentEntity.getUpdatedBy() == null ? 0
						: currentEntity.getUpdatedBy().getId();
				Long newUpdatedById = request.getUpdatedBy().getId();

				if (currentUpdatedById != newUpdatedById) {
					UserDto user = userDao.find(newUpdatedById);
					if (user != null) {
						currentEntity.setUpdatedBy(user);

					} else {
						currentEntity.setUpdatedBy(null);

					}

				}

			}
			
			if (request.getTechResource() != null) {
				Long curTechResource = currentEntity.getTechResource() == null ? 0 : currentEntity.getTechResource().getId();
				Long newAssigneeId = request.getTechResource().getId();

				if (curTechResource != newAssigneeId) {
					UserDto newAssignee = userDao.find(newAssigneeId);
					if (newAssignee != null) {
						currentEntity.setTechResource(newAssignee);
					} else {
						currentEntity.setTechResource(null);
					}
				}
			}
			
			if (request.getAssignee() != null) {
				Long currentAssigneeId = currentEntity.getAssignee() == null ? 0 : currentEntity.getAssignee().getId();
				Long newAssigneeId = request.getAssignee().getId();

				if (currentAssigneeId != newAssigneeId) {
					UserDto newAssignee = userDao.find(newAssigneeId);
					if (newAssignee != null) {
						currentEntity.setAssignee(newAssignee);
						isAssignUpdate = true;
					} else {
						currentEntity.setAssignee(null);
						isAssignUpdate = true;
					}
				}
			}

			currentEntity = dao.save(currentEntity);

			response = MapperUtil.dto2atRespFunc.apply(currentEntity);
		}

		if (response == null) {

			response = new ATBody();
			response.setErrorMessage("Error updating assignment");

		} else {

			if (isAssignUpdate) {

				List<Email> emails = MailUtil.handleAssigneeUpdate(response, lastAssignment);
				if (emails != null && !emails.isEmpty()) {
					try {
						mailService.sendEmails(emails);
					} catch (KException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				Email email = MailUtil.handleAssignmentUpdate(response);
				if (email != null) {
					try {
						mailService.sendSimpleMessage(email);
					} catch (KException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
		return response;
	}

	@Override
	public ATBody saveAssignment(ATBody request) throws KException {

		ATBody response = null;
		Validator.validateAssignemnt(request);
		ATResponseDTO entity = MapperUtil.atResp2DtoFunc.apply(request);
		entity = dao.save(entity);
		response = MapperUtil.dto2atRespFunc.apply(entity);

		if (response != null) {
			if (request.getPostedBy() != null && request.getPostedBy().getId() != 0) {
				UserDto postedByDto = userDao.find(request.getPostedBy().getId());
				response.setPostedBy(MapperUtil.dto2User.apply(postedByDto));
			}
			if (request.getUpdatedBy() != null && request.getUpdatedBy().getId() != 0) {
				UserDto updatedBy = userDao.find(request.getUpdatedBy().getId());
				response.setUpdatedBy(MapperUtil.dto2User.apply(updatedBy));
			}
			if (request.getTechResource() != null && request.getTechResource().getId() != 0) {
				UserDto assineeDto = userDao.find(request.getTechResource().getId());
				response.setTechResource(MapperUtil.dto2User.apply(assineeDto));
			}
			if (request.getAssignee() != null && request.getAssignee().getId() != 0) {
				UserDto assineeDto = userDao.find(request.getAssignee().getId());
				response.setAssignee(MapperUtil.dto2User.apply(assineeDto));

				List<Email> emails = MailUtil.handleAssigneeUpdate(response, null);
				if (emails != null && !emails.isEmpty()) {
					mailService.sendEmails(emails);
				}
			}
		} else {
			response = new ATBody();
			response.setErrorMessage("Error saving assignment");
		}

		return response;
	}

	@Override
	public ATBody findAssignment(Long id) throws KException {
		ATBody response = null;
		ATResponseDTO entity = dao.find(id);
		if (entity != null) {
			response = MapperUtil.dto2atRespFunc.apply(entity);
			AttachmentResponse attachmentResponse = null;
			try {
				 attachmentResponse = remoteDocService.findAttachemnts(response.getId(),DOCTYPES.ASSIGNMENT_DOC.toString());				
			} catch (Exception ex) {
				throw new KException(KExceptionCodes.REMOTE_ERR, " Error getting attachments");			}

			if(attachmentResponse != null && attachmentResponse.getAttachments() != null) {
				response.setAttachments(attachmentResponse.getAttachments());
			}
			
		}

		if (response == null) {
			response = new ATBody();
			response.setErrorMessage("Error finding assignment");
		}
		return response;
	}

	@Override
	public ATResponse findAssignments(SearchCriteria searchCriteria) {
		ATResponse response = null;
		Page<ATResponseDTO> pages = dao.findAll(searchCriteria);

		List<ATResponseDTO> entities = pages.getContent();
		if (entities != null && !entities.isEmpty()) {
			List<ATBody> responseBody;
			responseBody = entities.stream().map(x -> MapperUtil.dto2MinAtRespFunc.apply(x)).collect(Collectors.toList());
			response = new ATResponse();
			response.setPage(searchCriteria.getPage());
			response.setSize(pages.getNumberOfElements());
			response.setTotalPage(pages.getTotalPages());
			response.setTotalItems(pages.getTotalElements());			
			response.setAssignments(responseBody);
		}
		
		if (response == null) {
			response = new ATResponse();
			response.setMessage("No Assignments");
		}
		return response;
	}

	@Override
	public AtCount getCount(String status, String postedBy, String assignedTo, String searchTerm, String expiresFrom,
							String expiresTo) {
		AtCount response = new AtCount();
		List<Object[]> counts = dao.getCount(status, postedBy, assignedTo, searchTerm, expiresFrom, expiresTo);
		Map<String, Long> countsMap = new HashMap<>();
		response.setStatusCount(countsMap);
		Long total = 0l;
		for (Object[] o : counts) {
			countsMap.put((String) o[0], (Long) o[1]);
			total += (Long) o[1];
		}
		if (!countsMap.isEmpty()) {
			countsMap.put("ALL", total);
		}

		return response;

	}

	@Override
	public Comment findComment(long commentId) {
		Comment response = null;
		CommentsDTO commentsDTO = commentsDao.find(commentId);
		if (commentsDTO != null) {
			response = MapperUtil.dto2Comment.apply(commentsDTO);
		}
		if (response == null) {
			response = new Comment();
			response.setMessage("No comment found for the id.");
		}

		return response;
	}

	@Override
	public Comments findComments(long assignmentId) {
		Comments comments = new Comments();
		List<CommentsDTO> commentDtos = commentsDao.findAll(assignmentId);
		List<Comment> commentsList = new ArrayList<>();
		if (commentDtos != null && !commentDtos.isEmpty()) {
			commentsList = commentDtos.stream().map(x -> MapperUtil.dto2Comment.apply(x)).collect(Collectors.toList());
			comments.setComments(commentsList);
		}
		if (commentsList.isEmpty()) {
			comments.setMessage("No comments found");
		}
		return comments;
	}

	@Override
	public ATBody deleteAssignment(long id) {
		ATBody response = new ATBody();
		dao.delete(id);
		response.setMessage("OK");
		return response;
	}
}
