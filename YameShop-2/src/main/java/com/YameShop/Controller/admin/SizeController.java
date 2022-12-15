package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.YameShop.domain.Category;
import com.YameShop.domain.Size;
import com.YameShop.service.SizeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller

@RequestMapping("admin/size")
public class SizeController {
	@Autowired
	SizeService sizeService;
	
	@GetMapping("add")
	public String AddSize(ModelMap model) {
			List<Size> listsize = sizeService.findAll();
			model.addAttribute("listsize", listsize);
		return "admin/size/listsize";
	}
//	@GetMapping("delete/{idsize}")
//	public String deletesize(ModelMap model, @PathVariable("idsize") Integer idsize) {
//		sizeMapper.deleteByPrimaryKey(idsize);
//		System.err.println(idsize);
//		return "admin/size/listsize";
//	}
	// save size
	@PostMapping("ajaxsavesize")
	@ResponseBody
	public List<Size> savesize(@RequestParam String dataJson ) throws JsonMappingException, JsonProcessingException {
			
		ObjectMapper objectMapper = new ObjectMapper();
	//	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode jsonNode;
		System.err.println(dataJson);
		
			jsonNode = objectMapper.readTree(dataJson);
			Size size = new Size();
			String sizename = jsonNode.get("sizeName").asText();
			Integer startheight = jsonNode.get("startHieght").asInt();
			Integer endheight = jsonNode.get("endHieght").asInt();
			Integer startwigh = jsonNode.get("startWeigh").asInt();
			Integer endwigh = jsonNode.get("endWeigh").asInt();
			List<Size> list = new ArrayList<>();
			list.add(size);
		//	List<Size> listsizeList = sizeMapper.findBySizeName(size.getSizeName().trim());
			List<Size> listsize = sizeService.findBySizeName(sizename);
			List<Size> listallsize = sizeService.findByAllSize(startheight, endheight, startwigh, endwigh);
			if(listsize.size() > 0) {
				return list;
			}
//			else {
//				if(listallsize.size() > 0) {
//					
//						return  "falseall";
//				}
				else {
					size.setSize_Name(sizename);
					size.setStart_Hieght(startheight);
					size.setEnd_Hieght(endheight);
					size.setStart_Weigh(startwigh);
					size.setEnd_Weigh(endwigh);
					size.setStatus(0);
					sizeService.Save(size);
				return sizeService.findAll();
				}	
			
			
		
	}
	
	// update size
		@PostMapping("ajaxupdatesize")
		@ResponseBody
		public List<Size> updatesize(@RequestParam String dataJson) throws JsonMappingException, JsonProcessingException {
				
			ObjectMapper objectMapper = new ObjectMapper();
		//	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JsonNode jsonNode;
				jsonNode = objectMapper.readTree(dataJson);
				Size size = new Size();
				String id = jsonNode.get("id").asText();
				String sizename = jsonNode.get("sizeName").asText();
			
				Integer startheight = jsonNode.get("startHieght").asInt();
				
				Integer endheight = jsonNode.get("endHieght").asInt();
				Integer startwigh = jsonNode.get("startWeigh").asInt();
				Integer endwigh = jsonNode.get("endWeigh").asInt();
				System.err.println(endwigh);
				List<Size> list = new ArrayList<>();
				list.add(size);
				
			//	List<Size> listsizeList = sizeMapper.findBySizeName(size.getSizeName().trim());
				List<Size> listsize = sizeService.findBySizeName(sizename);
			//	List<Size> listallList = sizeMapper.findByAllSize(size.getStartHieght(), size.getEndHieght(), size.getStartWeigh(), size.getEndWeigh());
				List<Size> listallsize = sizeService.findByAllSize(startheight, endheight, startwigh, endwigh);
				if(listsize.size() > 0 && listallsize.size() > 0) {
					return list;
				}
//				else {
//					if(listallsize.size() > 0) {
//						
//							return  "falseall";
//					}
					else {
						Size size3 = sizeService.findById(id);
						size3.setSize_Name(sizename);
						size3.setStart_Hieght(startheight);
						size3.setEnd_Hieght(endheight);
						size3.setStart_Weigh(startwigh);
						size3.setEnd_Weigh(endwigh);
						size3.setStatus(0);
						sizeService.UpdateSize(size3);
					//	sizeMapper.insert(size);
						
						return sizeService.findAll();
					}	
				
				
			
		}
	
	// Updatestatus
		@PostMapping("updatestatus")
		@ResponseBody
		public List<Size> updatestatus(@RequestParam String id, @RequestParam Integer status) {

			Size size = sizeService.findById(id);
	
			if(status == 0) {
			size.setStatus(1);
			sizeService.UpdateSize(size);
				return sizeService.findAll();
			}
			else {
				size.setStatus(0);
				sizeService.UpdateSize(size);
				return sizeService.findAll();
			}
		}
		
		@PostMapping("deletesize")
		@ResponseBody
		public List<Size> deletecategory( @RequestParam String id) {
			Size size = sizeService.findById(id);
			sizeService.DeleteId(size);

			return sizeService.findAll();
		}

}
