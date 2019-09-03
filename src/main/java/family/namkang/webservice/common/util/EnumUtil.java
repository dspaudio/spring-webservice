package family.namkang.webservice.common.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.dto.EnumCodeNameDto;

public class EnumUtil {
	private static final Map<String, List<EnumCodeNameDto>> ENUM_LIST_MAP = EnumUtil.initEnumList();
	
	public static final String NEWS_CATEGORY = "News.Category";
	
	public static Map<String, List<EnumCodeNameDto>> factoryEnumList() {
		return ENUM_LIST_MAP;
	}
	
	private static Map<String, List<EnumCodeNameDto>> initEnumList() {
		Map<String, List<EnumCodeNameDto>> map = new HashMap<String, List<EnumCodeNameDto>>();
		
		List<EnumCodeNameDto> cat = Arrays.asList( News.Category.class.getEnumConstants() ).stream().map(EnumCodeNameDto::new).collect(Collectors.toList());
		map.put(EnumUtil.NEWS_CATEGORY, cat);
		
		return map;
	}
}
