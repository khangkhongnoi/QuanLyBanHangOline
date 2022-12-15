package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Category;
import com.YameShop.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("admin/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	@GetMapping("add")
	@Transactional
	public String AddCategory(ModelMap model) {
		
	 List<Category>  listcategory =	categoryService.findAllLimitDong(0,15);
	List<Category> listpage = categoryService.findall();
	double tongpage = Math.ceil((double) listpage.size() / 15);
	// int tongpage = listpage.size() / 15;
	int tongsocategory = listpage.size();
	model.addAttribute("tongsocategory", tongsocategory);
	 model.addAttribute( "tongpage", tongpage);
	 
		model.addAttribute("listcategory", listcategory);
		return "admin/category/listcategory";
	}
	
	@GetMapping("showcategorys")
	@Transactional
	public String khangr(ModelMap model) {
		List<Category>  listcategory =	categoryService.findAllLimitDong(0,15);
		List<Category> listpage = categoryService.findall();
		double tongpage = Math.ceil((double) listpage.size() / 15);
		// int tongpage = listpage.size() / 15;
		int tongsocategory = listpage.size();
		model.addAttribute("tongsocategory", tongsocategory);
		 model.addAttribute( "tongpage", tongpage);
		 
			model.addAttribute("listcategory", listcategory);
		return "admin/category/listcategory2";
	}
	@GetMapping("oke")
	@ResponseBody
	@Transactional
public List<Category> getCategory(){
		
		return categoryService.findall();
			
	}
	// Save 
	@PostMapping("ajaxaddcategory")
	@ResponseBody
	@Transactional
	public String ajaxaddcategory(@RequestParam String dataJson, @RequestParam Integer status, @RequestParam Integer solimit) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;
			System.err.println(dataJson);
			try {
				Category category = new Category();
			
				jsonNode = objectMapper.readTree(dataJson);
				String categoryName = jsonNode.get("categoryName").asText();
				List<Category> listCategoryName = categoryService.findByCategoName(categoryName);
				
						if(listCategoryName.size() > 0) {
							for (Category category2 : listCategoryName) {
								System.out.println("ten bi trung vul " + category2.getCategory_Name());
								return "false";
							}
						}
						else {
							category.setCategory_Name(categoryName);
						
//							System.err.println(category.getCategoryName());
							
						category.setStatus(0);
						categoryService.Save(category);
						
						if(status == 2) {
							String html = "";
							List<Category> listcategory = categoryService.findAllLimitDong(0, solimit);
							for (Category category2 : listcategory) {
								
								html +="<tr class=\"categorys\" ";
								html +=" <td > </td>";
								html +=" <td class=\"IdCategory1\" value = "+ category2.getId_Category() + " > "+  category2.getId_Category()   +" </td>";
								html +=" <td class=\"categoryName\" id=\"khangcogi\"> "+  category2.getCategory_Name()  +" </td>";
								if(category2.getStatus() == 0) {
									html +="<td> <span class=\" btn badge bg-secondary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
											
											+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Hoạt động</span>\r\n"
											
											+ "            	</td>";
								}
								if(category2.getStatus() == 1) {
					html +="<td> <span class=\" btn badge bg-primary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
											
											+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Ngưng hoạt động</span>\r\n"
											
											+ "            	</td>";
									
									
								}
								
								html +="<td><a  data-id= " + category2.getId_Category() +"\r\n"
										+ "	data-name=" + category2.getCategory_Name() +"\r\n"
										+ " onclick=\"btninfoaize(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
										+ "        class=\"btn-sm btn-outline-info \" ><i class=\"fas fa-edit\"></i></a>\r\n"
										
										+ "          		\r\n"
										+ "          			<a data-id= " + category2.getId_Category() +"\r\n"
										+ "          				data-name=" + category2.getCategory_Name() +"\r\n"
										+ "          				onclick=\"showmodal(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
										+ "						 class=\"btn-sm btn-outline-danger btnDelete\"><i class=\"fa fa-trash\"></i></a>\r\n"
										+ "          		</td>";
								html +="</tr>";
							}
							return html;
						}
						else {
							String html = "";
							List<Category> listcategory = categoryService.findAllLimitDongStatus(solimit, status);
							for (Category category2 : listcategory) {
								
								html +="<tr class=\"categorys\" ";
								html +=" <td > </td>";
								html +=" <td class=\"IdCategory1\" value = "+ category2.getId_Category() + " > "+  category2.getId_Category()   +" </td>";
								html +=" <td class=\"categoryName\" id=\"khangcogi\"> "+  category2.getCategory_Name()  +" </td>";
								if(category2.getStatus() == 0) {
									html +="<td> <span class=\" btn badge bg-secondary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
											
											+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Hoạt động</span>\r\n"
											
											+ "            	</td>";
								}
								if(category2.getStatus() == 1) {
					html +="<td> <span class=\" btn badge bg-primary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
											
											+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Ngưng hoạt động</span>\r\n"
											
											+ "            	</td>";
									
									
								}
								
								html +="<td><a  data-id= " + category2.getId_Category() +"\r\n"
										+ "	data-name=" + category2.getCategory_Name() +"\r\n"
										+ " onclick=\"btninfoaize(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
										+ "        class=\"btn-sm btn-outline-info \" ><i class=\"fas fa-edit\"></i></a>\r\n"
										
										+ "          		\r\n"
										+ "          			<a data-id= " + category2.getId_Category() +"\r\n"
										+ "          				data-name=" + category2.getCategory_Name() +"\r\n"
										+ "          				onclick=\"showmodal(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
										+ "						 class=\"btn-sm btn-outline-danger btnDelete\"><i class=\"fa fa-trash\"></i></a>\r\n"
										+ "          		</td>";
								html +="</tr>";
							}
							return html;
						}
					}

			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "";
	}
//	// Update
	@PostMapping("ajaxupdatecategory")
	@ResponseBody
	public String ajaxupdate(@RequestParam String dataJson ,@RequestParam Integer status, @RequestParam Integer solimit ) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
			System.err.println(dataJson);
		try {
			jsonNode = objectMapper.readTree(dataJson);
			Category category = new Category();
			String id = jsonNode.get("IdCategory").asText();
			
			String categoryName = jsonNode.get("categoryName").asText();
			System.out.println(categoryName);
			
			List<Category> listName = categoryService.findByCategoName(categoryName);
			if(listName.size() > 0) {
				return "false";
			}
			else {
//				category.setId(id);
				Category category1 = categoryService.findById(id);
				
				category1.setCategory_Name(categoryName);
				categoryService.UpdateCategory(category1);
			
				if(status == 2) {
					String html = "";
					List<Category> listcategory = categoryService.findAllLimitDong(0, solimit);
					for (Category category2 : listcategory) {
						
						html +="<tr class=\"categorys\" ";
						html +=" <td > </td>";
						html +=" <td class=\"IdCategory1\" value = "+ category2.getId_Category() + " > "+  category2.getId_Category()   +" </td>";
						html +=" <td class=\"categoryName\" id=\"khangcogi\"> "+  category2.getCategory_Name()  +" </td>";
						if(category2.getStatus() == 0) {
							html +="<td> <span class=\" btn badge bg-secondary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
									
									+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Hoạt động</span>\r\n"
									
									+ "            	</td>";
						}
						if(category2.getStatus() == 1) {
			html +="<td> <span class=\" btn badge bg-primary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
									
									+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Ngưng hoạt động</span>\r\n"
									
									+ "            	</td>";
							
							
						}
						
						html +="<td><a  data-id= " + category2.getId_Category() +"\r\n"
								+ "	data-name=" + category2.getCategory_Name() +"\r\n"
								+ " onclick=\"btninfoaize(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
								+ "        class=\"btn-sm btn-outline-info \" ><i class=\"fas fa-edit\"></i></a>\r\n"
								
								+ "          		\r\n"
								+ "          			<a data-id= " + category2.getId_Category() +"\r\n"
								+ "          				data-name=" + category2.getCategory_Name() +"\r\n"
								+ "          				onclick=\"showmodal(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
								+ "						 class=\"btn-sm btn-outline-danger btnDelete\"><i class=\"fa fa-trash\"></i></a>\r\n"
								+ "          		</td>";
						html +="</tr>";
					}
					return html;
				}
				else {
					String html = "";
					List<Category> listcategory = categoryService.findAllLimitDongStatus(solimit, status);
					for (Category category2 : listcategory) {
						
						html +="<tr class=\"categorys\" ";
						html +=" <td > </td>";
						html +=" <td class=\"IdCategory1\" value = "+ category2.getId_Category() + " > "+  category2.getId_Category()   +" </td>";
						html +=" <td class=\"categoryName\" id=\"khangcogi\"> "+  category2.getCategory_Name()  +" </td>";
						if(category2.getStatus() == 0) {
							html +="<td> <span class=\" btn badge bg-secondary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
									
									+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Hoạt động</span>\r\n"
									
									+ "            	</td>";
						}
						if(category2.getStatus() == 1) {
			html +="<td> <span class=\" btn badge bg-primary\" data-id= " + category2.getId_Category() +" data-name= " + category2.getStatus() +"  " 
									
									+ "  onclick=\"activeCategory(this.getAttribute('data-id'), this.getAttribute('data-name'))\"  value = "+ category2.getStatus() +" >  Ngưng hoạt động</span>\r\n"
									
									+ "            	</td>";	
						}
						
						html +="<td><a  data-id= " + category2.getId_Category() +"\r\n"
								+ "	data-name=" + category2.getCategory_Name() +"\r\n"
								+ " onclick=\"btninfoaize(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
								+ "        class=\"btn-sm btn-outline-info \" ><i class=\"fas fa-edit\"></i></a>\r\n"
								
								+ "          		\r\n"
								+ "          			<a data-id= " + category2.getId_Category() +"\r\n"
								+ "          				data-name=" + category2.getCategory_Name() +"\r\n"
								+ "          				onclick=\"showmodal(this.getAttribute('data-id'), this.getAttribute('data-name'))\"\r\n"
								+ "						 class=\"btn-sm btn-outline-danger btnDelete\"><i class=\"fa fa-trash\"></i></a>\r\n"
								+ "          		</td>";
						html +="</tr>";
					}
					return html;
				}
				
		}
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	// Updatestatus
	@PostMapping("updatestatus")
	@ResponseBody
	public List<Category> updatestatus(@RequestParam String id, @RequestParam Integer status ,@RequestParam Integer status1, @RequestParam Integer solimit) {
		System.err.println(status.toString());
		
		Category category = categoryService.findById(id);
		System.err.println(category.getCategory_Name());
		
		if(status == 0) {
			category.setStatus(1);
			categoryService.UpdateCategory(category);
			return showstatus(status1, solimit);
			
		}
		else {
			category.setStatus(0);
			categoryService.UpdateCategory(category);
			
			return showstatus(status1, solimit);
		}
		
//		String s1 = "Active";
//		if(status.equals(s1)) {
//		categoryMapper.updatestatus(id, 1);
//			return "0";
//		}
//		else {
//			categoryMapper.updatestatus(id, 0);
//			return "1";
//		}
	//	return "";
	}

	@PostMapping("deletecategory")
	@ResponseBody
	public List<Category> deletecategory( @RequestParam String id, @RequestParam Integer status, @RequestParam Integer solimit ) {
		Category category = categoryService.findById(id);
		categoryService.DeleteId(category);

		return	showstatus(status, solimit);
	}
	// showCategory
	@GetMapping("showCategory")
	@ResponseBody
	public List<Category> showCategory(@RequestParam Integer sotrang, @RequestParam Integer number) {
		if(number == 2) {
			return categoryService.findAllLimitDong(0, sotrang);
		}
		List<Category> listcategory = categoryService.findAllLimitDongStatus(sotrang, number);
		
		return listcategory;
	}
	
	
	// phan trang
	@GetMapping("pagingcategory")
	@ResponseBody
	public List<Category> pagingcategory( @RequestParam int spbatdau ,@RequestParam int status ,@RequestParam int solimit) {
		System.err.println(solimit);
		System.err.println(spbatdau);
		if(status == 2) {
			
			List<Category> listcategory = categoryService.findAllLimitDong(spbatdau,solimit);
			return listcategory;
		}
		else {
		List<Category> listcategory = categoryService.findAllLimitstatus(spbatdau,solimit, status);
		
		return listcategory;
		}
	}
	
	
	@GetMapping("showstatus")
	@ResponseBody
	public List<Category> showstatus( @RequestParam Integer number , @RequestParam Integer sotrang ) {
		
		if(number == 2) {
			List<Category> listcategory = categoryService.findAllLimitDong(0, sotrang);
			return listcategory;
		}
		else {
			
			List<Category> listcategory = categoryService.findAllLimitDongStatus(sotrang, number);
			
			return listcategory;
		}
				
	}
}

