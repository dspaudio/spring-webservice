package family.namkang.webservice.service.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.file.File;
import family.namkang.webservice.domain.file.FileRepository;
import family.namkang.webservice.domain.file.FileUtil;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.domain.post.PostRepository;
import family.namkang.webservice.domain.post.PostSpecs;
import family.namkang.webservice.dto.post.PostDetailDto;
import family.namkang.webservice.dto.post.PostListDto;
import family.namkang.webservice.dto.post.PostSaveDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
    private PostRepository postRepository;
    private FileRepository fileRepository;
    
    @Transactional(readOnly = true)
    public Page<PostListDto> findAll(Map<String, String> params, Pageable pageable) {
    	
        return postRepository.findAll(PostSpecs.multiple(params), pageable)
                .map(PostListDto::new);
    }
    
    @Transactional(readOnly = true)
    public PostDetailDto findById(Long boardId, Long id) {
    	
        return postRepository.findByBoardIdAndId(boardId, id).map(PostDetailDto::new).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));
    }
    
    @Transactional
    public Post save(PostSaveDto dto, MultipartFile[] files){
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	Post post;
    	if (dto.getId() == null) {
            post = postRepository.save(dto.toEntity());
            // groupNo이 설정되지 않았을 경우 id값으로 설정
            if ( post.setDefaultGroupNo() ) post = postRepository.save(post);
            
            
    	} else {
        	post = postRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        	post.update(dto);
        	// 업로드 파일 저장처리 필요
        	post = postRepository.save(post);
    	}

    	// 업로드 파일 저장처리 필요
        try {
			fileRepository.saveAll(FileUtil.getUploadedFilesPost(files, post.getId()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
        
    	return post;
    }
    
    
}
