package com.example.studentssocial.service;

import com.example.studentssocial.dto.CommentsDto;
import com.example.studentssocial.dto.MessageDto;
import com.example.studentssocial.entity.Comments;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.enums.UserEmailOptions;
import com.example.studentssocial.mapper.CommentsMapper;
import com.example.studentssocial.mapper.MessageMapper;
import com.example.studentssocial.repository.CommentsRepository;
import com.example.studentssocial.repository.PostRepository;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final SendEmailService sendEmailService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository;
    private final CommentsMapper commentsMapper;
    private final MessageMapper messageMapper;

    private final FileStorageServiceImpl storageService;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, SendEmailService sendEmailService, UserRepository userRepository, PostRepository postRepository, SubjectRepository subjectRepository, CommentsMapper commentsMapper, MessageMapper messageMapper, FileStorageServiceImpl storageService) {
        this.sendEmailService = sendEmailService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.subjectRepository = subjectRepository;
        this.commentsMapper = commentsMapper;
        this.commentsRepository = commentsRepository;
        this.messageMapper = messageMapper;
        this.storageService = storageService;
    }

    public List<Comments> getAllComments() {
        List<Comments> comments = new ArrayList<>();
        commentsRepository.findAll().iterator().forEachRemaining(comments::add);
        return comments;
    }

    public Optional<Comments> getComments(Long id) {
        Optional<Comments> optionalComments = commentsRepository.findById(id);
        return optionalComments;
    }

    public void deleteComments(Long id) {
        Optional<Comments> comments = commentsRepository.findById(id);
        if (comments.isPresent()) {
            commentsRepository.delete(comments.get());
        }
    }

    public List<CommentsDto> getCommentsbyPostId(Long postId){
        List<Comments> allComments = new ArrayList<>();
        commentsRepository.findAll().iterator().forEachRemaining(allComments::add);
        List<CommentsDto> finalComments = new ArrayList<>();
        for(Comments comments: allComments){
            if(comments.getPost().getId() == postId){
                finalComments.add(commentsMapper.mapCommentsToCommentsDto(comments));
            }
        }

        Comparator<CommentsDto> comparator = (CommentsDto o1, CommentsDto o2) -> {
            if (o1.getCommentsDate().before(o2.getCommentsDate())) {
                return 1;
            } else if (o1.getCommentsDate().equals(o2.getCommentsDate())) {
                return 0;
            } else {
                return -1;
            }

        };
        ArrayList<CommentsDto> comments = finalComments.stream().sorted(comparator).collect(Collectors.toCollection(ArrayList::new));


        return comments;
    }

    public CommentsDto updateComments(Comments commentsDto)
    {
        Comments comments = commentsRepository.findById(commentsDto.getId()).get();
        comments.setCommentsDate(commentsDto.getCommentsDate());
        comments.setText(commentsDto.getText());

        return commentsMapper.mapCommentsToCommentsDto(comments);


    }

    public CommentsDto saveComments(CommentsDto commentsDto,List<MultipartFile> files)
    {
        if (files != null) {
            try {
                Long postId = commentsDto.getPostId();
                Optional<Post> post = postRepository.findById(postId);
                Long subjectId= 0L;
                if(post.isPresent()){
                    subjectId = post.get().getSubject().getId();
                }
                String filePath = getUploadFilePathAndCreateIfNotExist(subjectId);
                files.forEach((file) -> {
                    String fileName = file.getOriginalFilename();
                    fileName = getUniqueFileName(filePath, fileName);
                    if (commentsDto.getFileName() != "") {
                        commentsDto.setFileName(commentsDto.getFileName() + "," + fileName);
                    } else {
                        commentsDto.setFileName(fileName);
                    }
                    //trb sa verific cand fac save

                    storageService.save(file, filePath, fileName);
                });

            } catch (Exception e) {

            }
        }


        Comments comments = commentsMapper.mapCommentsDtoToComments(commentsDto);

        Optional<User> user = userRepository.findById(commentsDto.getUserId());
        if(user.isPresent()){
            comments.setCommentType(user.get().getAuthorities());
        }
        Comments savedComments = commentsRepository.save(comments);

        MessageDto messageDto = messageMapper.fromCommentDtoToMessageDto(commentsDto);

        Thread thread = new Thread(() -> sendEmailService.verifyAndSendEmail(messageDto, UserEmailOptions.COMMENT));
        thread.start();
        return commentsMapper.mapCommentsToCommentsDto(savedComments);
    }


    private String getUniqueFileName(String filePath, String fileName) {
        String uniqueFileName = fileName;
        if (storageService.checkIfFileNameExists(fileName, filePath)) {
            uniqueFileName = storageService.generateNameForExistingFile(fileName, filePath);
            return getUniqueFileName(filePath, uniqueFileName);
        }else{

            return uniqueFileName;
        }
    }

    private String getUploadFilePathAndCreateIfNotExist(Long subjectId) {
        String uploadFilePath = getUploadFilePath(subjectId);
        storageService.checkAndAddFolder(uploadFilePath);
        return uploadFilePath;
    }

    private String getUploadFilePath(Long subjectId) {
        String rootPath = storageService.getRootPath();
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        String subjectDirectory = "";
        if (subject.isPresent()) {
            subjectDirectory = subject.get().getName();
        }
        return rootPath + File.separator + subjectDirectory;
    }



    public void deleteCommentsWithPostId(Long postId){
        commentsRepository.deleteAllByPostId(postId);
    }


}
