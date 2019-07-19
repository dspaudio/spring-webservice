package family.namkang.webservice.common;

import java.util.Arrays;
import java.util.stream.Stream;

import lombok.Getter;

public class CommonEnums {

    public enum PostType {

        TYPE1("타입1"),
        TYPE2("타입2"),
        TYPE3("타입3"),
        TYPE4("타입4"),
        TYPE5("타입5"),
        TYPE6("타입6"),
        TYPE7("타입7"),
        TYPE8("타입8");

        @Getter
        private String value;

        PostType(String value) {
            this.value = value;
        }
    }
    public enum PostTypeGroup {

        GROUP1("그룹1", new PostType[] {PostType.TYPE1,PostType.TYPE2,PostType.TYPE3}),
        GROUP2("그룹2", new PostType[] {PostType.TYPE4,PostType.TYPE5,PostType.TYPE6}),
        GROUP3("그룹3", new PostType[] {PostType.TYPE7,PostType.TYPE8}),
        EMPTY("없음", new PostType[] {});

        @Getter
        private String value;
        @Getter
        private PostType[] containedPostType;

        PostTypeGroup(String value, PostType[] containedPostType) {
            this.value = value;
            this.containedPostType = containedPostType;
        }
        
        public static PostTypeGroup findGroup(PostType postType) {
            Stream<PostTypeGroup> stream = Arrays.stream(PostTypeGroup.values());
            stream.filter(group -> hasPostType(group, postType)).findAny().orElse(PostTypeGroup.EMPTY);
            return Arrays.stream(PostTypeGroup.values()).filter(group -> hasPostType(group, postType)).findAny().orElse(PostTypeGroup.EMPTY);
        }
        private static boolean hasPostType(PostTypeGroup group, PostType type) {
            return Arrays.stream(group.getContainedPostType()).anyMatch(contained -> contained==type);
        }
    }
}
