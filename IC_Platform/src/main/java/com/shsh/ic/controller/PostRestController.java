package com.shsh.ic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shsh.ic.model.dto.Posts;
import com.shsh.ic.model.service.PostService;

@RestController
@RequestMapping("/api")
public class PostRestController {
	
	private PostService postService;
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	@Autowired
	public PostRestController(PostService postService) {
		this.postService = postService;
	}

	// 이미지 등록
	@PostMapping("/post/{playerId}")
	public ResponseEntity<?> addPost(@RequestPart("file") MultipartFile file, @PathVariable int playerId) {
		if (!Objects.requireNonNull(file.getContentType()).startsWith("image"))
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		
		Path root = Paths.get(uploadPath);
		
		if (!Files.exists(root)) {
			try {
				Files.createDirectories(Paths.get(uploadPath));
			} catch (IOException e) {
				throw new RuntimeException("폴더 생성 실패");
			}
		}
		String postName = UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
		Path postPath = root.resolve(postName);
		Posts post = new Posts(playerId, postName, postPath.toString(), file.getSize());
		
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, postPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new RuntimeException("파일을 저장할 수 없습니다. Error: " + e.getMessage());
		}
		
		if (postService.insertPost(post, playerId))
			return new ResponseEntity<String>("success", HttpStatus.CREATED);

		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// 해당 선수가 등록한 이미지 가져오기
	@GetMapping("/post/{playerId}")
	public ResponseEntity<?> getPost(@PathVariable int playerId) {
		List<Posts> postList = postService.selectPosts(playerId);
		if (postList == null || postList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Posts>>(postList, HttpStatus.OK);
	}

	// 등록 이미지 삭제
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<?> dropPost(@PathVariable int postId) {
		Posts post = postService.selectPostOne(postId);
		FileSystemUtils.deleteRecursively(Paths.get(post.getPostPath()).toFile());
		if (postService.deletePost(postId))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
