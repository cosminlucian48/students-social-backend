package com.example.studentssocial.service;

import com.example.studentssocial.dto.MessageDto;
import com.example.studentssocial.dto.PostDto;
import com.example.studentssocial.entity.Post;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.enums.UserEmailOptions;
import com.example.studentssocial.mapper.MessageMapper;
import com.example.studentssocial.mapper.PostMapper;
import com.example.studentssocial.repository.PostRepository;
import com.example.studentssocial.repository.SubjectRepository;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final SubjectRepository subjectRepository;
    private final UserService userService;
    private final ParseService parseService;
    private final CommentsService commentsService;
    private final PostMapper postMapper;
    private final MessageMapper messageMapper;
    private final SendEmailService sendEmailService;
    private final FileStorageServiceImpl storageService;

    @Autowired
    public PostService(PostRepository postRepository, SubjectRepository subjectRepository, UserService userService, ParseService parseService, CommentsService commentsService, PostMapper postMapper, MessageMapper messageMapper, SendEmailService sendEmailService, FileStorageServiceImpl storageService) {
        this.subjectRepository = subjectRepository;
        this.userService = userService;
        this.parseService = parseService;
        this.commentsService = commentsService;
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.messageMapper = messageMapper;
        this.sendEmailService = sendEmailService;
        this.storageService = storageService;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
//        postRepository.findAllByOrderByPostDateDesc().iterator().forEachRemaining(posts::add);
        posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
//        postRepository.findAll().iterator().forEachRemaining(posts::add);

        posts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                if (o1.getPostDate().compareTo(o2.getPostDate()) < 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });


        return posts;
    }

    public Optional<Post> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost;
    }

//    public List<FileInfo> getAllFiles(){
//        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
//            String filename = path.getFileName().toString();
//
//
//            return new FileInfo(filename, url);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//    }

    public Resource getFile(String filename, Long subjectId){
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        System.out.println(subjectId);
        String subjectName = "";
        if(subject.isPresent()){
            subjectName = subject.get().getName();
        }
        Resource file = storageService.load(filename,subjectName);
//        System.out.println(file);
        return file;
    }


    public void deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            commentsService.deleteCommentsWithPostId(id);
            postRepository.delete(post.get());
        }
    }

    public PostDto updatePost(Post postDto) {
        Post post = postRepository.findById(postDto.getId()).get();
        post.setPostDate(postDto.getPostDate());
        post.setTitle(postDto.getTitle());
        post.setText(postDto.getText());
        post.setIsSticky(postDto.getIsSticky());
        post.setId(postDto.getId());
        /*post.setSubject(postDto.getSubject());
        post.setUserDetails(postDto.getUserDetails());*/
        return postMapper.mapPostToPostDto(post);


    }

    public PostDto savePost(PostDto postDto, List<MultipartFile> files) {
        if (files != null) {
            try {
                String filePath = getUploadFilePathAndCreateIfNotExist(postDto.getSubjectId());
                files.forEach((file) -> {
                    String fileName = file.getOriginalFilename();
                    fileName = getUniqueFileName(filePath, fileName);
                    if (postDto.getFileName() != "") {
                        postDto.setFileName(postDto.getFileName() + "," + fileName);
                    } else {
                        postDto.setFileName(fileName);
                    }
                    //trb sa verific cand fac save

                    storageService.save(file, filePath, fileName);
                });

            } catch (Exception e) {

            }
        }
        List<User> users = userService.getUsersByEmail(postDto.getEmail());
        postDto.setUserId(users.get(0).getId());
        postDto.setPostType(users.get(0).getAuthorities());
        Post post = postMapper.mapPostDtoToPost(postDto);

        Post savedPost = postRepository.save(post);

        MessageDto messageDto = messageMapper.fromPostDtoToMessageDto(postDto);


//        System.out.println(storageService.loadAll().collect(Collectors.toList()));
        Thread thread = new Thread(() -> sendEmailService.verifyAndSendEmail(messageDto, UserEmailOptions.POST));
        thread.start();

        return postMapper.mapPostToPostDto(savedPost);
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


    public List<PostDto> getPostsBySubjectId(Long subjectId) throws IOException {
        List<Post> allPosts = new ArrayList<>();
        List<File> files = new ArrayList<>();
        postRepository.findAll().iterator().forEachRemaining(allPosts::add);
        List<PostDto> finalPosts = new ArrayList<>();
        for (Post post : allPosts) {
            files = new ArrayList<>();
            if (post.getSubject().getId() == subjectId) {
                User user = userService.getUserById(post.getUser().getId());
                PostDto postDto = postMapper.mapPostToPostDto(post);

                postDto.setEmail(user.getEmail());
                finalPosts.add(postDto);
            }
        }

//        Collections.sort(finalPosts, new Comparator<PostDto>() {
//            @Override
//            public int compare(PostDto o1, PostDto o2) {
//                if (o1.getPostDate().before(o2.getPostDate())) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            }
//        });

        Comparator<PostDto> comparator = (PostDto o1, PostDto o2) -> {
            if (o1.getPostDate().before(o2.getPostDate())) {
                return 1;
            } else if (o1.getPostDate().equals(o2.getPostDate())) {
                return 0;
            } else {
                return -1;
            }

        };
        ArrayList<PostDto> posts = finalPosts.stream().sorted(comparator).collect(Collectors.toCollection(ArrayList::new));
        return posts;
    }

//    public PostDto updatePost(PostDto postDto){
//        Post post = postRepository.findById(postDto.getId()).get();
//        return postMapper.mapPostToPostDto(post);
//    }

    public void deletePostWithSubjectId(Long subjectId) {

        List<Post> posts = postRepository.findAllBySubjectId(subjectId);
        for (Post post : posts) {
            this.commentsService.deleteCommentsWithPostId(post.getId());
        }
        postRepository.deleteAllBySubjectId(subjectId);
    }
}
