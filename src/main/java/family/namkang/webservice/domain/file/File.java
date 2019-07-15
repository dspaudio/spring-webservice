package family.namkang.webservice.domain.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 300)
    private String fileName;

    @Column(nullable = false, length = 1000)
    private String fileUrl;

    @Column(nullable = false, length = 1000)
    private String filePath;

    @Column(nullable = false, length = 1000)
    private String mimeType;

    @Column(nullable = false)
    private Long fileSize;
    
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post; 
    

    @Builder
    public File(String fileName, String fileUrl, String filePath, String mimeType, Long fileSize, Post post) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.post = post;
    }
}