package com.YameShop.Controller.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.YameShop.domain.AppUser;
import com.YameShop.domain.Category;
import com.YameShop.domain.Images;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.KhacHang;
import com.YameShop.domain.MyCounter;
import com.YameShop.domain.Product;

import com.YameShop.domain.Product_Color;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.Promontion;
import com.YameShop.domain.Size;
import com.YameShop.imp.ProductDetails;
import com.YameShop.service.AccountSerive;
import com.YameShop.service.CategoryService;
import com.YameShop.service.ImagesServices;
import com.YameShop.service.InvoiceService;
import com.YameShop.service.KhachHangServices;
import com.YameShop.service.LuuLuongTruyCapServices;
import com.YameShop.service.ProductColorService;
import com.YameShop.service.ProductDetailsService;
import com.YameShop.service.ProductService;
import com.YameShop.service.Product_detailsServices;
import com.YameShop.service.PromontionServices;
import com.YameShop.service.SizeService;


@Controller
@SessionAttributes("taikhoan")
@RequestMapping("Home")
public class TrangChuController {
	@Autowired
	ProductService productService;
	@Autowired
	Product_detailsServices product_detailsServices;
@Autowired
ProductDetailsService detailsService;
	@Autowired 
	ProductColorService colorService;
	@Autowired
	PromontionServices promontionServices;
	@Autowired
	ImagesServices imagesServices;
	@Autowired
	LuuLuongTruyCapServices luongTruyCapServices;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	AccountSerive accountSerive;
	@Autowired
	KhachHangServices khachHangServices;
	@Autowired
	CategoryService categoryService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	SizeService sizeService;
	@Autowired
	
	 
	 @ModelAttribute("mycounter")
	    public MyCounter setUpCounter() {
	        return new MyCounter();
	    }
	 	
		
		int sum = 0;
		@GetMapping("Yame")
		public String Home(ModelMap model) {
			MyCounter counter = luongTruyCapServices.findbyid(1);
		
			sum = counter.getCount() + 1;
			
			String pattern = "dd-MM-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String data = simpleDateFormat.format(new Date());
			System.err.println(data + "ngay");
			List<Promontion> listpromontion = promontionServices.findAllEndTime(data ,1);
			for (Promontion promontion : listpromontion) {
				System.err.println(promontion.getEnd_Time());
			}
			model.addAttribute("listpromontion", listpromontion);
			List<Promontion> listpromontionvitri2 = promontionServices.findAllEndTime(data ,2);
			
			model.addAttribute("listpromontionvitri2", listpromontionvitri2);
			
			List<Promontion> listpromontionvitri3 = promontionServices.findAllEndTime(data ,3);
			
			model.addAttribute("listpromontionvitri3", listpromontionvitri3);
			
			
			
			List<Product> listproduct = productService.fidbyCategory("LH0001", 8);
			model.addAttribute("listproduct", listproduct);
			
			List<Product> listaokhoac = productService.fidbyCategory("LH0003", 4);
			model.addAttribute("listaokhoac",listaokhoac);
			
			List<Product> listaosomi= productService.fidbyCategory("LH0004", 4);
			model.addAttribute("listaosomi",listaosomi);
			
			List<Product> listquanjean= productService.fidbyCategoryand("LH0009", "LH0002", 8);
			model.addAttribute("listquanjean",listquanjean);
			List<Product> listquansort = productService.fidbyCategory("LH0005", 4);
			model.addAttribute("listquansort",listquansort);
			List<Product> listqbalo = productService.fidbyCategory("LH0006", 4);
			model.addAttribute("listqbalo",listqbalo);
			List<Product> listdep = productService.fidbyCategory("LH0010", 4);
			model.addAttribute("listdep",listdep);
			List<Product> listnon = productService.fidbyCategory("LH0008", 4);
			model.addAttribute("listnon",listnon);
			System.err.println(sum);
			MyCounter counter2 = luongTruyCapServices.findbyid(1);
			counter2.setCount(sum);
			luongTruyCapServices.Update(counter2);
			
			
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			
			List<Product_Color> colors = colorService.findAll();
			model.addAttribute("colors",colors);
			
			return "/view/home/Home";
		}
		
		int sumview = 0;
		@GetMapping("Product/{idproduct}")
		public String Product_details(ModelMap model ,@PathVariable("idproduct") String idproduct) {
			
			Product product =productService.findById(idproduct);
			
			
			sumview = product.getWiewtruycap() + 1;
			
			
			Product productview = productService.findById(idproduct);
			productview.setWiewtruycap(sumview);
			productService.UpdateProduct(productview);
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			
			model.addAttribute("product", product);
			
			List<Product> sanphamlienquan = productService.sanphamlienquan(idproduct);
			model.addAttribute("sanphamlienquan",sanphamlienquan);
			return "/view/Product_Details/loadproductdetails";
		}
		
		@GetMapping("search")
		public String search(ModelMap model , @RequestParam(name = "name", required = false) String name) {
			System.err.println(name);
			List<Product> list = null;
			if(StringUtils.hasText(name)) {
				list = productService.searchview(name, 15);
				for (Product product : list) {
					System.err.println(product.getProduct_Name());
				}
			}
			else {
				list = productService.findall();
			}
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			model.addAttribute("listsearch", list);
			return "/view/Timkiem/Timkiem";
		}
		@GetMapping("dangkytk")
		public String dangkytk(ModelMap model) {
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			return "/view/DangKy/DangKy";
		}
		@PostMapping("TaoMK")
		public String TạoMK(@RequestParam("phone") String phone ,ModelMap model) {
			System.err.println(phone);
			List<AppUser> appUsers = accountSerive.findByPhone(phone);
			if(appUsers.size() > 0) {
				System.err.println("loisdt");
				return "/view/DangKy/Loi";
			}
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			model.addAttribute("phonene",phone);
			return "/view/DangKy/TaoMK";
		}
		
		@PostMapping("successginin")
		public String dangkytc (ModelMap model , @RequestParam("phone") String phone ,@RequestParam("mk") String mk) {
			System.err.println(phone + "-" + mk);
			AppUser appUser = new AppUser();
			appUser.setSdt(phone);
			appUser.setPassword(bCryptPasswordEncoder.encode(mk));
			KhacHang khacHang = new KhacHang();
			khacHang.setTenKH(phone);
			khacHang.setPhone(phone);
			khacHang.setAppUser(appUser);
			appUser.setKhacHang(khacHang);
			accountSerive.Save(appUser);
			return "/view/DangKy/DKThanhCong";
		}
		@GetMapping("DangNhap")
		public String DangNhap(ModelMap model) {
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			return "/view/DangKy/DangNhap";
		}
		@GetMapping("DangNhapvao")
		@ResponseBody
		public String DangNhapvao(@RequestParam String name, @RequestParam String mk, ModelMap model) {
			List<AppUser> appUser = accountSerive.findByPhone(name);
			for (AppUser appUser2 : appUser) {
				if(appUser2 == null) {
					return "rong";
				}
				else {
					if(appUser2.getUserName() != "" && bCryptPasswordEncoder.matches(mk, appUser2.getPassword())) {
						model.addAttribute("taikhoan", appUser);
						return "thanhcong";
					}else
					{
						System.err.println("dangngaptn");
						return "thatbai";
				}
				}
				
			}
			return "m";
//			if(appUser == null) {
//				return"lt";
//			}
//			else {
//				if(appUser.getUserName() != "" && bCryptPasswordEncoder.matches(mk, appUser.getPassword())) {
//					return "thanhcong";
//				}else
//				{
//					System.err.println("dangngaptn");
//					return "thatbai";
//				}
//			
//			}
			
		}
		@GetMapping("Capnhataikhoan/{id}")
		public String Capnhataikhoan(@PathVariable("id") Integer id ,ModelMap model ) {
			KhacHang khacHang = khachHangServices.finById(id);
			System.err.println("ten" + khacHang.getTenKH());
			model.addAttribute("appUsers",khacHang);
			model.addAttribute("KhacHang", new KhacHang());
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			return "/view/DangKy/Capnhattaikhoan";
		}
		@PostMapping("saveOrUpdate")
		public String saveOrUpdate(ModelMap model,@RequestParam("id") Integer id, @RequestParam("tenKH") String tenKH ,@RequestParam("Birthday") String Birthday , @RequestParam("CustomerAddress") String CustomerAddress) {
			System.err.println(id);
			KhacHang khacHang = khachHangServices.finById(id);
			khacHang.setAddress(CustomerAddress);
			khachHangServices.update(khacHang);
			KhacHang khacHang1 = khachHangServices.finById(id);
			model.addAttribute("appUsers",khacHang1);
			return "/view/DangKy/Capnhattaikhoan";
		}
		
		@GetMapping("danhmuc/{id}")
		public String danhmuc(@PathVariable("id") String id ,ModelMap model) {
			System.err.println(id);
			List<Product> list = productService.fidbyCategory(id, 15);
			Category category = categoryService.findById(id);
			model.addAttribute("category",category);
			model.addAttribute("listproduct", list);
			List<Category> listcategory = categoryService.findviewlimitstatus();
			model.addAttribute("listcategory",listcategory);
			List<Category> listcategorystatus = categoryService.findByStatus(0);
			model.addAttribute("listcategorystatus",listcategorystatus);
			return "/view/Danhmuc/listdanhmuc";
		}
		@GetMapping("muahangthanhcong")
		public String muahangthanhcong(ModelMap model) {
			List<Invoice> list = invoiceService.findAlltop1();
			model.addAttribute("list",list);
			return "/view/DatHang/thanhtoanthanhcong";
		}
		@GetMapping("cart/ThanhToan")
		public String thanhtoan() {
			
			
			return "/view/DatHang/thanhtoan";
		}
	@GetMapping("SuccessThanhToan")
		public String succseethanhtoan() {
			return "/view/DatHang/thanhtoanthanhcong";
		}
	@GetMapping("phantrangsp/{id}/{sobt}/{sokt}")
	public String phantrangspdanhmuc(@PathVariable("id") String id,@PathVariable("sobt") Integer sobt ,@PathVariable("sokt") Integer sokt, ModelMap model) {
		System.err.println(id); 
		List<Product> listphantrangsp = productService.fidbyCategorybtkt(id, sobt, sokt);
		model.addAttribute("listproduct",listphantrangsp);
		Category category = categoryService.findById(id);
		model.addAttribute("category",category);
		List<Category> listcategory = categoryService.findviewlimitstatus();
		model.addAttribute("listcategory",listcategory);
		List<Category> listcategorystatus = categoryService.findByStatus(0);
		model.addAttribute("listcategorystatus",listcategorystatus);
		return "/view/Danhmuc/listdanhmuc";
	}
	
	@GetMapping("xemdonhang/{idkh}")
	public String xemhaodon(ModelMap model, @PathVariable("idkh") Integer idkh) {
		List<Invoice> listkh = invoiceService.finByidKH(idkh);
		model.addAttribute("listkh",listkh);
		List<Category> listcategory = categoryService.findviewlimitstatus();
		model.addAttribute("listcategory",listcategory);
		List<Category> listcategorystatus = categoryService.findByStatus(0);
		model.addAttribute("listcategorystatus",listcategorystatus);
		return "/view/donhang/dsdonhang";
	}
	
	@GetMapping("xemchitiethoadon/{idhd}")
	public String xemchitiethoadon(@PathVariable("idhd") Long idhd ,ModelMap model) {
		Invoice invoice = invoiceService.findbyid(idhd);
		model.addAttribute("invoice",invoice);
		List<Category> listcategory = categoryService.findviewlimitstatus();
		model.addAttribute("listcategory",listcategory);
		List<Category> listcategorystatus = categoryService.findByStatus(0);
		model.addAttribute("listcategorystatus",listcategorystatus);
		
		List<Invoice_details>list = invoiceService.listinvoicedetails(idhd);
		
		model.addAttribute("listinoive", list);
		return "/view/donhang/chitiethoadon";
	}
	
	@GetMapping("TimSize")
	@ResponseBody
	public String TimSize(@RequestParam int chieucao, @RequestParam int cannang) {
		System.err.println(chieucao + "-" + cannang);
		List<Size> size = sizeService.TimSize( chieucao ,cannang);
		
		if (size.size() <= 0) {
			return "Chưa có dữ liệu. Vui lòng xem size mẫu trong chi tiết sản phẩm.";
		}
		else {
			for (Size size2 : size) {
				return "Bạn cao " + chieucao  + " cm, Cân nặng " +  cannang + " kg, Size " + size2.getSize_Name() + " phù hợp nhất với bạn.";
			}
		}
		
		return "";
	}
	@GetMapping("xemdanhsachspdaxem")
	public String xemdanhsachspdaxem(ModelMap model) {
		List<Category> listcategory = categoryService.findviewlimitstatus();
		model.addAttribute("listcategory",listcategory);
		List<Category> listcategorystatus = categoryService.findByStatus(0);
		model.addAttribute("listcategorystatus",listcategorystatus);
		return "/view/xemdssp/xemdssp";
	}
	@GetMapping("KhuyenMai/{idKM}")
	public String CTKM(@PathVariable Long idKM , ModelMap model) {
		Promontion promontion = promontionServices.finbyid(idKM);
		model.addAttribute("promontion", promontion);
		List<Category> listcategory = categoryService.findviewlimitstatus();
		model.addAttribute("listcategory",listcategory);
		List<Category> listcategorystatus = categoryService.findByStatus(0);
		model.addAttribute("listcategorystatus",listcategorystatus);
		return "/view/KhuyenMai/listkhuyenmai";
	}
	@GetMapping("Mausac/{id}")
	public String Mau(@PathVariable String id , ModelMap model) {
		Product_Color product_Color = colorService.findById(id);
		model.addAttribute("product_Color",product_Color);
		List<Product_details> product_details = detailsService.finbuMau(id);
		model.addAttribute("product_details",product_details);
		List<Category> listcategory = categoryService.findviewlimitstatus();
		model.addAttribute("listcategory",listcategory);
		List<Category> listcategorystatus = categoryService.findByStatus(0);
		model.addAttribute("listcategorystatus",listcategorystatus);
		return "/view/xemdssp/listmau";
	}
}
