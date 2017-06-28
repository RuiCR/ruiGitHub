package edu.fjnu.cr.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fjnu.cr.domain.Dictionary;

@Controller()
@RequestMapping("/dictionary")
public class DictionaryController {
	
	@RequestMapping("/toQueryDictionary/{id}")
	String toQueryDictionary(@PathVariable Long id,Model model) throws Exception{
		model.addAttribute("userid", id);
		return "dictionary/query_dictionary";
	}
	
	
}
