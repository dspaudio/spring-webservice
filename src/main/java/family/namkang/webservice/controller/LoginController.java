package family.namkang.webservice.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller  
@AllArgsConstructor
public class LoginController {
	public static final String HOME_FILE_NAME = "index";

    @GetMapping("/login/oauth2/code/kakao")
    public String index(Model model, @RequestParam Map<String, String> params) {
    	return HOME_FILE_NAME;
    }
}
 
