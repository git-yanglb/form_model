package com.wy.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * 页面跳转
	 * 
	 * @param module
	 * @param url
	 * @return
	 */
	@RequestMapping("{url}/xhtml")
	public String page(@PathVariable("url") String url) {
		return url;
	}

	/**
	 * 页面跳转(二级目录)
	 * 
	 * @param module
	 * @param function
	 * @param url
	 * @return
	 */
	@RequestMapping("{module}/{url}/xhtml")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
		return module + "/" + url;
	}

	/**
	 * 页面跳转(三级目录)
	 * 
	 * @param module
	 * @param function
	 * @param url
	 * @return
	 */
	@RequestMapping("{subModule}/{module}/{url}/xhtml")
	public String page(@PathVariable("module") String module, @PathVariable("url") String url,
			@PathVariable("subModule") String subModule) {
		return subModule + "/" + module + "/" + url;
	}

}
